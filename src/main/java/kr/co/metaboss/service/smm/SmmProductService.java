package kr.co.metaboss.service.smm;

import kr.co.metaboss.repository.smm.SmmProductRepository;
import kr.co.metaboss.repository.smm.SmmVendorRepository;
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
public class SmmProductService {

    private final SmmProductRepository smmProductRepository;
    private final SmmVendorRepository smmVendorRepository;

    public List<ProductVO> getTrafficProducts(String vendor) {
        return smmProductRepository.getTrafficProducts(vendor);
    }

    public int getTrafficProductsSize(String vendor) {
        int totalCount = smmProductRepository.getTrafficProductsSize(vendor);
        int pageCount = (int) Math.floor(totalCount/20);
        return pageCount;
    }

    public List<ProductVO> getYoutubeProducts(String vendor) {
        return smmProductRepository.getTrafficProducts(vendor);
    }

    public int getYoutubeProductsSize(String vendor) {
        int totalCount = smmProductRepository.getTrafficProductsSize(vendor);
        int pageCount = (int) Math.floor(totalCount/20);
        return pageCount;
    }

    public void updateProduct(String vendor) {
        VendorVO vendorVO = smmVendorRepository.getVendor(vendor);
        List<String> productServiceList = smmProductRepository.getProductServices(vendor);
        JSONObject data = new JSONObject();
        data.put("key", vendorVO.getApiKey());
        data.put("action", "services");
        JSONArray response = new Requests(vendorVO.getUrl(), data).postAndArrayResponse();
        List<Map<String, Object>> responseArray = JSONUtils.getListMapFromJsonArray(response);
        for (Map<String, Object> stringObjectMap : responseArray) {
            stringObjectMap.put("vendor", vendor);
            if (productServiceList.contains(String.valueOf(stringObjectMap.get("service")))) smmProductRepository.updateProduct(stringObjectMap);
            else smmProductRepository.insertProduct(stringObjectMap);
        }
    }
}
