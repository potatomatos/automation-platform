package cn.cxnxs.webspider.web.mapper;

import cn.cxnxs.webspider.web.entity.Scenarios;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 任务场景 Mapper 接口
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface ScenariosMapper extends BaseMapper<Scenarios> {

    IPage<ScenariosVo> selectScenariosList(Page<ScenariosVo> page, ScenariosVo scenariosVo);
}
