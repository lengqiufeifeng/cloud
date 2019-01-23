package logan.common.base.utils;

import java.util.UUID;

/*
 * 主键生产器
 */
public class PrimaryKeyGenerator {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "" );
    }

}
