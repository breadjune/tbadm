package kr.co.trafficboss.tbadm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/dashboard")
    public String index() { return "index"; }

    @RequestMapping("/order")
    public String order() { return "order"; }

    @RequestMapping("/orderList")
    public String orderList() { return "orderList"; }

    @RequestMapping("/charts")
    public String charts(Model model) {
        return "charts";
    }

    @RequestMapping("/elements")
    public String elements(Model model) {
        return "elements";
    }

    @RequestMapping("/icons")
    public String icons(Model model) {
        return "icons";
    }

    @RequestMapping("/notifications")
    public String notifications(Model model) {
        return "notifications";
    }

    @RequestMapping("/page-lockscreen")
    public String lockscreen(Model model) {
        return "page-lockscreen";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        System.out.println("로그인 페이지 이동!");
        return "login";
    }

    @RequestMapping("/page-profile")
    public String profile(Model model) {
        return "page-profile";
    }
    @RequestMapping("/panels")
    public String panels(Model model) {
        return "panels";
    }

    @RequestMapping("/tables")
    public String tables(Model model) {
        return "tables";
    }

    @RequestMapping("/typography")
    public String typography(Model model) {
        return "typography";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("testText", "hello world!");
        return "test";
    }

}
