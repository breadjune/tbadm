package kr.co.trafficboss.metaboss.controller;

import kr.co.trafficboss.metaboss.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class APIController {

    private final VendorService vendorService;

    @PostMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        vendorService.updateProduct(vendor);
    }

}
