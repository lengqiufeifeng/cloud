package logan.common.base.test;

import java.util.Date;
import java.util.List;



import com.google.gson.Gson;

import logan.common.base.utils.reflect.*;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

public class JTest {
    @Test
    public void TestReflectSql() {
        try {
            List<Class<?>> classes = ClassUtils.getAllSuperclasses(Class.forName("logan.common.base.test.vo.FeeInfo"));

            for (Class clas : classes) {
                String st = GenSqlEntity.exec(clas);
                FileHandler.writeFile("D:", "initsql.sql", st);
                System.err.println(st);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
