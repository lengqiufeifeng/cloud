package logan.exemple.dispatch.controller;

import logan.exemple.dispatch.base.KieUtils;
import logan.exemple.dispatch.model.Address;
import logan.exemple.dispatch.model.fact.AddressCheckResult;
import logan.exemple.dispatch.service.ReloadDroolsRules;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by logan on 2017/11/9.
 * qq：425018553
 */
@RequestMapping("/test" )
@Controller
public class TestController {

    @Resource
    private ReloadDroolsRules rules;

    @ResponseBody
    @RequestMapping("/address" )
    public String test() {
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();

        Address address = new Address();
        address.setPostcode("994251" );

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        String msg = "触发了" + ruleFiredCount + "条规则";

        if (result.isPostCodeResult()) {
            msg = msg + "————规则校验通过";
        }

        kieSession.dispose();
        return msg;
    }

    @ResponseBody
    @RequestMapping("/reload" )
    public String reload() throws IOException {
        rules.reload();
        return "ok";
    }
}
