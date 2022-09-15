package kr.co.metaboss.controller;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.service.SMMService;
import kr.co.metaboss.vo.ProductVO;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/smm")
@Log4j2
public class SMMController {

    private final SMMService smmService;

    @PostMapping("/balance")
    @ResponseBody
    public String getBalance(@RequestParam(defaultValue = "KINGS") String vendor) {
        return smmService.getBalance(vendor);
    }

    @GetMapping("/traffic/products")
    public ModelAndView getProducts(@RequestParam(defaultValue = "KINGS") String vendor) {
        ModelAndView mav = new ModelAndView("traffic/products");
        mav.addObject("list", smmService.getTrafficProducts(vendor));
        mav.addObject("count", smmService.getTrafficProductsSize(vendor));
        return mav;
    }

    @PostMapping("/traffic/products")
    @ResponseBody
    public ModelAndView postProducts(@RequestParam String vendor) {
        ModelAndView mav = new ModelAndView("traffic/products :: #tables");
        mav.addObject("list", smmService.getTrafficProducts(vendor));
        return mav;
    }

    @PostMapping("/traffic/getProducts")
    @ResponseBody
    public List<ProductVO> getProduct(@RequestParam String vendor, @RequestParam(defaultValue = "0") String index) {
        return smmService.getTrafficProducts(vendor);
    }

    @GetMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        smmService.updateProduct(vendor);
    }

    @GetMapping("/traffic/order")
    public ModelAndView order() {
        ModelAndView mav = new ModelAndView("traffic/order");
        List<VendorVO> vendors = smmService.getVendors();
        mav.addObject("vendors", vendors);
        return mav;
    }

    @PostMapping("/traffic/order")
    @ResponseBody
    public void addOrder(Order order) {
        smmService.addOrder(order);
//        return "redirect:/traffic/order";
    }

    @GetMapping("/traffic/list")
    public ModelAndView orderList() {
        ModelAndView mav = new ModelAndView("traffic/list");
        List<VendorVO> vendors = smmService.getVendors();
        mav.addObject("vendors", vendors);
        return mav;
    }

    @PostMapping("/traffic/list")
    @ResponseBody
    public Map<String, Object> orderList(Search search) {
        return smmService.getOrderList(search);
    }

    @PostMapping("/traffic/size")
    @ResponseBody
    public Map<String, Integer> pageSize(Search search) {
        return smmService.getPageSize(search);
    }
}
