package kr.co.trafficboss.metaboss.repository;

import kr.co.trafficboss.metaboss.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProductRepository {
    List<ProductVO> getProduct();
    List<ProductVO> getProductByVendor(String vendor);
    List<String> getProductServices(String vendor);
    int insertProduct(Map<String, Object> map);
    int updateProduct(Map<String, Object> map);
}
