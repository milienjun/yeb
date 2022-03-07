package com.xxx.server.controller;


import com.xxx.server.pojo.Menu;
import com.xxx.server.service.IAdminService;
import com.xxx.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuobin
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {
    @Autowired
    private IMenuService iMenuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return iMenuService.getMenuByAdminId();
    }

}
