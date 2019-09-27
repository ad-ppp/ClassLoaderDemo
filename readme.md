# A Demo for classLoader. Feature
1. Execute code not installed as part of an application.
2. 打包可执行 jar 程序，查看 /helloAndroid/readme.md
3. A template of network classloader.

## Question ,如果回答不出，可以参考文末链接回答问题。
#### Java VM
- 为什么JVM采用的是 双亲委派模型 加载class，它有那些好处？
- 怎么判断是同一个class？
- JVM 中的 ClassLoader 的层级关系，画出层级图
- Android 中的 ClassLoader 的层级关系，画出层级图
- BootClassLoader 是在什么时候加载的？
- JVM 中的 运行时数据区域主要存放哪些内容
- 描述，GC 回收技术。
- 描述，GC 的工作原理。
- WeakReference 与 SoftReference 的区别

#### Android VM
1. 说说 ART 和 Dalvik 的区别
2. ODEX 是什么文件，它有什么用
3. Android 的ClassLoader是有哪个类主要处理

## 测试准备
1. 将子模块 helloAndroid 生成的jar包，用dx工具生成包含 dex文件的jar包
```
1. cd ${main project path}
2. dx --dex --output=helloAndroid_dex.jar ./helloAndroid/gen/helloAndroid.jar 
为： 
```
2. 将生成的 helloAndroid_dex.jar push 到 /sdcard/helloAndroid/helloAndroid_dex.jar
`adb push ${helloAndroid_dex.jar path} /sdcard/helloAndroid/helloAndroid_dex.jar`
3. 测试

# 参考
- [热修复入门：Android 中的 ClassLoader](https://jaeger.itscoder.com/android/2016/08/27/android-classloader.html)
- [一看你就懂，Java中的ClassLoader详解](https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650238826&idx=1&sn=294d91d9a190ea56822696994f8a04b0&chksm=88639e05bf1417137f364037bb35a8fcc120679c92b924cfabffa7011199d8ac8bba69a25194&scene=38#wechat_redirect)
- [JVM 的 工作原理，层次结构 以及 GC工作原理](https://segmentfault.com/a/1190000002579346)
- [从源码分析 Android dexClassLoader 加载机制原理](https://blog.csdn.net/nanzhiwen666/article/details/50515895)