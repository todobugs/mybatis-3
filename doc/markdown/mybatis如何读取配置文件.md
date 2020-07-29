------

![The MyBatis Blog](https://3.bp.blogspot.com/-HKtWXLIvvdk/T6VWCexS-qI/AAAAAAAAATo/QmRUDiFjWd0/s1600/mybatis-superbird-small.png)



用了这么久mybatis，你知道mybatis是如何加载配置文件的？

经常看到采用如下方式读取配置文件：

```java
InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
```

好像这成了唯一的加载方式，事实是这样的吗？

------



**话不多说，直接源码**

既然是读取文件/资源，那肯定牵涉到io操作。在mybatis源码中我们看到有个io包，路径为：`org.apache.ibatis.io`，然后我们看下其架构

![io-architecture](./sources/io-architecture.png)



由图中可以看出，类`Resources`聚合并创建`ClassLoaderWrapper对象。

Resources对资源的加载有五种方式，分别为：

- 生成stream，getResourceAsStream
- 生成properties，getResourceAsProperties
- 生成Reader，getResourceAsReader
- 生成File，getResourceAsFile
- 生成URL，getResourceURL

上述每种方法都会有对应的重载方法，并调用ClassLoaderWrapper中对应的方法，主要是使用对应的类加载器（没有提供时采用默认）

以Resources#getResourceAsStream方法为例：

```java
public class Resources {
  
  private static ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();
  …
    
  //传入资源文件名称
  public static InputStream getResourceAsStream(String resource) throws IOException {
    //传入null 类加载，并调用其重载方法
    return getResourceAsStream(null, resource);
  }

  //getResourceAsStream 重载方法实际上调用classLoaderWrapper对象中对应的方法
  public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
    //具体调用
    InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
    if (in == null) {
      throw new IOException("Could not find resource " + resource);
    }
    return in;
  }
	…
}
```

```java
public class ClassLoaderWrapper {
  …
  ClassLoader defaultClassLoader;//默认类加载器
  ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();//系统类加载器
  
  public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
    return getResourceAsStream(resource, getClassLoaders(classLoader));
  }
  
  InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
    //循环遍历各个类加载器，并使用非空的类加载器
    for (ClassLoader cl : classLoader) {
      if (null != cl) {
        // 试着根据资源名称生成输入流
        InputStream returnValue = cl.getResourceAsStream(resource);
        // 如果returnValue为null,可能是某些类加载器希望已[/]开头,所以会重新尝试加载
        if (null == returnValue) {
          returnValue = cl.getResourceAsStream("/" + resource);
        }
        //如果返回值不为null，则返回（否则继续遍历，直至遍历结束）
        if (null != returnValue) {
          return returnValue;
        }
      }
    }
    return null;
  }
  
  //创建类加载器组（同时也定义了类加载器的顺序）
  ClassLoader[] getClassLoaders(ClassLoader classLoader) {
    return new ClassLoader[]{
        classLoader,//传入的类加载器
        defaultClassLoader,//默认类加载器
        Thread.currentThread().getContextClassLoader(),//线程上下文类加载器
        getClass().getClassLoader(),//当前类加载器
        systemClassLoader//系统类加载器
    };
  }
  …
}
```

以上就是加载资源的过程，其他几种加载方式流程基本相同，不再赘述，有兴趣的朋友可以自行学习。



------

微观世界，达观人生。

做一名踏实的coder ！



**欢迎扫描下方二维码，关注我的个人微信公众号 ~**



![todobugs](todobugs.png)

