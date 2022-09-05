package kr.co.metaboss.service.smm;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.repository.smm.TrafficOrderRepository;
import kr.co.metaboss.repository.smm.SmmVendorRepository;
import kr.co.metaboss.utils.Requests;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.VendorVO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class TrafficOrderService extends SmmVendorService {

    private TrafficOrderRepository trafficOrderRepository;

    public TrafficOrderService(SmmVendorRepository smmVendorRepository, TrafficOrderRepository trafficOrderRepository) {
        super(smmVendorRepository);
        this.trafficOrderRepository = trafficOrderRepository;
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
        return trafficOrderRepository.getOrder(search);
    }

    public String getStatus(String vendor, List<OrderVO> list) {
        VendorVO vendorVO = getVendor(vendor);
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
        map.put("pageSize", (Integer.parseInt(trafficOrderRepository.getOrderCount(search)) / search.getRecodeSize()) + 1);
        return map;
    }
}
