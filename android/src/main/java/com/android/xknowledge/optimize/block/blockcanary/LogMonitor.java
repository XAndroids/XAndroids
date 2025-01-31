package com.android.xknowledge.optimize.block.blockcanary;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Printer;

import java.util.List;

public class LogMonitor implements Printer {
    private StackSampler mStackSampler;
    private boolean mPrintingStarted = false;
    private long mStartTimestamp;
    // 卡顿阈值
    private long mBlockThresholdMillis = 3000;
    //采样频率
    private long mSampleInterval = 1000;

    private Handler mLogHandler;

    public LogMonitor() {
        mStackSampler = new StackSampler(mSampleInterval);
        HandlerThread handlerThread = new HandlerThread("block-canary-io");
        handlerThread.start();
        mLogHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    public void println(String x) {
        //从if到else会执行 dispatchMessage，如果执行耗时超过阈值，输出卡顿信息
        if (!mPrintingStarted) {
            //记录开始时间
            mStartTimestamp = System.currentTimeMillis();
            mPrintingStarted = true;
            //下次分发前，开始收集主线程堆栈信息
            mStackSampler.startDump();
        } else {
            final long endTime = System.currentTimeMillis();
            mPrintingStarted = false;
            //出现卡顿
            if (isBlock(endTime)) {
                //如果出现卡顿，获取搜集的主线程堆栈信息
                notifyBlockEvent(endTime);
            }
            //分发结束，停止收集主线程堆栈信息
            mStackSampler.stopDump();
        }
    }

    private void notifyBlockEvent(final long endTime) {
        mLogHandler.post(new Runnable() {
            @Override
            public void run() {
                //获得卡顿时主线程堆栈
                List<String> stacks = mStackSampler.getStacks(mStartTimestamp, endTime);
                for (String stack : stacks) {
                    Log.e("block-canary", stack);
                }
            }
        });
    }


    private boolean isBlock(long endTime) {
        return endTime - mStartTimestamp > mBlockThresholdMillis;
    }


}
