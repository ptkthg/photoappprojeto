#include <jni.h>
#include <string>
#include <jni.h>

extern "C" JNIEXPORT jstring
Java_com_example_patrick_1santos_HomeActivity_addFive(
        JNIEnv* env,
        jobject loader,
        jint input) {
    int result = input + 5;
    return env->NewStringUTF(std::to_string(result).c_str());
}