package com.cafe24.mysite.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.vo.GuestBookVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class GuestBookServiceTest {
	@Autowired
	private GuestBookService guestBookService;
	
	GuestBookVo vo;
	
	@Before
	public void setUp() {
		vo = new GuestBookVo();
		vo.setName("강수진0709");
		vo.setContents("테스트 진행중하핫");
		vo.setPassword("1234");
	}
	
	//insert에 값이 확
	@Test
	@Transactional
	@Rollback
	public void guestbookInsertTest() {
		assertTrue(guestBookService.add(vo));
	}
}
