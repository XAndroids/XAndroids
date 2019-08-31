package com.example.xknowledge.framework.eventbus

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.xknowledge.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class StickyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    //订阅的时候，可以立即获取发送的粘性事件
    @Subscribe(sticky = true)
    fun onStickMessageEvent(event: MessageEvent) {
        Log.i(
            "EventbusActivity",
            "onStickMessageEvent," + event.message + ",Thread = ${Thread.currentThread()}"
        )
    }
}
