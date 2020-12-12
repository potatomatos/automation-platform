package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.Scenarios;
import cn.cxnxs.webspider.web.mapper.ScenariosMapper;
import cn.cxnxs.webspider.web.service.IScenariosService;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务场景 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Service
public class ScenariosServiceImpl extends ServiceImpl<ScenariosMapper, Scenarios> implements IScenariosService {

    @Autowired
    private ScenariosMapper scenariosMapper;

    @Override
    public IPage<ScenariosVo> getList(Page<ScenariosVo> page, ScenariosVo scenariosVo) {
        return scenariosMapper.selectScenariosList(page,scenariosVo);
    }
}
