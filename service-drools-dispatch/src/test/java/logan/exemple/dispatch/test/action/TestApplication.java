package logan.exemple.dispatch.test.action;


import logan.common.base.utils.http.HttpClientUtil;
import logan.common.base.utils.http.Httphandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by qiufeng on 2017/9/19.
 */

public class TestApplication {
    private static final Logger LOG = LoggerFactory.getLogger(TestApplication.class);

    @Test
    public void address() throws Exception {
        Httphandler.BEncode="UTF-8";
        InputStream st = HttpClientUtil.httpAsyncGet("http://127.0.0.1:8082/test/address", null,
                null, Charset.forName("UTF-8"), null).getEntity().getContent();

        System.err.println(Httphandler.convertStreamToString(st));
        st=HttpClientUtil.httpAsyncGet("http://127.0.0.1:8082/test/reload", null, null,
                null, null).getEntity().getContent();
        System.err.println(Httphandler.convertStreamToString(st));
        st = HttpClientUtil.httpAsyncGet("http://127.0.0.1:8082/test/address", null,
                null, Charset.forName("UTF-8"), null).getEntity().getContent();
        System.err.println(Httphandler.convertStreamToString(st));
    }

}
