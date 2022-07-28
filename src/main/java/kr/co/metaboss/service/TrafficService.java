package kr.co.metaboss.service;

import kr.co.metaboss.dto.smm.Order;
import kr.co.metaboss.repository.OrderRepository;
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
public class TrafficService extends VendorService {

    public TrafficService(VendorRepository vendorRepository) {
        super(vendorRepository);
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
