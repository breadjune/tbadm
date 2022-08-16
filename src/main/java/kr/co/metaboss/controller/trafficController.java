package kr.co.metaboss.controller;

import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.service.OrderService;
import kr.co.metaboss.service.ProductService;
import kr.co.metaboss.service.TrafficService;
import kr.co.metaboss.service.VendorService;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
@Log4j2
public class trafficController {

    private final OrderService orderService;
    private final ProductService productService;
    private final VendorService vendorService;
    private final TrafficService trafficService;

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
    public ModelAndView order() {
        ModelAndView mav = new ModelAndView("order");
        List<String> vendors = vendorService.getVendorByName();
        mav.addObject("vendors", vendors);
//        mav.addObject("products", productService.getProductByVendor(vendors.get(0)));
        return mav;
    }

    @PostMapping("/order")
    @ResponseBody
    public List<ProductVO> order(String vendor) {
       return productService.getProductByVendor(vendor);
    }

    @PostMapping("/addOrder")
    public String addOrder(Order order) {
        log.info("addOrder : " + order);
        trafficService.addOrder(order);
        return "redirect:/traffic/order";
    }

    @GetMapping("/orderList")
    public ModelAndView orderList() {
        ModelAndView mav = new ModelAndView("orderList");
        List<String> vendors = vendorService.getVendorByName();
        mav.addObject("vendors", vendors);
        return mav;
    }

    @PostMapping("/orderList")
    @ResponseBody
    public Map<String, Object> orderList(@RequestParam(required = false) String vendor,
                                  @RequestParam(defaultValue = "") String column,
                                  @RequestParam(defaultValue = "") String value) {
        return orderService.getOrderList(vendor, column, value);
    }

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

    @PostMapping("/balance")
    @ResponseBody
    public String getBalance(@RequestParam(defaultValue = "KINGS") String vendor) {
        return trafficService.getBalance(vendor);
    }

}
