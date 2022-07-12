package kr.co.trafficboss.tbadm.controller;

import kr.co.trafficboss.tbadm.dto.LoginDto;
import kr.co.trafficboss.tbadm.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login.do")
    public String signUp(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("index get request");
        return "/login";
    }

}
