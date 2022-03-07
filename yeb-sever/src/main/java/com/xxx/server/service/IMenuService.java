package com.xxx.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhuobin
 * @since 2022-01-25
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAllMenus();
}
