/**
 *    Copyright 2009-2019 the original author or authors.
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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that indicate the method signature.
 * 拦截器方法签名注解类
 * @see Intercepts
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Signature {
  /**
   * Returns the java type.
   * type为执行器类型
   * @return the java type
   */
  Class<?> type();

  /**
   * Returns the method name.
   * 要拦截的方法名
   * @return the method name
   */
  String method();

  /**
   * Returns java types for method argument.
   * 被拦截方法的参数数组列表（切记参数列表的顺序要完全一致）
   * @return java types for method argument
   */
  Class<?>[] args();
}
