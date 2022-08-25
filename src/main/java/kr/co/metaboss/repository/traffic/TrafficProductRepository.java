package kr.co.metaboss.repository.traffic;

import kr.co.metaboss.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TrafficProductRepository {
    List<ProductVO> getProduct();
    List<ProductVO> getProductByVendor(String vendor);
    int getTotalCountByVendor(String vendor);
    List<String> getProductServices(String vendor);
    int insertProduct(Map<String, Object> map);
    int updateProduct(Map<String, Object> map);
}
