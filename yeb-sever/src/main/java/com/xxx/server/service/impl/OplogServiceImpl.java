package com.xxx.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.OplogMapper;
import com.xxx.server.pojo.Oplog;
import com.xxx.server.service.IOplogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuobin
 * @since 2022-01-25
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}