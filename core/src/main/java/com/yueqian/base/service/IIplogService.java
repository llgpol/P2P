package com.yueqian.base.service;

import com.yueqian.base.query.IplogQueryObject;
import com.yueqian.base.query.PageResult;

public interface IIplogService {

	PageResult query(IplogQueryObject qo);
}
