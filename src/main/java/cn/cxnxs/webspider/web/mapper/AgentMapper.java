package cn.cxnxs.webspider.web.mapper;

import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 爬虫任务配置信息 Mapper 接口
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface AgentMapper extends BaseMapper<Agent> {

    List<Agent> selectByTypeProperties(AgentTypeVo agentTypeVo);
}
