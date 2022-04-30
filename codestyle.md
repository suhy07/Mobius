### Android前端代码规范

本规范结合阿里巴巴Android开发手册、Kotlin代码规范、Jetpack官方文档编写

[toc]

### 一、项目架构

#### MVVM
>MVVM(Model-View-ViewModel)是一种代码架构模式，被广泛应用在Android程序设计领域。Mobius项目Android端使用Jetpack组件进行MVVM架构

M: 仓库层，仓库层要做的工作是自主判断接口请求的数据应该是从数据库中读取还是从网络中获取，并将数据返回给调用方。

### 一、目录结构

项目的目录结构如下
```
 kt
    |---edu
        |---fzu
            |---mobius
                |--- base
                |    |--- BaseAcitvity.kt
                |    |--- xxx.kt
                |
                |--- compose
                |    |--- xxxCompose.kt
                |    |--- xxx.kt
                |
                |--- db
                |    |--- DbManager.kt
                |    |--- xxx.kt
                |
                |--- entity
                |    |--- User.kt
                |    |--- Letter.kt
                |    |--- xxx.kt
                |
                |--- global
                |    |--- xxx.kt   
                |
                |--- network
                |    |--- HttpClient.kt
                |    |--- xxx.kt
                |
                |--- theme
                |    |--- Color.kt
                |    |--- Shape.kt
                |    |--- Theme.kt
                |    |--- Type.kt
                |    |--- xxx.kt
                |   
                |--- util
                    |--- xxxUtil.kt
                    |--- xxx.kt

```

### 二、Kotlin 语言规范

#### 2.1 命名规则

在 Kotlin 中，包名与类名的命名规则非常简单：
包的名称总是小写且不使用下划线（org.example.project）。 通常不鼓励使用多个词的名称，但是如果确实需要使用多个词，可以将它们连接在一起或使用驼峰风格（org.example.myProject）。


##### 2.1.1类、对象、Compose

类与对象及Compose函数的名称以大写字母开头并使用驼峰风格：

```
open class DeclarationProcessor { /*……*/ }

object EmptyDeclarationProcessor : DeclarationProcessor() { /*……*/ }


```

### 三、Compose 语言规范

### 四、Android 资源文件命名与使用

#### drawable资源
