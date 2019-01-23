package logan.common.base.utils.orm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

public class GenEntityMysql {

    private String packageOutPath = "com.common.test.model";// 指定实体生成所在包的路径
    private String authorName = "logan.xu";// 作者名字
    private String tablename = "top_user_shop_cart";// 表名
    private String tablec = "";// 表注释
    private String[] colnames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private String[] colComments; // 列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    // 数据库连接
    private static final String URL = "jdbc:mysql://localhost:3306/activelife";
    private static final String NAME = "mdao";
    private static final String PASS = "mdao";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection con;

    /*
     * 构造函数
     */
    public GenEntityMysql() {
        // 创建连接
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL, NAME, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // try {
            // con.close();
            // } catch (SQLException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
        }
    }

    /**
     * 根据表明生成实体
     *
     * @param tablename
     * @throws SQLException
     */
    public void genEntityFormTable(String tablename) throws SQLException {
        // 查要生成实体类的表
        this.tablename = tablename;
        String sql = "show table status like '" + tablename + "'";
        PreparedStatement pStemt = con.prepareStatement(sql);
        ResultSet resultSet = pStemt.executeQuery();
        while (resultSet.next()) {
            tablec = resultSet.getString("comment" );
        }
        // sql = "select * from " + tablename + " limit 1";
        sql = "SHOW FULL COLUMNS FROM " + tablename;
        pStemt = con.prepareStatement(sql);
        // ResultSetMetaData rsmd = pStemt.getMetaData();
        // int size = rsmd.getColumnCount(); // 统计列
        resultSet = pStemt.executeQuery();
        resultSet.last();
        int size = resultSet.getRow();// 获取行数
        colnames = new String[size];
        colTypes = new String[size];
        colComments = new String[size];
        resultSet.first();// 回到第一个
        for (int i = 0; i < size; i++) {
            colnames[i] = resultSet.getString("field" );
            colTypes[i] = resultSet.getString("type" );
            if (colTypes[i].indexOf("datetime" ) > -1) {
                f_util = true;
            }
            if (colTypes[i].indexOf("image" ) > 0
                    || colTypes[i].indexOf("text" ) > -1) {
                f_sql = true;
            }
            colComments[i] = resultSet.getString("comment" );
            resultSet.next();
        }
        String content = parse(colnames, colTypes, colComments);
        try {
            File directory = new File("" );
            // System.out.println("绝对路径："+directory.getAbsolutePath());
            // System.out.println("相对路径："+directory.getCanonicalPath());
            String path = this.getClass().getResource("" ).getPath();
            String outputPath = directory.getAbsolutePath() + "/src/main/java/"
                    + this.packageOutPath.replace(".", "/" ) + "/"
                    + initcap(_relL(tablename)) + ".java";
            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colTypes,
                         String[] colComments) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n" );
        sb.append("\r\n" );
        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n" );
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n" );
        }
        // 注释部分
        sb.append("/**\r\n" );
        sb.append(" * " + tablename + " 实体类\r\n" );
        sb.append(" * " + tablec + " \r\n" );
        sb.append(" *\r\n" );
        sb.append(" * @author " + this.authorName + " \r\n" );
        sb.append(" * @date " + new Date() + "\r\n" );
        sb.append(" */ \r\n" );
        // 实体部分
        sb.append("public class " + initcap(_relL(tablename)) + "{\r\n" );
        processAllAttrs(sb);// 属性
        processAllMethod(sb);// get set方法
        sb.append("}\r\n" );

        // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\t/**\r\n" );
            sb.append("\t * " + colComments[i] + "\r\n" );
            sb.append("\t */\r\n" );
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
                    + _relL(colnames[i]) + ";\r\n" );
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        sb.append("\t\r\n" );
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(_relL(colnames[i])) + "("
                    + sqlType2JavaType(colTypes[i]) + " " + _relL(colnames[i])
                    + ") {\r\n" );
            sb.append("\t\tthis." + _relL(colnames[i]) + " = "
                    + _relL(colnames[i]) + ";\r\n" );
            sb.append("\t}\r\n" );
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                    + initcap(_relL(colnames[i])) + "() {\r\n" );
            sb.append("\t\treturn " + _relL(colnames[i]) + ";\r\n" );
            sb.append("\t}\r\n" );
        }

    }

    /**
     * kk_sk to kkSk
     */
    private String _relL(String str) {
        String st[] = str.split("_" );
        str = "";
        for (int i = 0; i < st.length; i++) {
            if (i != 0)
                st[i] = initcap(st[i]);
            str = str + st[i];
        }
        return str;
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {
        sqlType = sqlType.toLowerCase();
        if (sqlType.indexOf("bit" ) > -1) {
            return "boolean";
        } else if (sqlType.indexOf("tinyint" ) > -1) {
            return "byte";
        } else if (sqlType.indexOf("smallint" ) > -1) {
            return "short";
        } else if (sqlType.indexOf("int" ) > -1) {
            return "int";
        } else if (sqlType.indexOf("bigint" ) > -1) {
            return "long";
        } else if (sqlType.indexOf("float" ) > -1) {
            return "float";
        } else if (sqlType.indexOf("decimal" ) > -1
                || sqlType.indexOf("numeric" ) > -1
                || sqlType.indexOf("real" ) > -1
                || sqlType.indexOf("money" ) > -1
                || sqlType.indexOf("smallmoney" ) > -1) {
            return "double";
        } else if (sqlType.indexOf("varchar" ) > -1
                || sqlType.indexOf("char" ) > -1
                || sqlType.indexOf("nvarchar" ) > -1
                || sqlType.indexOf("nchar" ) > -1
                || sqlType.indexOf("text" ) > -1) {
            return "String";
        } else if (sqlType.indexOf("datetime" ) > -1) {
            return "Date";
        } else if (sqlType.indexOf("image" ) > -1) {
            return "Blod";
        }

        return null;
    }

    /**
     * 出口 TODO
     *
     * @param args
     */
    public static void exec(String[] args) {

        GenEntityMysql gem = new GenEntityMysql();
        try {
            gem.genEntityFormTable("top_user_shop_cart" );
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
