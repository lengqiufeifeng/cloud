package logan.common.base.utils.orm;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class GenSqlEntity {

	/**
	 * 运行注解，拼出sql
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void exec(String[] args) throws Exception {
		Field[] fields = CustomModel.class.getFields();
		DataBaseTable tableModel = (DataBaseTable) CustomModel.class
				.getAnnotation(DataBaseTable.class);
		String tableName = tableModel.tableName();
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
		for (int i = 0; i < fields.length; i++) {
			ColumnsName tabFeild = fields[i].getAnnotation(ColumnsName.class);
			if (tabFeild != null) {
				if (i == 0) {
					sql = sql + tabFeild.fieldName() + " "
							+ getColumnType(fields[i].getType());
				} else {
					sql = sql + " ," + tabFeild.fieldName() + " "
							+ getColumnType(fields[i].getType());
				}
			}
		}
		sql = sql + ");";
		System.out.println(sql);
	}

	/**
	 * 得到type
	 * 
	 * @param type
	 * @return
	 */
	public static String getColumnType(Type type) {
		String colums = "TEXT";
		if (type == Long.class || (type == Long.TYPE)) {

		} else if (Integer.class == type || (type == Integer.TYPE)) {
			colums = "INTEGER";
		} else if (type == String.class) {

		} else if (type == byte[].class) {
			colums = "BLOB";
		}

		return colums;
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DataBaseTable {
		public String tableName();
	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface ColumnsName {
		String fieldName() default "";
	}

	@DataBaseTable(tableName = "CustomModel")
	public class CustomModel {

		@ColumnsName(fieldName = "userId")
		public String a_mImUserId;

		@ColumnsName(fieldName = "UserCustomList")
		public byte[] b_mUserCustomList;

		@ColumnsName(fieldName = "datatype")
		public int c_mType;

	}

}