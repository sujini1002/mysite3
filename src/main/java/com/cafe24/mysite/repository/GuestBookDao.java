package com.cafe24.mysite.repository;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestBookVo;


@Repository
public class GuestBookDao {
	
	public static Integer PAGENUM = 5;
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean delete(GuestBookVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return 1==count;
	}

	public Boolean insert(GuestBookVo vo) {
		Long count = (long) sqlSession.insert("guestbook.insert", vo);
		return count==1;
	}
	public GuestBookVo insert2(GuestBookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
		GuestBookVo result = sqlSession.selectOne("guestbook.getList2");
		return result;
	}
	public List<GuestBookVo> getList() {
		
		return sqlSession.selectList("guestbook.getList");
	}
	
	public List<GuestBookVo> getList(Long lastNo) {
		return sqlSession.selectList("guestbook.getList3",lastNo);
	}

	
}
