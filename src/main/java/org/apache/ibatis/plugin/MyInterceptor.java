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
package org.apache.ibatis.plugin;

import org.apache.ibatis.executor.Executor;

import java.util.Properties;

/**
 * @Description: Intercepts 标识启用自定义拦截
 * Signature参数：
 *  method表示指定的拦截方法，
 *  type表示拦截类型，
 *  arg表示传入参数，且参数顺序一定要与对应的方法一致，避免重载方法（需要在mybatis-config.xml配置文件中添加）
 * @Author: mingyi
 * @Date: 2020/3/14 19:42
 * @package: org.apache.ibatis.plugin
 * @name: MyInterceptor
 */
/*@Intercepts({
  @Signature(method = "",type = Executor.class,args = {

  })
})*/
public class MyInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    return null;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target,this);
  }

  @Override
  public void setProperties(Properties properties) {

  }
}
