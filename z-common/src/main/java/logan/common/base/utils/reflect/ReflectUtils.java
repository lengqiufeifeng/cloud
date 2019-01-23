package logan.common.base.utils.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReflectUtils {
    public static Object initObj(Object curr_obj, Class<?> clazz) {
        System.out.println("-----------  method start-------------------------" );
        try {
            Class<?> curr_cls = clazz;
            if (null == curr_obj)
                curr_obj = curr_cls.newInstance();
            do {
                Field[] fields = curr_cls.getDeclaredFields();
                for (Field field : fields) {
                    String key = field.getName();
                    PropertyDescriptor descriptor = new PropertyDescriptor(key, curr_cls);
                    Method method = descriptor.getWriteMethod();
                    switch (field.getType().getName()) {
                        case "java.lang.Boolean":
                        case "boolean":
                            method.invoke(curr_obj, true);
                            break;
                        case "java.lang.Byte":
                        case "byte":
                        case "java.lang.Short":
                        case "short":
                        case "java.lang.Integer":
                        case "int":
                        case "java.lang.Long":
                        case "long":
                            method.invoke(curr_obj, 1);
                            break;
                        case "java.lang.String":
                            String str = "自赋值：" + method.getName();
                            method.invoke(curr_obj, str);
                            break;
                        case "java.util.Date":
                            method.invoke(curr_obj, new Date());
                            break;
                        case "java.util.Map":
                            invokeMap(curr_obj, method, field);
                            break;
                        case "java.util.List":
                            invokeList(curr_obj, method, field);
                            break;
                        case "class java.lang.Integer":
                        case "interface java.util.List":
                        case "interface java.util.Map":
                            break;
                        default:
                            Class<?> val_cla = descriptor.getPropertyType();
                            // 构造成员内部类实例
                            // Constructor<?> con2 =
                            // val_cla.getConstructor(curr_cls);
                            // con2.setAccessible(true);
                            // Object obj2 = con2.newInstance(curr_obj);
                            Object obj2 = val_cla.newInstance();
                            Object val_obj = initObj(obj2, val_cla);
                            method.invoke(curr_obj, val_obj);
                            break;
                    }

                }

                Class<?> scls = curr_cls.getSuperclass();
                if (scls.getName().indexOf("Object" ) > 0) {
                    break;
                } else {
                    curr_cls = scls;
                }
                System.out.println("----class" + curr_cls.getName() + " end---------------------------" );
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------  method end-------------------------" );
        return curr_obj;
    }

    private static void invokeMap(Object obj, Method method, Field field) {

    }

    private static void invokeList(Object obj, Method method, Field field) throws Exception {
        field.setAccessible(true);
        List<Object> list = new ArrayList<Object>();
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = ((ParameterizedType) type);
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type type1 : types) {
                Class cr_clazz = ((Class) type1);
                switch (cr_clazz.getName()) {
                    case "java.lang.Boolean":
                    case "boolean":
                        list.add(true);
                        break;
                    case "java.lang.Byte":
                    case "byte":
                    case "java.lang.Short":
                    case "short":
                    case "java.lang.Integer":
                    case "int":
                    case "java.lang.Long":
                    case "long":
                        list.add(1);
                        break;
                    case "java.lang.String":
                        String str = "自赋值：" + method.getName();
                        list.add(str);
                        break;
                    case "java.util.Date":
                        list.add(new Date());
                        break;
                    default:
                        Object c_obj = initObj(cr_clazz.newInstance(), cr_clazz);
                        list.add(c_obj);
                        break;
                }

            }
            field.set(obj, list);
        }

    }

    public static void getSuperAndCurrentMethods(Class<?> cls) {
        System.out.println("-----------  method start-------------------------" );
        Class<?> curr_cls = cls;
        do {
            Class<?> scls = curr_cls.getSuperclass();
            if (scls.getName().indexOf("Object" ) < 0) {
                System.out.println("super class name:" + scls.getName());
                getMethods(scls);
                System.out.println("--------------super class end---------------------------" );
                curr_cls = scls;
            } else {
                getMethods(cls);
                break;
            }
        } while (true);

        System.out.println("----------  method end-------------------------" );
    }

    public static Method[] getMethods(Class<?> cls) {

        Method[] methods = cls.getDeclaredMethods(); // 获取实体类的所有属性，返回Field数组
        for (Method mod : methods) {
            System.out.println("实体类的所有属性:" + mod.getName());
            System.out.println("实体类的所有属性类型:" + mod.getParameterTypes());
        }

        return methods;
    }

    public static void getSuperAndCurrentFields(Class<?> cls) {
        System.out.println("-----------  method start-------------------------" );
        Class<?> curr_cls = cls;
        do {
            Class<?> scls = curr_cls.getSuperclass();
            if (scls.getName().indexOf("Object" ) < 0) {
                System.out.println("super class name:" + scls.getName());
                getFields(scls);
                System.out.println("--------------super class end---------------------------" );
                curr_cls = scls;
            } else {
                getFields(cls);
                break;
            }
        } while (true);

        System.out.println("----------  method end-------------------------" );
    }

    public static Field[] getFields(Class<?> cls) {
        System.out.println("-----------field start-------------------------" );

        Field[] fields = cls.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        for (Field fid : fields) {
            System.out.println("实体类的所有属性:" + fid.getName());
            System.out.println("实体类的所有属性类型:" + fid.getType().getName());
            System.out.println("实体类的所有属性类型:" + fid.getGenericType().toString());
        }
        System.out.println("-----------field end-------------------------" );

        return fields;
    }
}
