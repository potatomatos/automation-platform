package cn.cxnxs.webspider.web.service;

import cn.cxnxs.webspider.web.entity.Scenarios;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务场景 服务类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface IScenariosService extends IService<Scenarios> {

    /**
     * 分页获取场景
     * @param scenariosVo
     * @return
     */
    IPage<ScenariosVo> getList(Page<ScenariosVo> page, ScenariosVo scenariosVo);
}
