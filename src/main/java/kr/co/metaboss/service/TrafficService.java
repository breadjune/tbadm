package kr.co.metaboss.service;

import kr.co.metaboss.dto.smm.Order;
import kr.co.metaboss.repository.VendorRepository;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TrafficService {

    private final VendorRepository vendorRepository;

//    public int addOrder(Order order) {
//        VendorVO vendorVO = vendorRepository.getVendor(order.getVendor());
//        JSONObject result = new Requests()
//    }

}
