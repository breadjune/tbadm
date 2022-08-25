package kr.co.metaboss.controller.traffic;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.service.traffic.TrafficOrderService;
import kr.co.metaboss.service.traffic.TrafficProductService;
import kr.co.metaboss.service.traffic.TrafficAPIService;
import kr.co.metaboss.service.traffic.TrafficVendorService;
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
public class TrafficController {

    private final TrafficProductService trafficProductService;
    private final TrafficVendorService trafficVendorService;
    private final TrafficOrderService trafficOrderService;
    private final TrafficAPIService trafficAPIService;

    @GetMapping("/products")
    public ModelAndView getProducts(@RequestParam(defaultValue = "KINGS") String vendor) {
        ModelAndView mav = new ModelAndView("products");
        mav.addObject("list", trafficProductService.getProductByVendor(vendor));
        mav.addObject("count", trafficProductService.getTotalCountByVendor(vendor));
        return mav;
    }

    @PostMapping("/products")
    @ResponseBody
    public ModelAndView postProducts(@RequestParam String vendor) {
        List<ProductVO> list = trafficProductService.getProductByVendor(vendor);
        ModelAndView mav = new ModelAndView("products :: #tables");
        mav.addObject("list", list);
        return mav;
    }

    @PostMapping("/getProduct")
    @ResponseBody
    public List<ProductVO> getProduct(@RequestParam String vendor,
                                      @RequestParam(defaultValue = "0") String index) {
        return trafficProductService.getProductByVendor(vendor);
    }

    @GetMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        trafficProductService.updateProduct(vendor);
    }

    @PostMapping("/balance")
    @ResponseBody
    public String getBalance(@RequestParam(defaultValue = "KINGS") String vendor) {
        return trafficAPIService.getBalance(vendor);
    }

    @GetMapping("/order")
    public ModelAndView order() {
        ModelAndView mav = new ModelAndView("order");
        List<String> vendors = trafficVendorService.getVendorByName();
        mav.addObject("vendors", vendors);
        return mav;
    }

    @PostMapping("/addOrder")
    public String addOrder(Order order) {
        trafficAPIService.addOrder(order);
        return "redirect:/traffic/order";
    }

    @GetMapping("/orderList")
    public ModelAndView orderList() {
        ModelAndView mav = new ModelAndView("orderList");
        List<String> vendors = trafficVendorService.getVendorByName();
        mav.addObject("vendors", vendors);
        return mav;
    }

    @PostMapping("/orderList")
    @ResponseBody
    public Map<String, Object> orderList(Search search) {
        return trafficOrderService.getOrderList(search);
    }

    @PostMapping("/pageSize")
    @ResponseBody
    public Map<String, Integer> pageSize(Search search) {
        return trafficOrderService.getPageSize(search);
    }
}
