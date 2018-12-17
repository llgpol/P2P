package com.yueqian.base.service;
/**
 * 视频验证相关服务
 * @author Administrator
 *
 */

import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.VedioAuthQueryObject;

public interface IVedioAuthService {

	PageResult query(VedioAuthQueryObject qo);

	void audit(Long logininfoValue, String remark, int state);
}
