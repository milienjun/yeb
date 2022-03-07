package com.xxx.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhuobin
 * @since 2022-01-25
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
