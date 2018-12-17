package com.yueqian.base.mapper;

import com.yueqian.base.domain.Logininfo;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LogininfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

	int getCountByUsername(String username);

	Logininfo login(@Param("username") String username,@Param("password") String password,@Param("userType") int userType);

	int getCountByUserType(int userManager);
}