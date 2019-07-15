package com.cafe24.mysite.controller.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.cafe24.mysite.vo.UserVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class,TestWebConfig.class})
@WebAppConfiguration
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		mockMvc = MockMvcBuilders.standaloneSetup(GuestBookController).build();
	}
	
	@Test
	public void testUserJoin() throws Exception {
		UserVo userVo = new UserVo();
		
		//Normal User's Join Data
		userVo.setName("강수진");
		userVo.setEmail("zzz12@email.com");
		userVo.setPassword("sujinHi@#34S");
		userVo.setGender("MALE");
		
		ResultActions resultActions = mockMvc.perform(post("/user/api/join")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")));

		// Invalidation in Password Join Data
		UserVo userVo2 = new UserVo();
		userVo2.setName("강수진");
		userVo2.setEmail("aufcl@naver.com");
		userVo2.setPassword("susjooiew34");
		userVo2.setGender("MALE");
		
		resultActions = mockMvc.perform(post("/user/api/join")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo2))).andDo(print());
		
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result",is("fail")))
		;
		
		// Invalidation in Gender Join Data
		userVo2.setName("강수진");
		userVo2.setEmail("aufcl@naver.com");
		userVo2.setPassword("susjoD@oiew34");
		userVo2.setGender("");
		
		resultActions = mockMvc.perform(post("/user/api/join")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo2))).andDo(print());
		
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result",is("fail")))
		;
		
	}
	
	
	@Test
	public void testUserLogin() throws Exception {
		UserVo userVo = new UserVo();
		
		//1. Normal User's login Data
		userVo.setEmail("zzz12");
		userVo.setPassword("sujinHi@#34S");
		
		ResultActions resultActions = mockMvc.perform(post("/user/api/login")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))).andDo(print());
		
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")));

//		// Invalidation in login Data
//		UserVo userVo2 = new UserVo();
//		userVo2.setName("강수진");
//		userVo2.setEmail("aufcl@naver.com");
//		userVo2.setPassword("susjooiew34");
//		userVo2.setGender("MALE");
//		
//		resultActions = mockMvc.perform(post("/user/api/join")
//				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo2))).andDo(print());
//		
//		resultActions.andExpect(status().is4xxClientError())
//		.andExpect(jsonPath("$.result",is("fail")))
//		;
//		
//		// Invalidation in Gender Join Data
//		userVo2.setName("강수진");
//		userVo2.setEmail("aufcl@naver.com");
//		userVo2.setPassword("susjoD@oiew34");
//		userVo2.setGender("");
//		
//		resultActions = mockMvc.perform(post("/user/api/join")
//				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo2))).andDo(print());
//		
//		resultActions.andExpect(status().is4xxClientError())
//		.andExpect(jsonPath("$.result",is("fail")))
//		;
		
	}
}
