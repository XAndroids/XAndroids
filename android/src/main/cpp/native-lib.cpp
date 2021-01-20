//
// Created by QITMAC0000562 on 2019-08-29.
//
#include <jni.h>
#include <string>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <android/log.h>
#include "Player.h"
#include "JavaCallHelper.h"
#include "Log.h"

//FIXME 为什么案例里面不需要include
//参考：https://stackoverflow.com/questions/43926204/android-undefined-reference-with-ndk
#include <android/native_window.h>
#include <android/native_window_jni.h>

#include "client/linux/handler/minidump_descriptor.h"
#include "client/linux/handler/exception_handler.h"
#include <android/log.h>

extern "C" {
#include <libavutil/avutil.h>
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_android_xknowledge_ndk_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_android_xknowledge_ndk_NativeLib_getFFmpegVersion(
        JNIEnv *env,
        jobject /* this */) {
    int version = avutil_version();
    return version;
}


int8_t *m_ptr;
int32_t m_size;
int m_fd;

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_NativeLib_writeTest(JNIEnv *env, jobject instance) {

    std::string file = "/sdcard/a.txt";

    //打开文件
    m_fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);

    //获得一页内存大小
    //Linux采用了分页来管理内存，即内存的管理中，内存是以页为单位,一般的32位系统一页为 4096个字节
    m_size = getpagesize();


    //将文件设置位 m_size这么大
    ftruncate(m_fd, m_size); // 100  1000 10000

    // m_size:映射区的长度。 需要是整数页个字节    byte[]
    m_ptr = (int8_t *) mmap(0, m_size, PROT_READ | PROT_WRITE, MAP_SHARED, m_fd, 0);

    std::string data("asdsadasdasd");
    //将 data 的 data.size() 个数据 拷贝到 m_ptr
    memcpy(m_ptr, data.data(), data.size());

//    __android_log_print(ANDROID_LOG_ERROR, "mmap", "写入数据:%s", data.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_NativeLib_readTest(JNIEnv *env, jobject instance) {
    //申请内存
    char *buf = static_cast<char *>(malloc(100));

    memcpy(buf, m_ptr, 100);

    std::string result(buf);
//    __android_log_print(ANDROID_LOG_ERROR, "MMKV", "读取数据:%s", result.c_str());

    //取消映射
    munmap(m_ptr, m_size);
    //关闭文件
    close(m_fd);
}

//JNI OnLoad方法
//参考：https://blog.csdn.net/fireroll/article/details/50102009
JavaVM *javaVM = 0;

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    javaVM = vm;
    return JNI_VERSION_1_4;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_android_xknowledge_ndk_ffmpeg_Player_nativeInit(JNIEnv *env, jobject thiz) {
    //将Native的Player传递给Java
    Player *player = new Player(new JavaCallHelper(javaVM, env, thiz));
    return (jlong) player;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_ffmpeg_Player_setDataSource(JNIEnv *env, jobject thiz,
                                                            jlong nativeHandle, jstring path_) {
    const char *path = env->GetStringUTFChars(path_, 0);
    Player *player = reinterpret_cast<Player *>(nativeHandle);
    player->setDataSource(path);

    env->ReleaseStringUTFChars(path_, path);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_ffmpeg_Player_prepare(JNIEnv *env, jobject thiz,
                                                      jlong nativeHandle) {
    LOGE("Java_com_android_xknowledge_ndk_ffmpeg_Player_prepare");
    Player *player = reinterpret_cast<Player *>(nativeHandle);
    player->prepare();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_ffmpeg_Player_start(JNIEnv *env, jobject thiz,
                                                    jlong nativeHandle) {
    Player *player = reinterpret_cast<Player *>(nativeHandle);
    player->start();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_ffmpeg_Player_setSurface(JNIEnv *env, jobject thiz,
                                                         jlong nativeHandle, jobject surface) {
    Player *player = reinterpret_cast<Player *>(nativeHandle);
    ANativeWindow *window = ANativeWindow_fromSurface(env, surface);
    player->setWindow(window);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_ffmpeg_Player_stop(JNIEnv *env, jobject thiz, jlong nativeHandle) {
    Player *player = reinterpret_cast<Player *>(nativeHandle);
    player->stop();
    delete player;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_optimize_crash_CrashReport_testNativeCrash(JNIEnv *env, jclass clazz) {
    //FIXME /Users/qitmac0000562/XCodeProjects/XProjects/XKnowledge/android/src/main/cpp/crash-handler.cpp:10: error: undefined reference to '__android_log_print'
    //引入了log库为什么还是找不到？？
    __android_log_print(ANDROID_LOG_INFO, "native", "xxxxxxxxxx");
    int *p = NULL;
    *p = 10;
}

bool DumpCallback(const google_breakpad::MinidumpDescriptor &descriptor,
                  void *context,
                  bool succeeded) {
    __android_log_print(ANDROID_LOG_ERROR, "native", "native crash:%s", descriptor.path());
    return false;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_optimize_crash_CrashReport_initNativeCrash(JNIEnv *env, jclass clazz,
                                                                       jstring path_) {
    const char *path = env->GetStringUTFChars(path_, 0);

    __android_log_print(ANDROID_LOG_INFO, "native", "===> %s", path);
    google_breakpad::MinidumpDescriptor descriptor(path);
    static google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback,
                                                NULL, true, -1);

    env->ReleaseStringUTFChars(path_, path);
}