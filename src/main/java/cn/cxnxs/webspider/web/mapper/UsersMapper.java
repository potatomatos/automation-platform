package cn.cxnxs.webspider.web.mapper;

import cn.cxnxs.webspider.web.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 获取登录用户
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Select("select * from users where (username=#{username} and encrypted_password=#{password}) or (email=#{username} and encrypted_password=#{password})")
    Users selectLoginUser(String username, String password);
}
