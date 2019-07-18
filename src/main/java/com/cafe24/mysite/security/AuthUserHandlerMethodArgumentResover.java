package com.cafe24.mysite.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserHandlerMethodArgumentResover implements HandlerMethodArgumentResolver {


	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Object principal = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			principal = authentication.getPrincipal();
		}
		
		if(principal == null || principal.getClass() == String.class) {
			return null;
		}
		
		
		
		return principal;
	}
	

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		System.out.println("!!!!!!!!!!!!!supportsParameter");
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser가 안 붙어 있음
		if(authUser == null) {
			return false;
		}
		
		//securityUser가 아닌 경우
		if(parameter.getParameterType().equals(SecurityUser.class)==false) {//클래스 객체들을 비교함
			return false;
		}
		
		
		return true;
	}

}
