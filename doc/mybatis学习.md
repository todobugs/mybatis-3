# mybatis学习

## 传统jdbc的处理流程：

1、通过Class.forName("com.mysql.jdbc.Driver")，加载数据库驱动

2、通过DriverManager.getConnection获取connection链接

3、通过PreparedStatement 获取准备执行语句

4、组装执行语句

5、执行statement语句

6、提交或回滚

7、关闭执行语句

8、关闭connection



基于Mybatis学习

[Mybatis 开发文档](https://mybatis.org/mybatis-3/zh/getting-started.html)

```xml

```



MyBatis框架执行流程

  1.将sql语句和数据库配置信息保存在配置文件
  2.在MyBatis运行时，将配置信息存储Configuration对象
  3.在创建SqlSession对象提供属性
        1） Configuration对象
        2） dirty:true   sql语句执行完毕后   可以事务提交
                  false  sql语句执行发送错误  事务进行回滚
        3） Executor执行器对象：
                         创建Statement对象，在创建过程中
                         依靠MapperStatement对象将赋值内容与sql占位符
                         进行绑定

  4.SqlSession.commit(): 根据此时dirty属性决定提交和回滚
  5.SqlSession.close();  



![image-20200313151836465](/Users/mingyi/Library/Application Support/typora-user-images/image-20200313151836465.png)

mybatis 整体运行流程



![image-20200315213830142](/Users/mingyi/Library/Application Support/typora-user-images/image-20200315213830142.png)

session创建相关类关系



![image-20200315214123415](/Users/mingyi/Library/Application Support/typora-user-images/image-20200315214123415.png)

Executor执行器相关类关系图

![image-20200315214652164](/Users/mingyi/Library/Application Support/typora-user-images/image-20200315214652164.png)

builder相关类关系图

![image-20200315220723515](/Users/mingyi/Library/Application Support/typora-user-images/image-20200315220723515.png)

数据源相关









