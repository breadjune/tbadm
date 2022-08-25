package kr.co.metaboss.repository.traffic;

import kr.co.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TrafficVendorRepository {
    List<VendorVO> getVendorList();
    VendorVO getVendor(String vendor);
    String getKey(String vendor);
    List<String> getVendorByName();
}
