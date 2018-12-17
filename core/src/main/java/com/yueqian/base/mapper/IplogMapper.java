package com.yueqian.base.mapper;

import com.yueqian.base.domain.Iplog;
import com.yueqian.base.query.IplogQueryObject;

import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);


    /**
     * 高级查询总数
     * @param qo
     * @return
     */
	int queryForCount(IplogQueryObject qo);

	/**
	 * 查询当前页
	 * @param qo
	 * @return
	 */
	List<Iplog> query(IplogQueryObject qo);

}