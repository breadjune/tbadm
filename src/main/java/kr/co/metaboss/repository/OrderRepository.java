package kr.co.metaboss.repository;

import kr.co.metaboss.dto.smm.Order;
import kr.co.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderRepository {
    public int addOrder(Order order);
}
