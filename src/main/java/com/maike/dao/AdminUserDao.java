package com.maike.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.maike.entity.AdminUser;

public interface AdminUserDao {
		//查询所有的管理员用户
		List<HashMap<String, Object>> selectadminUSer();
		//根据username查询管理员 用户
		ArrayList<HashMap<String, Object>> selectadminUSer(String username);
		//根据id号查询管理员
		AdminUser selectadminUser(String id);
		//通过id号修改管理员信息
		int updateadmininfo(AdminUser admin);
		//添加新的管理员信息
		int insertadmininfo(AdminUser adminUser);
		int deleteadminuser(String id);
}
