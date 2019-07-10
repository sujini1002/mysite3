package com.cafe24.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mysite.vo.GuestBookVo;

@Service
public class GuestBookService2 {

	public List<GuestBookVo> getContentsList(long l) {
		//서비스테스트 할때  이부분 고치기
		GuestBookVo first = new GuestBookVo(1L,"강수진","1234","test1","2019-07-10 10:15");
		GuestBookVo second = new GuestBookVo(2L,"강수진","1234","test2","2019-07-10 10:15");
		
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		list.add(first);
		list.add(second);
		return list;
	}

	public GuestBookVo addContents(GuestBookVo guestbookVo) {
		guestbookVo.setNo(10L);
		guestbookVo.setReg_date("2019-07-10 11:19");
		return guestbookVo;
	}

	public Long deleteContents(Long no, String password) {
		
		return no;
	}

}
