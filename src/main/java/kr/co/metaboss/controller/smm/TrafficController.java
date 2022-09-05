package kr.co.metaboss.controller.smm;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.service.smm.TrafficOrderService;
import kr.co.metaboss.service.smm.SmmProductService;
import kr.co.metaboss.service.smm.SmmAPIService;
import kr.co.metaboss.service.smm.SmmVendorService;
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

    private final SmmProductService smmProductService;
    private final SmmVendorService smmVendorService;
    private final TrafficOrderService trafficOrderService;
    private final SmmAPIService trafficAPIService;

    @GetMapping("/products")
    public ModelAndView getProducts(@RequestParam(defaultValue = "KINGS") String vendor) {
        ModelAndView mav = new ModelAndView("products");
        mav.addObject("list", smmProductService.getTrafficProducts(vendor));
        mav.addObject("count", smmProductService.getTrafficProductsSize(vendor));
        return mav;
    }

    @PostMapping("/products")
    @ResponseBody
    public ModelAndView postProducts(@RequestParam String vendor) {
        List<ProductVO> list = smmProductService.getTrafficProducts(vendor);
        ModelAndView mav = new ModelAndView("products :: #tables");
        mav.addObject("list", list);
        return mav;
    }

    @PostMapping("/getProduct")
    @ResponseBody
    public List<ProductVO> getProduct(@RequestParam String vendor,
                                      @RequestParam(defaultValue = "0") String index) {
        return smmProductService.getTrafficProducts(vendor);
    }

    @GetMapping("/updateProduct")
    @ResponseBody
    public void updateProduct(String vendor) {
        smmProductService.updateProduct(vendor);
    }

    @GetMapping("/order")
    public ModelAndView order() {
        ModelAndView mav = new ModelAndView("order");
        List<String> vendors = smmVendorService.getVendorByName();
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
        List<String> vendors = smmVendorService.getVendorByName();
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
