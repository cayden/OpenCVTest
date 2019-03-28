//
// Created by caydencui on 2019/3/27.
//
#include <jni.h>
#include<opencv2/opencv.hpp>
#include<iostream>
using namespace cv;
using namespace std;

extern "C" JNIEXPORT jintArray

JNICALL
Java_com_cayden_opencvtest_NativeLib_Bitmap2Grey(
        JNIEnv *env,
        jobject /* this */,jintArray buf,jint w,jint h) {
    jint *cbuf;
    jboolean ptfalse = false;
    cbuf = env->GetIntArrayElements(buf, &ptfalse);
    if(cbuf == NULL){
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char*)cbuf);
    // 注意，Android的Bitmap是ARGB四通道,而不是RGB三通道
    cvtColor(imgData,imgData,CV_BGRA2GRAY);
    cvtColor(imgData,imgData,CV_GRAY2BGRA);

    int size=w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, (jint*)imgData.data);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;
}
