package com.jhta.bonfire.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jhta.bonfire.service.BMembersService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired BMembersService service;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//������� id session�� �ֱ�
		String username=request.getParameter("username");
		request.getSession().setAttribute("id",username);
		request.getSession().setAttribute("role", service.getRole(username));
		//����� ���? ����?! �޾ƿͼ� �ֱ�!
		
		
		
		//request�� ������ ����������~?
//		
//		Map<String, String[]> map= request.getParameterMap();
//		StringBuilder sb = new StringBuilder();
//		sb.append("testfilter");
//		for(String key : map.keySet()) {
//		    sb.append("\nrequest param: "+ key);
//		    for(String value : map.get(key)) {
//		        sb.append("\tvalue: "+ value);
//		    }
//		}
//		Cookie[] cookies = request.getCookies();
//		if (cookies!=null) {
//		    for (Cookie cookie : cookies) {
//		        sb.append("\ncookie: "+cookie.getName());
//		        sb.append("\tvalue: "+cookie.getValue());
//		    }
//		}
//		Enumeration<String> attrs = request.getAttributeNames();
//		while(attrs.hasMoreElements()) {
//		    String attr = attrs.nextElement();
//		    sb.append("\nrequest Attribute : "+attr);
//		    sb.append("\tvalue: "+request.getAttribute(attr));
//		}
//		HttpSession session = request.getSession();
//		Enumeration<String> sattrs = session.getAttributeNames();
//		while(sattrs.hasMoreElements()) {
//		    String attr = sattrs.nextElement();
//		    sb.append("\nsession Attribute : "+attr);
//		    sb.append("\tvalue: "+session.getAttribute(attr));
//		}
//		System.out.println(sb.toString());
//		
		List<String> roleNames=new ArrayList<String>();	//�����̸��� ���� arraylist �׸�
		for(GrantedAuthority auth:authentication.getAuthorities()) {
			roleNames.add(auth.getAuthority());	//�ش� ȸ�� ���̵��� ���� ��������
		}
//		if(roleNames.contains("ROLE_ADMIN")) {
//			response.sendRedirect(request.getContextPath()+"/admin");
//		}else if(roleNames.contains("ROLE_MEMBER")) {
//			response.sendRedirect(request.getContextPath()+"/member/main");
//		}else {
//			response.sendRedirect(request.getContextPath()+"/");
//		}
		response.sendRedirect(request.getContextPath()+"/");
	}

}
