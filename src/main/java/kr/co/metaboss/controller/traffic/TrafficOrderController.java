package kr.co.metaboss.controller.traffic;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.service.traffic.TrafficAPIService;
import kr.co.metaboss.service.traffic.TrafficOrderService;
import kr.co.metaboss.service.traffic.TrafficVendorService;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
@Log4j2
public class TrafficOrderController {

    private final TrafficVendorService trafficVendorService;
    private final TrafficOrderService trafficOrderService;
    private final TrafficAPIService trafficAPIService;

    @GetMapping("/order")
    public ModelAndView order() {
        ModelAndView mav = new ModelAndView("order");
        List<String> vendors = trafficVendorService.getVendorByName();
        mav.addObject("vendors", vendors);
        return mav;
    }

//    @PostMapping("/order")
//    @ResponseBody
//    public List<ProductVO> order() {
//        List<OrderVO> list = trafficOrderService.get();
//        return mav;
//    }

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
