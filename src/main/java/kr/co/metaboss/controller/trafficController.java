package kr.co.metaboss.controller;

import kr.co.metaboss.service.ProductService;
import kr.co.metaboss.service.VendorService;
import kr.co.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
public class trafficController {

    private final ProductService productService;
    private final VendorService vendorService;

    @GetMapping("/products")
    public ModelAndView product(HttpServletRequest req) {
        String vendor = req.getParameter("vendor") != null ? req.getParameter("vendor") : "JAP";
        ModelAndView mav = new ModelAndView("products");
        List<ProductVO> productList = productService.getProductByVendor(vendor);
//        System.out.println("list : " + list.toString());
        mav.addObject("productList",productList);
        return mav;
    }

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
        productService.updateProduct(vendor);
    }

}
