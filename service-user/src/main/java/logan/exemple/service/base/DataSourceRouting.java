package logan.exemple.service.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by logan on 2017/9/27.
 * qq：425018553
 */

public class DataSourceRouting extends AbstractRoutingDataSource {
    private Logger logger = LoggerFactory.getLogger(DataSourceRouting.class);

    @Override
    protected Object determineCurrentLookupKey() {
        if (logger.isDebugEnabled())
            logger.debug("数据源为{}", DataSourceContextHolder.getDbType());
        return DataSourceContextHolder.getDbType();
    }
}
