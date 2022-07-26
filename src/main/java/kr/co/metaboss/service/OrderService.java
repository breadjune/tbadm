package kr.co.metaboss.service;

import kr.co.metaboss.dto.smm.Order;
import kr.co.metaboss.repository.OrderRepository;
import kr.co.metaboss.repository.ProductRepository;
import kr.co.metaboss.repository.VendorRepository;
import kr.co.metaboss.utils.JSONUtils;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OrderService extends VendorService {

    private OrderRepository orderRepository;

    public OrderService(VendorRepository vendorRepository, OrderRepository orderRepository) {
        super(vendorRepository);
        this.orderRepository = orderRepository;
    }

    public int addOrder(Order order) {
        JSONObject json = JSONUtils.convertDtoToJsonObject(order);
        VendorVO vendorVO = getVendor(order.getVendor());
        json.put("key", vendorVO.getApiKey());
        log.info("json : " + json);
//        Requests requests = new Requests(vendorVO.getUrl(), json);
//        JSONObject response = requests.postAndObjectResponse();
//        log.info("response : " + response);
        orderRepository.addOrder(order);
        return 1;
    }

}
