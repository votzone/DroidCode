#include <string>
#include "XJniHook.h"
#include "Logger.h"

#include "MSHook/Hooker.h"

#define HOOK(func) Cydia::elfHookFunction("libnative-lib.so", #func, (void*) my_##func, (void**) &org_##func)


JNIEXPORT jstring JNICALL (*org_Java_game_wq_nativecode_MainActivity_stringFromJNI)(JNIEnv *env, jobject );

JNIEXPORT jstring JNICALL my_Java_game_wq_nativecode_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
	jstring hello = org_Java_game_wq_nativecode_MainActivity_stringFromJNI(env, thiz);
    const char *str = env->GetStringUTFChars(hello, false);
	LOGD("#Java_game_wq_nativecode_MainActivity_stringFromJNI %s.", str);

	std::string hello2 = "Hello Hooked";
	return env->NewStringUTF(hello2.c_str());

}


JNIEXPORT jstring JNICALL Java_game_wq_sohook_SoHookCore_hookJNIString(JNIEnv *env, jobject){
    // 核心代码， Hook stringFromJNI
	HOOK(Java_game_wq_nativecode_MainActivity_stringFromJNI);

    // 为了返回 jstring对象，没有实际意义
    std::string hello2 = "Hello Hooked";
    return env->NewStringUTF(hello2.c_str());
}

