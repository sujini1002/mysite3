package com.cafe24.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.vo.GuestBookVo;


@RestController("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestBookController {
	
	@RequestMapping(value="/list/{no}",method = RequestMethod.GET)
	public JSONResult list(@PathVariable(value="no")int no) {
		
		return JSONResult.success(null);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public JSONResult add(@ModelAttribute GuestBookVo guestbookVo) {
		
		Map<String,String> data = new HashMap<String, String>();
		data.put("name", guestbookVo.getName());
		data.put("contents", guestbookVo.getContents());
		return JSONResult.success(data);
	}
}
