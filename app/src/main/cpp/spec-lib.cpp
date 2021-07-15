#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_compose_cvsoul_util_crypto_JNI_genSpecKey(JNIEnv *env, jobject thiz) {
    std::string key = "im+nxllk7nNeFlPNMeywHP63HyknAvoxex/VAB9dH1p1ruUJb6vVWwFT5ZVhFkiM";

    return env->NewStringUTF(key.c_str());
}