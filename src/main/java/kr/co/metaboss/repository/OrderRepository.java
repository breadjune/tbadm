package kr.co.metaboss.repository;

import kr.co.metaboss.dto.smm.Order;
import kr.co.metaboss.vo.OrderVO;
import kr.co.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderRepository {
    List<OrderVO> getOrder(String column, String value);
    int insertOrder(Order order);
}
