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

import java.util.Properties;

/**
 * @author Clinton Begin
 * 自定义拦截器的接口
 * 实现该接口的实现类，需要有注解：
 */
public interface Interceptor {

  //对需要拦截的业务进行增强操作，跟jdk动态代理类似
  Object intercept(Invocation invocation) throws Throwable;

  /**
   * @desc 作用：
   * 如果被拦截对象所在的在类有实现接口就为当前拦截对象生成一个代理对象
   * 如果被拦截对象所在的在类没有指定的接口，不需要则返回当前对象
   * @param target 参数为被拦截的对象
   * @return
   */
  default Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }
  //读取配置文件中的属性信息（默认空实现，如果用户自定义了相关配置参数，则需要做相应处理）
  default void setProperties(Properties properties) {
    // NOP
  }

}
