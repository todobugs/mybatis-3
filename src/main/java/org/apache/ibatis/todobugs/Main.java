/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.todobugs;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.todobugs.entity.UserInfo;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class Main {

  public static void main(String[] args) throws Exception{
    System.out.println("^^"+int.class);
/*
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession session = sessionFactory.openSession();

    UserInfo userInfo = session.selectOne("selectUserById",1);
    System.out.println(userInfo.toString());*/

//    System.out.println(Main.class.getClassLoader());
  }
}
