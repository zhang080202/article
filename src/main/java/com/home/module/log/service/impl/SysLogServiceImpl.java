package com.home.module.log.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.SysLog;
import com.home.module.log.mapper.SysLogMapper;
import com.home.module.log.service.ISysLogService;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Override
	@Transactional
	public void saveLog(SysLog syslog) {
		baseMapper.insert(syslog);
	}

}
