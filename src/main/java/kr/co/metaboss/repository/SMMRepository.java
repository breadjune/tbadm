package kr.co.metaboss.repository;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.ProductVO;
import kr.co.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SMMRepository {
    List<VendorVO> getVendors();
    VendorVO getVendors(String vendor);

    //    List<VendorVO> getVendorList();
//    VendorVO getVendor(String vendor);
//    String getKey(String vendor);
//    List<String> getVendorByName();
    List<ProductVO> getProduct();

    List<ProductVO> getTrafficProducts(String vendor);
    int getTrafficProductsSize(String vendor);

    List<ProductVO> getYoutubeProducts(String vendor);
    int getYoutubeProductsSize(String vendor);

    List<String> getProductServices(String vendor);

    int insertProduct(Map<String, Object> map);
    int updateProduct(Map<String, Object> map);

    List<OrderVO> getOrder(Search search);
    String getOrderCount(Search search);
    int insertOrder(Order order);
}
