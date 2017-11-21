package logan.exemple.service.base;

/**
 * Created by logan on 2017/9/27.
 * qq：425018553
 */
public class DataSourceContextHolder {
    public enum DbType{
        MASTER,SLAVE
    }

    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    public static void setDbType(DbType dbType){
        if(dbType==null)throw new NullPointerException();
        contextHolder.set(dbType);
    }

    public static DbType getDbType(){
        return contextHolder.get()==null? DbType.MASTER:contextHolder.get();
    }

    public static void clearDbType(){
        contextHolder.remove();
    }
}
