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
package org.apache.ibatis.binding;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Clinton Begin
 * @author Eduardo Macarron
 * @author Lasse Voss
 */
public class MapperRegistry {
  /** 全局配置类 */
  private final Configuration config;
  /** 已添加的mapper代理类工厂 */
  private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();
  /** 构造函数 */
  public MapperRegistry(Configuration config) {
    this.config = config;
  }

  /**
   * @since 3.2.2
   */
  /** 根据包名添加mapper */
  public void addMappers(String packageName) {
    //默认superType为Object.class,这样该包下的所有接口均会被添加到knownMappers中
    addMappers(packageName, Object.class);
  }

  /**
   * @since 3.2.2
   */
  /** 根据指定包名及父类类型添加mapper */
  public void addMappers(String packageName, Class<?> superType) {
    /** 通过resolverUtil类判断查询packageName包下所有匹配superType的类型，并添加到一个set类型集合中 */
    ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();
    resolverUtil.find(new ResolverUtil.IsA(superType), packageName);
    Set<Class<? extends Class<?>>> mapperSet = resolverUtil.getClasses();
    /** 循环遍历该集合，并将该mapperClass添加到knownMappers中 */
    for (Class<?> mapperClass : mapperSet) {
      addMapper(mapperClass);
    }
  }

  /**
   * 判断是否为接口，是的话才会生成代理对象
   * 后续会在运行时该代理对象会被拦截器进行拦截处理
   */
  public <T> void addMapper(Class<T> type) {
    /** 1、判断传入的type是否是一个接口
     *  2、判断knownMappers是否已经存在，若存在则抛出已存在异常。
     *  3、设置是否加载完成标识，final会根据是否加载完成来区别是否删除该type的设置
     *  4、将该接口put到knownMappers中
     *  5、调用MapperAnnotationBuilder构造方法，并进行解析。（具体处理逻辑会在builder模块中展开）
     */
    if (type.isInterface()) {
      if (hasMapper(type)) {
        throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
      }
      boolean loadCompleted = false;
      try {
        knownMappers.put(type, new MapperProxyFactory<>(type));
        // It's important that the type is added before the parser is run
        // otherwise the binding may automatically be attempted by the
        // mapper parser. If the type is already known, it won't try.
        MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
        parser.parse();
        loadCompleted = true;
      } finally {
        if (!loadCompleted) {
          knownMappers.remove(type);
        }
      }
    }
  }

  /** 获取mapper代理对象 */
  @SuppressWarnings("unchecked")
  public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
    if (mapperProxyFactory == null) {
      throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
    }
    try {
      return mapperProxyFactory.newInstance(sqlSession);
    } catch (Exception e) {
      throw new BindingException("Error getting mapper instance. Cause: " + e, e);
    }
  }

  /**判断knownMappers中是否存在该类型的mapper代理工厂*/
  public <T> boolean hasMapper(Class<T> type) {
    return knownMappers.containsKey(type);
  }

  /**
   * @since 3.2.2
   */
  /** 主要用于测试，无需关注 */
  public Collection<Class<?>> getMappers() {
    return Collections.unmodifiableCollection(knownMappers.keySet());
  }
}
