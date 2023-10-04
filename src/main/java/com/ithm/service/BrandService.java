package com.ithm.service;

import com.ithm.mapper.BrandMapper;
import com.ithm.pojo.Brand;
import com.ithm.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {

	SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

	/**
	 * 查询所有
	 *
	 * @return
	 */
	public List<Brand> selectAll() {
		// 1、调用BrandMapper.selectAll()
		// SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();每次都要做一次那就提到成员的位置

		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取BrandMapper
		BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

		// 4、调用方法
		List<Brand> brands = mapper.selectAll();

		sqlSession.close();

		return brands;

	}

	/**
	 * 添加
	 * @param brand
	 */
	public void add(Brand brand) {
		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取BrandMapper
		BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

		// 4、调用添加方法
		mapper.add(brand);

		// 5、因为是增删改的操作 所以要提交事务
		sqlSession.commit();

		// 释放资源
		sqlSession.close();

	}


	/**
	 * 根据id查询
	 * @return
	 */
	public Brand selectById(int id) {
		// 1、调用BrandMapper.selectAll()
		// SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();每次都要做一次那就提到成员的位置

		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取BrandMapper
		BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

		// 4、调用方法
		Brand brand = mapper.selectById(id);

		sqlSession.close();

		return brand;

	}


	/**
	 * 修改
	 * @param brand
	 */
	public void update(Brand brand) {
		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取BrandMapper
		BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

		// 4、调用添加方法
		mapper.update(brand);

		// 5、因为是增删改的操作 所以要提交事务
		sqlSession.commit();

		// 释放资源
		sqlSession.close();

	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(int id){
		// 2、获取SqlSession
		SqlSession sqlSession = factory.openSession();
		// 3、获取BrandMapper
		BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

		// 4、调用添加方法
		mapper.deleteById(id);

		// 5、因为是增删改的操作 所以要提交事务
		sqlSession.commit();

		// 释放资源
		sqlSession.close();
	}

}
