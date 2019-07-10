package com.cafe24.mysite.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.TestWebConfig;
import com.cafe24.mysite.vo.GuestBookVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class,TestWebConfig.class})
@WebAppConfiguration
public class GuestBookService2Test {
	
	@Autowired
	private GuestBookService2 guestBookService2;
	
	@Autowired
	private GuestBookService guestBookService;
	
	
	
	//service 연결확인
	@Test
	public void TestGuestBookServiceDI() {
		assertNotNull(guestBookService2);
	}
	
	@Test
	public void testGetContentList() {
		List<GuestBookVo> list = guestBookService.getlist();
//		assertArrayEquals(list);
	}
	
	@Test
	public void testWriteContentList() {
		GuestBookVo vo = new GuestBookVo(1L,"강수진","1234","test1","2019-07-10 10:15");
	}
}
