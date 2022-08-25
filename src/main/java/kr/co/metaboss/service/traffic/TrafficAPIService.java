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
        JSONObject json = JSONUtils.convertDtoToJsonObject(order);
        VendorVO vendorVO = getVendor(order.getVendor());
        json.remove("vendor");
        json.put("key", vendorVO.getApiKey());
        Requests requests = new Requests(vendorVO.getUrl(), json);
        JSONObject response = requests.postAndObjectResponse();
        order.setOrderId(String.valueOf(response.getInt("order")));
        //[TODO] 임시 주문 번호 생성 (개발 완료 후 삭제 필요!!)
//        order.setOrderId("329888060");
        return trafficOrderRepository.insertOrder(order);
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
