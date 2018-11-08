package com.home.module.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.SysOss;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
public interface ISysOssService extends IService<SysOss> {

	void saveSysOss(String url, String flag);

}
