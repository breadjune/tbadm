package kr.co.metaboss.controller.smm;

import kr.co.metaboss.service.smm.SmmAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/smm")
@Log4j2
public class SmmController {

    private final SmmAPIService trafficAPIService;

    @PostMapping("/balance")
    @ResponseBody
    public String getBalance(@RequestParam(defaultValue = "KINGS") String vendor) {
        return trafficAPIService.getBalance(vendor);
    }

}
