# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html
# 设置CMAKE的版本号
cmake_minimum_required(VERSION 3.4.1)

# 设置include文件夹的地址
include_directories(${CMAKE_SOURCE_DIR}/src/main/jni/include)

# 设置opencv的动态库
add_library(libopencv_java3 SHARED IMPORTED)
set_target_properties(libopencv_java3 PROPERTIES IMPORTED_LOCATION
            ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libopencv_java3.so)

add_library( # Sets the name of the library.
             native-lib #.so库名 可自定义

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/jni/native-lib.cpp )

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

target_link_libraries( # Specifies the target library.
                       native-lib
                       libopencv_java3

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )