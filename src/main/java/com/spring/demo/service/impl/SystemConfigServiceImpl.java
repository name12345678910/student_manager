package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.demo.entity.SystemConfig;
import com.spring.demo.mapper.SystemConfigMapper;
import com.spring.demo.service.SystemConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {

}
