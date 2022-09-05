package kr.co.metaboss.repository.smm;

import kr.co.metaboss.dto.common.Search;
import kr.co.metaboss.dto.traffic.Order;
import kr.co.metaboss.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface YoutubeOrderRepository {
    List<OrderVO> getOrder(Search search);
    String getOrderCount(Search search);
    int insertOrder(Order order);
}
