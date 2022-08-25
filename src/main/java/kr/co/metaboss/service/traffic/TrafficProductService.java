package kr.co.metaboss.service.traffic;

import kr.co.metaboss.repository.traffic.TrafficProductRepository;
import kr.co.metaboss.repository.traffic.TrafficVendorRepository;
import kr.co.metaboss.utils.JSONUtils;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.ProductVO;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Service
public class TrafficProductService {

    private final TrafficProductRepository trafficProductRepository;
    private final TrafficVendorRepository trafficVendorRepository;

    public List<ProductVO> getProductByVendor(String vendor) {
        return trafficProductRepository.getProductByVendor(vendor);
    }

    public int getTotalCountByVendor(String vendor) {
        int totalCount = trafficProductRepository.getTotalCountByVendor(vendor);
        log.info("totalCount :" + totalCount);
        int pageCount = (int) Math.floor(totalCount/20);
        log.info("pageCount :" + pageCount);
        return pageCount;
    }

    public void updateProduct(String vendor) {
        log.info("vendor : " + vendor);
        VendorVO vendorVO = trafficVendorRepository.getVendor(vendor);
        List<String> productServiceList = trafficProductRepository.getProductServices(vendor);
        JSONObject data = new JSONObject();
        data.put("key", vendorVO.getApiKey());
        data.put("action", "services");
        JSONArray response = new Requests(vendorVO.getUrl(), data).postAndArrayResponse();
        List<Map<String, Object>> responseArray = JSONUtils.getListMapFromJsonArray(response);
        for (Map<String, Object> stringObjectMap : responseArray) {
            stringObjectMap.put("vendor", vendor);
            if (productServiceList.contains(String.valueOf(stringObjectMap.get("service")))) trafficProductRepository.updateProduct(stringObjectMap);
            else trafficProductRepository.insertProduct(stringObjectMap);
        }
    }
}
