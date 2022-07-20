package kr.co.metaboss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index() { return "index"; }

    @GetMapping("/login")
    public String login() {return "login"; }

    @GetMapping("/charts")
    public String charts() {
        return "charts";
    }

    @GetMapping("/elements")
    public String elements() {
        return "elements";
    }

    @GetMapping("/icons")
    public String icons() {
        return "icons";
    }

    @GetMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    @GetMapping("/page-lockscreen")
    public String lockscreen() {
        return "page-lockscreen";
    }

    @GetMapping("/page-profile")
    public String profile() {
        return "page-profile";
    }

    @GetMapping("/panels")
    public String panels() {
        return "panels";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }

    @GetMapping("/typography")
    public String typography() {
        return "typography";
    }

}
