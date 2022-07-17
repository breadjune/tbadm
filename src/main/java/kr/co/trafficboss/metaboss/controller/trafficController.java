package kr.co.trafficboss.metaboss.controller;

import kr.co.trafficboss.metaboss.service.ProductService;
import kr.co.trafficboss.metaboss.service.VendorService;
import kr.co.trafficboss.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
public class trafficController {

    private final ProductService productService;
    private final VendorService vendorService;

    @GetMapping("/order")
    public String order() { return "order"; }

    @GetMapping("/orderList")
    public String orderList() { return "orderList"; }

    @PostMapping("/getProduct")
    @ResponseBody
    public List<ProductVO> getProduct(@RequestParam String vendor) {
        return productService.getProductByVendor(vendor);
    }

    @PostMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        vendorService.updateProduct(vendor);
    }
}
