package kr.co.metaboss.repository.smm;

import kr.co.metaboss.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SmmProductRepository {
    List<ProductVO> getProduct();

    List<ProductVO> getTrafficProducts(String vendor);
    int getTrafficProductsSize(String vendor);

    List<ProductVO> getYoutubeProducts(String vendor);
    int getYoutubeProductsSize(String vendor);

    List<String> getProductServices(String vendor);

    int insertProduct(Map<String, Object> map);
    int updateProduct(Map<String, Object> map);
}
