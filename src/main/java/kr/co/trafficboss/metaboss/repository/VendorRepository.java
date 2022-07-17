package kr.co.trafficboss.metaboss.repository;

import kr.co.trafficboss.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VendorRepository {
    List<VendorVO> getVendorList();
    VendorVO getVendor(String vendorName);
}
