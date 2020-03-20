
### Java 类的加载过程
Java语言系统自带有三个类加载器:

Bootstrap ClassLoader：
最顶层的加载类，主要加载核心类库，%JRE_HOME%\lib 下的 rt.jar、resources.jar、charsets.jar 和 class等。另外需要注意的是可以通过启动jvm时指定 -Xbootclasspath 和 路径 来改变 Bootstrap ClassLoader 的加载目录。比如 java -Xbootclasspath/a:path 被指定的文件追加到默认的 bootstrap 路径中。我们可以打开我的电脑，在上面的目录下查看，看看这些jar包是不是存在于这个目录。

Extension ClassLoader：
扩展的类加载器，加载目录 %JRE_HOME%\lib\ext 目录下的jar包和class文件。还可以加载 -D java.ext.dirs 选项指定的目录。

Appclass Loader 也称为 SystemAppClass 加载当前应用的classpath的所有类。

我们上面简单介绍了 3个ClassLoader。说明了它们加载的路径。并且还提到了 -Xbootclasspath 和 -D java.ext.dirs 这两个虚拟机参数选项。