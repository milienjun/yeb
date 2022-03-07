package com.xxx.server.controller;


import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 个人中心
 */
@RestController
public class AdminInfoController {
    @Autowired
    private IAdminService adminService;

 @ApiOperation(value = "更新当前用户消息")
    @PutMapping("/admin/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication){
     if(adminService.updateById(admin)){
         SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,
                 null, authentication.getAuthorities()));
         return RespBean.success("更新成功！");
     }
     return RespBean.error("更新失败！");
 }


    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService. updateAdminPassword(oldPass, pass, adminId);
    }
}
