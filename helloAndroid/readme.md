## Feature
1. 运行参数
2. 打包依赖的jar包到新jar文件 [gradle :helloAndroid:jar -PallInOne] ,需要加入 allInOne 参数
3. 生成备份等

## 注意点
#### 打包依赖的jar包到新jar文件
需要 compile 方式依赖第三方lib，使用 implementation 不起作用。
#### dx 工具生成 dexfile
bad class file magic (cafebabe) or version (0034.0000)

是因为 class文件未能兼容 java 7。 
修改如下： sourceCompatibility = 1.7，并且不用lambda表达式

## 使用方式
- gradle :helloAndroid:jar
- gradle :helloAndroid:jar -PallInOne   // 打包第三方lib，到jar
- java -jar ${jar的位置} "${参数1}" "${参数2}" ...

