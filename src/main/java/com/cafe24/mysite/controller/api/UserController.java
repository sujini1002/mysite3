package com.cafe24.mysite.controller.api;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("userAPIController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	//@ResponseBody
	@ApiOperation(value="이메밀 존재 여부")
	@ApiImplicitParams({
		@ApiImplicitParam(name="email",value="이메일주소",required=true, dataType="string",defaultValue="")
	})
	@RequestMapping(value="/checkemail",method = RequestMethod.GET)
	public JSONResult checkEmail(@RequestParam(value="email",required=true,defaultValue = "")String email) {
		Boolean exist = userService.existEmail(email);
		return JSONResult.success(exist);
	}
	
	@PostMapping(value="/join")
	public ResponseEntity<JSONResult> join(@RequestBody @Valid UserVo userVo , BindingResult result) {
		
		//Valid 체크가 틀릴 시, join form으로 넘김
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println("error!!!!!!!!!!!"+error);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(null),HttpStatus.OK);
	}
	
	
	@PostMapping(value="/login")
	public ResponseEntity<JSONResult> login(@RequestBody UserVo userVo) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<UserVo>> validatorResults = validator.validateProperty(userVo,"email");//Validator 생성
		
		if(validatorResults.isEmpty()==false) {
			//오류 발생
			for(ConstraintViolation<UserVo> validatorResult : validatorResults) {
//				String message  = validatorResult.getMessage();
				String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale()); //한글 메세지를 위한 설정
				JSONResult result = JSONResult.fail(message);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(null),HttpStatus.OK);
	}
}
