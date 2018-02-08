package logan.exemple.service.monitoring.base;


import logan.common.base.boot.AbstractContextLoaderListener;

/**
 * Created by logan on 2017/9/22.
 * qq：425018553
 */
public class MonContextLoader extends AbstractContextLoaderListener {

    @Override
    public String getCallName() {
        return "service-monitoring";
    }

    @Override
    public boolean isContextInitialized() {
        return false;
    }
}
