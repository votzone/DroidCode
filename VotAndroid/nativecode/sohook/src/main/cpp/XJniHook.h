#ifndef __PLUGIN_HOOK_H_
#define __PLUGIN_HOOK_H_
#include <jni.h>


#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_game_wq_sohook_SoHookCore_hookJNIString(JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //__PLUGIN_HOOK_H_
