package com.monorella.srf.branch.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.monorella.srf.branch.dto.BranchOwner;

@Controller
public class LoginController {
	
	@Autowired LoginDao loginDao;
	
	//로그아웃 처리
	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	public String logout(){
		return "login/login_logout";
	}
	
	//로그인 폼
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginFrom(){ 
		return "login/login_form";
	}
	
	//로그인 처리
	@RequestMapping(value = "/login/loginpro", method = RequestMethod.POST)
	public String loginPro(BranchOwner branchOwner, HttpServletRequest request){
		//System.out.println("로그인 처리 요청");
		//System.out.println(branchOwner);
		BranchOwner owner = loginDao.loginBranchOwner(branchOwner);
		//System.out.println(owner);
		if(owner == null){
			System.out.println("아이디와 비번이 일치하지 않습니다.");
		}else{
			System.out.println("로그인 성공");
			request.getSession().setAttribute("branchOwner", owner);
		}

		return "dashboard/home";
	}
}
