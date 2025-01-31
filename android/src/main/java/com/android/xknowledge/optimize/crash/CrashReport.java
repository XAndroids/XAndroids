package com.android.xknowledge.optimize.crash;

import android.content.Context;

import java.io.File;

public class CrashReport {

    static {
        System.loadLibrary("native-lib");
    }

    public static void init(Context context) {
        //开启java监控
        Context applicationContext = context.getApplicationContext();
        CrashHandler.init(applicationContext);

        //开启ndk监控
        File file = new File(applicationContext.getExternalCacheDir(), "native_crash");
        if (!file.exists()) {
            file.mkdirs();
        }
        initNativeCrash(file.getAbsolutePath());
    }

    private static native void initNativeCrash(String path);

    public static native void testNativeCrash();

    public static void testJavaCrash() {
        int i = 1 / 0;
    }
}
