package com.cafe24.mysite.controller.api;


import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.TestWebConfig;
import com.cafe24.mysite.controller.GuestBookController;
import com.cafe24.mysite.service.GuestBookService;

import ch.qos.logback.core.status.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class,TestWebConfig.class})
@WebAppConfiguration
public class GuestbookControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private GuestBookService guestbookService;
	
	@Autowired
	private GuestBookController GuestBookController;
	
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		mockMvc = MockMvcBuilders.standaloneSetup(GuestBookController).build();
	}
	
	//컨트롤 테스트 아님 설정 확인 용
	@Test
	public void testDIGuestbookService() {
		assertNotNull(guestbookService);
	}
	
	//실패하는 테스트
	@Test
	public void testFetchGuestbookList() throws Exception{
		// api가 여기서 결정 됨
		mockMvc.perform(get("/api/guestbook/list/{no}",2L)).andExpect(status().isOk()).andDo(print()); 
		
	}
	
	//insert 테스트
	@Test
	public void testGuestbookInsert() throws Exception {
		mockMvc.perform(post("/api/guestbook/add").param("name", "강수진0709").param("password", "1234").param("contents", "테스트ㅋㅋㅋ"))
		.andExpect(status().isOk()).andDo(print()).andReturn();
	}
	
	
}
