#include <windows.h>
#include <stdio.h>
#include <jni.h>

typedef struct {
    ULONGLONG idleTime;
    ULONGLONG kernelTime;
    ULONGLONG userTime;
} CPUUsageTimes;

void getCPUTimes(CPUUsageTimes* times) {
    FILETIME idleTime, kernelTime, userTime;
    GetSystemTimes(&idleTime, &kernelTime, &userTime);

    times->idleTime = ((ULONGLONG)idleTime.dwLowDateTime) | (((ULONGLONG)idleTime.dwHighDateTime) << 32);
    times->kernelTime = ((ULONGLONG)kernelTime.dwLowDateTime) | (((ULONGLONG)kernelTime.dwHighDateTime) << 32);
    times->userTime = ((ULONGLONG)userTime.dwLowDateTime) | (((ULONGLONG)userTime.dwHighDateTime) << 32);
}

int getCpuUsage() {
    CPUUsageTimes prevTimes, currTimes;

    getCPUTimes(&prevTimes);
    Sleep(1000);
    getCPUTimes(&currTimes);

    ULONGLONG idleDiff = currTimes.idleTime - prevTimes.idleTime;
    ULONGLONG kernelDiff = currTimes.kernelTime - prevTimes.kernelTime;
    ULONGLONG userDiff = currTimes.userTime - prevTimes.userTime;
    ULONGLONG totalDiff = kernelDiff + userDiff;

    if (totalDiff == 0) {
        return -1;
    }

    return (int)(100 - ((idleDiff * 100) / totalDiff));
}

JNIEXPORT jint JNICALL Java_com_example_cpu_CpuUsageNative_getCpuUsage
(JNIEnv *env, jobject obj) {
    return getCpuUsage();
}
