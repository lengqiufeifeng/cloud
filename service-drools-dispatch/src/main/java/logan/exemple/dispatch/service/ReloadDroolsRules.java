package logan.exemple.dispatch.service;

import logan.exemple.dispatch.base.KieUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by logan on 2017/11/9.
 * qq：425018553
 */
@Service
public class ReloadDroolsRules {

    public void reload() throws UnsupportedEncodingException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/temp.drl", loadRules());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###" );
        }
        KieUtils.setKieContainer(kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId()));
        System.out.println("新规则重载成功" );
    }

    private String loadRules() {
        // 从数据库加载的规则
        return "package logan.exemple.dispatch\n" +
                "\n" +
                "\n" +
                "\n" +
                "import logan.exemple.dispatch.model.Address;\n" +
                "import logan.exemple.dispatch.model.fact.AddressCheckResult;\n" +
                "\n" +
                "rule \"Postcode should be filled with exactly 6 numbers\"\n" +
                "\n" +
                "    when\n" +
                "        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n" +
                "        checkResult : AddressCheckResult();\n" +
                "    then\n" +
                "        checkResult.setPostCodeResult(true);\n" +
                "System.out.println(\"logs in rule ：check success!\");\n" +
                "end";
    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

}