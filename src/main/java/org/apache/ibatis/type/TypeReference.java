/**
 *    Copyright 2009-2016 the original author or authors.
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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * References a generic type.
 *
 * @param <T> the referenced type
 * @since 3.1.0
 * @author Simone Tripodi
 */
public abstract class TypeReference<T> {

  //原生类型
  private final Type rawType;

  //构造函数，设置原生类型
  protected TypeReference() {
    rawType = getSuperclassTypeParameter(getClass());
  }

  /**
   * 功能描述：根据当前类的Class信息获取超类泛型的参数类型（比如IntegerHandlerType的超类泛型参数为Integer）
   * @param clazz
   * @return
   */
  Type getSuperclassTypeParameter(Class<?> clazz) {
    Type genericSuperclass = clazz.getGenericSuperclass();
    //如果传入类的泛型父类为Class的实例且不为TypeReference类，则已clazz的父类为参数递归调用getSuperclassTypeParameter；否则抛出异常
    if (genericSuperclass instanceof Class) {
      // try to climb up the hierarchy until meet something useful
      if (TypeReference.class != genericSuperclass) {
        return getSuperclassTypeParameter(clazz.getSuperclass());
      }
      throw new TypeException("'" + getClass() + "' extends TypeReference but misses the type parameter. "
        + "Remove the extension or add a type parameter to it.");
    }

    Type rawType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    // TODO remove this when Reflector is fixed to return Types
    if (rawType instanceof ParameterizedType) {
      rawType = ((ParameterizedType) rawType).getRawType();
    }
    return rawType;
  }

  //获取构造方法中设置的原生类型
  public final Type getRawType() {
    return rawType;
  }

  //toString方法返回rawType的toString方法
  @Override
  public String toString() {
    return rawType.toString();
  }

}
