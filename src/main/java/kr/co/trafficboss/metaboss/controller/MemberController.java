package kr.co.trafficboss.metaboss.controller;

import kr.co.trafficboss.metaboss.dto.Member;
import kr.co.trafficboss.metaboss.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login.do")
    public String signIn(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("index get request");
        return "index";
    }

    @PostMapping("/signup.do")
    @ResponseBody
    public Member signUp(@RequestBody Member member) {
        System.out.println("member : " + member.toString());
        memberService.addMember(member);
        return member;
    }

}
