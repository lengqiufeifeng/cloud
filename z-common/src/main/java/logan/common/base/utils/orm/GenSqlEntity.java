package logan.common.base.utils.orm;

import logan.common.base.utils.FileHandler;
import logan.common.base.utils.reflect.ClassUtils;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class GenSqlEntity {


    public static void main(String arg[]) {

        try {
            List<Class<?>> classes = ClassUtils.getClasses(Class.forName("logan.common.base.test.vo.FeeInfo" ).getPackage().getName());

            for (Class clas : classes) {
                String st = exec(CustomModel.class);
                FileHandler.writeFile("D:", "initsql.sql", st);
                System.err.println(st);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行注解，拼出sql
     *
     * @param clazz args
     * @throws Exception
     */
    public static String exec(Class clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        DataBaseTable tableModel = (DataBaseTable) clazz
                .getAnnotation(DataBaseTable.class);
        String tableName = stringChange(clazz.getName());
        if (null != tableModel) {
            tableName = tableModel.tableName();
        }

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n";
        String keyc = tableName + "_id";
        sql = sql + keyc + " VARCHAR(50) NOT NULL";
        for (int i = 0; i < fields.length; i++) {

            ColumnsName tabFeild = fields[i].getAnnotation(ColumnsName.class);
            if (tabFeild != null) {

                sql = sql + ",\n" + stringChange(tabFeild.fieldName()) + " "
                        + getColumnType(fields[i]);
            } else {
                sql = sql + " ,\n" + stringChange(fields[i].getName()) + " "
                        + getColumnType(fields[i]);
            }
        }
        sql = sql + " ,\n" + "`CREATE_BY` VARCHAR(20) NULL DEFAULT NULL COMMENT '创建人',\n" +
                "`CREATE_TIME` DATETIME NULL DEFAULT NULL COMMENT '创建时间',\n" +
                "`UPDATE_BY` VARCHAR(20) NULL DEFAULT NULL COMMENT '修改人',\n" +
                "`UPDATE_TIME` DATETIME NULL DEFAULT NULL COMMENT '修改时间'";
        sql = sql + "\n);\n";
        return sql;
    }

    /**
     * 得到type
     *
     * @param field
     * @return
     */
    public static String getColumnType(Field field) {
        String colums = "";
        switch (field.getType().getName()) {
            case "java.lang.Boolean":
            case "boolean":
                colums = "tinyint";
                break;
            case "java.lang.Byte":
            case "byte":
                colums = "bit";
                break;
            case "java.lang.Short":
            case "short":
            case "java.lang.Integer":
            case "int":
                colums = "int";
                break;
            case "java.lang.Long":
            case "long":
                colums = "bigint";
                break;
            case "java.lang.String":
                colums = "varchar(50)";
                break;
            case "java.util.Date":
                colums = "datetime";
                break;
            default:
                colums = "varchar(50)";
                break;
        }
        return colums;
    }

    /**
     * @param s 传入一个字符串
     * @return 返回一个字符串中的大写字母
     */
    private static String stringChange(String s) {
        s = s.substring((s.lastIndexOf("." ) + 1), s.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if (Character.isUpperCase(s.charAt(i))) {
                char c = Character.toLowerCase(s.charAt(i));
                if (i == 0)
                    sb.append(c);
                else
                    sb.append("_" + c);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DataBaseTable {
        String tableName();
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface ColumnsName {
        String fieldName() default "";
    }

    @DataBaseTable(tableName = "CustomModel" )
    public class CustomModel {

        @ColumnsName(fieldName = "userId" )
        public String a_mImUserId;

        @ColumnsName(fieldName = "user_date" )
        public Date UserDate;


        public int dataType;

    }

}