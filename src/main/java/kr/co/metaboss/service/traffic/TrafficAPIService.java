package kr.co.metaboss.service.traffic;

import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.repository.traffic.TrafficOrderRepository;
import kr.co.metaboss.repository.traffic.TrafficVendorRepository;
import kr.co.metaboss.utils.JSONUtils;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.VendorVO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TrafficAPIService extends TrafficVendorService {

    private final TrafficOrderRepository trafficOrderRepository;

    public TrafficAPIService(TrafficVendorRepository trafficVendorRepository, TrafficOrderRepository trafficOrderRepository) {
        super(trafficVendorRepository);
        this.trafficOrderRepository = trafficOrderRepository;
    }

    public int addOrder(Order order) {
        log.info("addOrder : " + order.toString());
        if (order.getKeyword() != null) {
            order.setLink(order.getLink()+":"+order.getKeyword());
        }
        log.info("replaceOrder : " + order.toString());
        return 1;

//        JSONObject json = JSONUtils.convertDtoToJsonObject(order);
//        VendorVO vendorVO = getVendor(order.getVendor());
//        json.remove("vendor");
//        json.put("key", vendorVO.getApiKey());
//        Requests requests = new Requests(vendorVO.getUrl(), json);
//        JSONObject response = requests.postAndObjectResponse();
//        order.setOrderId(String.valueOf(response.getInt("order")));
//        return trafficOrderRepository.insertOrder(order);
    }

    public String getBalance(String vendor) {
        JSONObject json = new JSONObject();
        VendorVO vendorVO = getVendor(vendor);
        json.put("key", vendorVO.getApiKey());
        json.put("action", "balance");
        Requests requests = new Requests(vendorVO.getUrl(), json);
        JSONObject response = requests.postAndObjectResponse();
        log.info("response : " + response);
        return response.getString("balance");
    }

}
