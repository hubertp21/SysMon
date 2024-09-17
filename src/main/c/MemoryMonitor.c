#include <windows.h>
#include <stdio.h>
#include <jni.h>

int getMemoryUsage() {
    MEMORYSTATUSEX memInfo;
    memInfo.dwLength = sizeof(MEMORYSTATUSEX);

    if (!GlobalMemoryStatusEx(&memInfo)) {
        return -1;
    }

    DWORDLONG totalMemory = memInfo.ullTotalPhys;
    DWORDLONG freeMemory = memInfo.ullAvailPhys;

    if (totalMemory == 0) {
        return -1;
    }

    DWORDLONG usedMemory = totalMemory - freeMemory;

    return (int)((usedMemory * 100) / totalMemory);
}

JNIEXPORT jint JNICALL Java_com_example_memory_MemoryUsageNative_getMemoryUsage
(JNIEnv *env, jobject obj) {
    return getMemoryUsage();
}
