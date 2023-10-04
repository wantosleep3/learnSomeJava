package com.ithm.service;

import com.ithm.mapper.UserMapper;
import com.ithm.pojo.User;
import com.ithm.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
	SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

	/**
	 * 登录方法
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password) {
		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取usermapper
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		// 4、调用方法
		User user = mapper.select(username, password);

		// 释放自由
		sqlSession.close();
		return user;
	}

	/**
	 * 注册
	 *
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取usermapper
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		// 	4、判断用户名是否存在
		User u = mapper.selectByUsername(user.getUsername());
		if(u==null) {
			// 	用户名不存在
			mapper.add(user);
			sqlSession.commit();
		}
		sqlSession.close();
		// 	return true;
		// }else{
		// 	return false;
		// }
		return u==null;
	}

}
