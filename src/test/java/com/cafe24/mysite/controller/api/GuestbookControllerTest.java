package com.cafe24.mysite.controller.api;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.TestWebConfig;
import com.cafe24.mysite.vo.GuestBookVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class,TestWebConfig.class})
@WebAppConfiguration
public class GuestbookControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		mockMvc = MockMvcBuilders.standaloneSetup(GuestBookController).build();
	}
	

	//컨트롤러 테스트
	@Test
	public void testFetchGuestbookList() throws Exception{
		// resultActions 응답결과를 가지고 있다.
		ResultActions resultActions = mockMvc.perform(get("/api/guestbook/list/{no}",1L).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data",hasSize(2)))
		.andExpect(jsonPath("$.data[0].name",is("강수진")))
		.andExpect(jsonPath("$.data[0].contents",is("test1")))
		.andExpect(jsonPath("$.data[1].no",is(2)))
		.andExpect(jsonPath("$.data[1].name",is("강수진")))
		.andExpect(jsonPath("$.data[1].contents",is("test2"))) 
		;
	}
	
	//insert 테스트
	@Test
	public void testInsertGuestbook() throws Exception {
		GuestBookVo vo = new GuestBookVo();
		vo.setName("user1");
		vo.setPassword("1234");
		vo.setContents("test1");
		
//		↓Mockito 사용이유
//		MailSender mailSender = Mockito.mock(MailSender.class);
//		Mockito.when(voMock.getNo2()).thenReturn("10L");//getNo2()를 호출하면 10L을 호출한다.
//		Long no = (Long)voMock.getNo2();
		
//		회원 가입 시, 메일 보내기
//		Mockito.when(milSenderMock.sendMail("")).thenReturn(true);
//		isSuccess = mailSenderMock.sendMail("");
		
		ResultActions resultActions = mockMvc.perform(post("/api/guestbook/add",1L).contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.name",is(vo.getName())))
		.andExpect(jsonPath("$.data.contents",is(vo.getContents())))
		;
	}
	
	@Test
	public void testDeleteGuestbook() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("no", 1L);
		map.put("password", "1234");
				
		ResultActions resultActions = mockMvc.perform(delete("/api/guestbook/delete",1L).contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		;
	}
}
