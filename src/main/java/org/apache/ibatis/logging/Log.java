/**
 *    Copyright 2009-2015 the original author or authors.
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
package org.apache.ibatis.logging;

/**
 * @author Clinton Begin
 */
public interface Log {
  //是否启用debug
  boolean isDebugEnabled();
  //是否启用trace
  boolean isTraceEnabled();
  //错误日志级别输出方法
  void error(String s, Throwable e);
  //重载错误日志级别输出方法
  void error(String s);
  //debug日志级别输出方法
  void debug(String s);
  //trace日志级别输出方法
  void trace(String s);
  //warn日志级别输出方法
  void warn(String s);
}
