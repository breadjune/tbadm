package kr.co.metaboss.controller;

import kr.co.metaboss.dto.smm.Order;
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

    @GetMapping("/products")
    public ModelAndView getProducts(@RequestParam(defaultValue = "KINGS") String vendor) {
        ModelAndView mav = new ModelAndView("products");
        mav.addObject("list", productService.getProductByVendor(vendor));
        mav.addObject("count", productService.getTotalCountByVendor(vendor));
        return mav;
    }

    @PostMapping("/products")
    @ResponseBody
    public ModelAndView postProducts(@RequestParam String vendor) {
        List<ProductVO> list = productService.getProductByVendor(vendor);
        ModelAndView mav = new ModelAndView("products :: #tables");
        mav.addObject("list", list);
        return mav;
    }

    @GetMapping("/order")
    public ModelAndView order(@RequestParam(defaultValue = "KINGS") String vendor) {
        ModelAndView mav = new ModelAndView("order");
        mav.addObject("vendors", vendorService.getVendorByName());
        mav.addObject("products", productService.getProductByVendor(vendor));
        return mav;
    }

    @PostMapping("/order")
    public void order(Order order) {
        log.info("post order : " + order.toString());
    }

    @GetMapping("/orderList")
    public String orderList() { return "orderList"; }

    @PostMapping("/getProduct")
    @ResponseBody
    public List<ProductVO> getProduct(@RequestParam String vendor,
                                      @RequestParam(defaultValue = "0") String index) {
        return productService.getProductByVendor(vendor);
    }

    @GetMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        productService.updateProduct(vendor);
    }

}
