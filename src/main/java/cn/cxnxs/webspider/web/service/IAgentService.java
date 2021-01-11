package cn.cxnxs.webspider.web.service;

import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import cn.cxnxs.webspider.web.vo.AgentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 爬虫任务配置信息 服务类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface IAgentService extends IService<Agent> {

    /**
     * 获取agent配置信息
     * @param agentType agent类型
     * @return
     */
    Map<String,Object> getAgentConfig(String agentType);


    /**
     * 查询所有
     * @param agentTypeVo 查询条件
     * @return none
     */
    List<AgentVo> findByTypeProperties(AgentTypeVo agentTypeVo);

    /**
     * 保存代理
     * @param agentVo 代理数据
     * @return none
     */
    Map<String, String> saveAgent(AgentVo agentVo);

    /**
     * 根据id获取代理
     * @param id 主键
     * @return none
     */
    AgentVo getAgentById(Integer id);
}
