package cn.cxnxs.webspider.core.agents;

import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.vo.AgentVo;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteAgentTest {

    @Autowired
    private IAgentService agentService;
    private static final Logger logger= LoggerFactory.getLogger(WebsiteAgentTest.class);

    @Test
    public void testGet() throws HttpProcessException {
        Agent agent = agentService.getById(5);
        AgentVo agentVo=new AgentVo();
        ObjectUtil.transValues(agent,agentVo);
        JSONObject options=new JSONObject();
        if (agentVo.getOptions()!=null){
            options=JSONObject.parseObject(agentVo.getOptions());
        }
        WebsiteAgent websiteAgent=new WebsiteAgent();
        websiteAgent.option(options).collect(null);
    }
}
