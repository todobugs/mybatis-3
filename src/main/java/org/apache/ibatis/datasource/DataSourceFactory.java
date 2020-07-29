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
package org.apache.ibatis.datasource;

import java.util.Properties;
import javax.sql.DataSource;

/**
 * @author Clinton Begin
 */

/**
 * 数据源工厂接口类，只提供两个方法：
 * 1.设置相关配置属性
 * 2.获取数据源
 * 3.该接口有三个实现类：PooledDataSourceFactory,UnpooledDataSourceFactory,JndiDataSourceFactory
 */
public interface DataSourceFactory {

  //设置属性（其目的是位datasource填充配置属性）
  void setProperties(Properties props);

  //获取数据源对象
  DataSource getDataSource();

}
