package logan.exemple.service.base;

import logan.common.base.boot.AbstractContextLoaderListener;

/**
 * Created by logan on 2017/9/22.
 * qq：425018553
 * 自定义监听
 */
public class MonContextLoader extends AbstractContextLoaderListener {

    @Override
    public String getCallName() {
        return "service-user";
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
