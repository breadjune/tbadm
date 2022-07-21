package kr.co.metaboss.controller;

import kr.co.metaboss.service.ProductService;
import kr.co.metaboss.service.VendorService;
import kr.co.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
@Log4j2
public class trafficController {

    private final ProductService productService;
    private final VendorService vendorService;

    @RequestMapping("/products")
    public ModelAndView getProducts(@RequestParam(defaultValue = "JAP") String vendor,
                                    @RequestParam(defaultValue = "0") String index) {
        ModelAndView mav = new ModelAndView("products");
        mav.addObject("list", productService.getProductByVendor(vendor, index));
        mav.addObject("count", productService.getTotalCountByVendor(vendor));
        return mav;
    }

    @PostMapping("/products")
    @ResponseBody
    public ModelAndView postProducts(@RequestParam String vendor,
                                     @RequestParam String index) {
        List<ProductVO> productList = productService.getProductByVendor(vendor, index);
        log.info("");
        ModelAndView mav = new ModelAndView("products :: #tables");
        mav.addObject("list", productList);
        return mav;
    }

    @GetMapping("/order")
    public String order() { return "order"; }

    @GetMapping("/orderList")
    public String orderList() { return "orderList"; }

    @PostMapping("/getProduct")
    @ResponseBody
    public List<ProductVO> getProduct(@RequestParam String vendor,
                                      @RequestParam(defaultValue = "0") String index) {
        return productService.getProductByVendor(vendor, index);
    }

    @GetMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        productService.updateProduct(vendor);
    }

}
