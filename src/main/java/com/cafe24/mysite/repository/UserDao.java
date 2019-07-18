package com.cafe24.mysite.repository;


import java.util.HashMap;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.UserVo;



@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get1(String email) {
		System.out.println("dao"+email);
		UserVo vo = new UserVo();
		vo.setNo(3L);
		vo.setName("둘리");
		vo.setEmail("aufcl@naver.com");
		vo.setPassword("1234");
		return vo;
	}
	
//	@Autowired
//	private DataSource dataSource;
	
	public UserDao() {
		System.out.println("userDao 생성");
	}
	public Boolean update(UserVo userVo) {
		int count  = sqlSession.update("user.update",userVo);
		return 1 == count;
	}

	public UserVo get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}

	public UserVo get(String email, String password) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword",map);
		
		
		return userVo;
	}

	public boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert",vo);
		System.out.println(vo);
		return 1==count;
	}

}
