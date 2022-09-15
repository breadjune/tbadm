package kr.co.metaboss.service;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.repository.SMMRepository;
import kr.co.metaboss.utils.JSONUtils;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.ProductVO;
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
public class SMMService {

    private final SMMRepository smmRepository;

    public SMMService(SMMRepository smmRepository) {
        this.smmRepository = smmRepository;
    }

    /**
     * 잔여 금액 조회
     * @param vendor
     * @return
     */
    public String getBalance(String vendor) {
        JSONObject json = new JSONObject();
        VendorVO vendorVO = smmRepository.getVendors(vendor);
        json.put("key", vendorVO.getApiKey());
        json.put("action", "balance");
        Requests requests = new Requests(vendorVO.getUrl(), json);
        JSONObject response = requests.postAndObjectResponse();
        return response.getString("balance");
    }

    public VendorVO getVendors(String vendor) { return smmRepository.getVendors(vendor); }

    public List<VendorVO> getVendors() { return smmRepository.getVendors(); }

    public List<ProductVO> getTrafficProducts(String vendor) {
        return smmRepository.getTrafficProducts(vendor);
    }

    public int getTrafficProductsSize(String vendor) { return (int) Math.floor(smmRepository.getTrafficProductsSize(vendor)/20); }

    public List<ProductVO> getYoutubeProducts(String vendor) { return smmRepository.getYoutubeProducts(vendor); }

    public int getYoutubeProductsSize(String vendor) { return (int) Math.floor(smmRepository.getYoutubeProductsSize(vendor)/20); }

    public void updateProduct(String vendor) {
        VendorVO vendorVO = smmRepository.getVendors(vendor);
        List<String> productServiceList = smmRepository.getProductServices(vendor);
        JSONObject data = new JSONObject();
        data.put("key", vendorVO.getApiKey());
        data.put("action", "services");
        JSONArray response = new Requests(vendorVO.getUrl(), data).postAndArrayResponse();
        List<Map<String, Object>> responseArray = JSONUtils.getListMapFromJsonArray(response);
        for (Map<String, Object> stringObjectMap : responseArray) {
            stringObjectMap.put("vendor", vendor);
            if (productServiceList.contains(String.valueOf(stringObjectMap.get("service")))) smmRepository.updateProduct(stringObjectMap);
            else smmRepository.insertProduct(stringObjectMap);
        }
    }

    /**
     * 주문 요청
     * @param order
     * @return
     */
    public int addOrder(Order order) {
        if (order.getKeyword() != null) order.setLink(order.getLink()+":"+order.getKeyword());
        JSONObject json = JSONUtils.convertDtoToJsonObject(order);
        VendorVO vendorVO = getVendors(order.getVendor());
        json.remove("vendor");
        json.put("key", vendorVO.getApiKey());
        Requests requests = new Requests(vendorVO.getUrl(), json);
        JSONObject response = requests.postAndObjectResponse();
        order.setOrderId(String.valueOf(response.getInt("order")));
        order.setOrderId("test");
        return smmRepository.insertOrder(order);
//        return 1;
    }

    public Map<String, Object> getOrderList(Search search) {
        Map<String, Object> map = new HashMap<>();
        List<OrderVO> list = this.getList(search);
        map.put("list", list);
        map.put("search", search);
        map.put("status", getStatus(search.getVendor(), list));
        return map;
    }

    public List<OrderVO> getList(Search search) {
        return smmRepository.getOrder(search);
    }

    public String getStatus(String vendor, List<OrderVO> list) {
        VendorVO vendorVO = smmRepository.getVendors(vendor);
        List<String> idList = new ArrayList<>();
        for (int i=0; i<list.size(); i++) idList.add(list.get(i).getOrderId());
        String ids = String.join(",", idList);
        JSONObject json = new JSONObject();
        json.put("key", vendorVO.getApiKey());
        json.put("action", "status");
        json.put("orders", ids);
        Requests requests = new Requests(vendorVO.getUrl(), json);
        return requests.post();
    }

    public Map<String, Integer> getPageSize(Search search) {
        Map<String, Integer> map = new HashMap<>();
        map.put("currentPage", search.getPage());
        map.put("pageSize", (Integer.parseInt(smmRepository.getOrderCount(search)) / search.getRecodeSize()) + 1);
        return map;
    }
}
