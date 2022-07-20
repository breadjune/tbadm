package kr.co.metaboss.service;

import kr.co.metaboss.repository.ProductRepository;
import kr.co.metaboss.repository.VendorRepository;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;

    public List<ProductVO> getProductByVendor(String vendor) {
        return  productRepository.getProductByVendor(vendor);
    }

    public void updateProduct(String vendor) {
        VendorVO vendorVO = vendorRepository.getVendor(vendor);
        List<String> productServiceList = productRepository.getProductServices(vendor);
        JSONObject data = new JSONObject();
        data.put("key", vendorVO.getApiKey());
        data.put("action", "services");
        JSONArray response = new Requests(vendorVO.getUrl(), data).postAndArrayResponse();
        List<Map<String, Object>> responseArray = JSONUtils.getListMapFromJsonArray(response);
        for (Map<String, Object> stringObjectMap : responseArray) {
            if (productServiceList.contains(String.valueOf(stringObjectMap.get("service")))) productRepository.updateProduct(stringObjectMap);
            else productRepository.insertProduct(stringObjectMap);
        }
    }
}
