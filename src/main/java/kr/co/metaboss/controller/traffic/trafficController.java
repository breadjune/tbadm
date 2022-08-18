package kr.co.metaboss.controller.traffic;

import kr.co.metaboss.service.traffic.TrafficProductService;
import kr.co.metaboss.service.traffic.TrafficAPIService;
import kr.co.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
@Log4j2
public class trafficController {

    private final TrafficProductService trafficProductService;
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

}
