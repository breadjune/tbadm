package kr.co.metaboss.service;

import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.repository.OrderRepository;
import kr.co.metaboss.repository.VendorRepository;
import kr.co.metaboss.utils.JSONUtils;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.VendorVO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class OrderService extends VendorService {

    private OrderRepository orderRepository;

    public OrderService(VendorRepository vendorRepository, OrderRepository orderRepository) {
        super(vendorRepository);
        this.orderRepository = orderRepository;
    }

    public Map<String, Object> getOrderList(String vendor, String column, String value) {
        VendorVO vendorVO = getVendor(vendor);
        List<OrderVO> list = orderRepository.getOrder(vendor, column, value);
        List<String> idList = new ArrayList<>();
        for (int i=0; i<list.size(); i++) idList.add(list.get(i).getOrderId());
        String ids = String.join(",", idList);
        JSONObject json = new JSONObject();
        json.put("key", vendorVO.getApiKey());
        json.put("action", "status");
        json.put("orders", ids);
        Requests requests = new Requests(vendorVO.getUrl(), json);
        JSONObject response = requests.postAndObjectResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("response", response.toString());
        return map;
    }

}
