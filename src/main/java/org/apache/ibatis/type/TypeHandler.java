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
package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Clinton Begin
 */
public interface TypeHandler<T> {

  //设置参数
  void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

  /**
   * @param columnName Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
   *  根据ResultSet及columnName获取转换结果
   *  请注意：当configuration中的useColumnLabel=false生效，useColumnLabel默认为true（请参看Configuration中的useColumnLabel属性）
   */
  T getResult(ResultSet rs, String columnName) throws SQLException;

  //根据ResultSet及columnIndex索引获取转换结果
  T getResult(ResultSet rs, int columnIndex) throws SQLException;

  //根据CallableStatement及columnIndex索引获取转换结果
  T getResult(CallableStatement cs, int columnIndex) throws SQLException;

}
