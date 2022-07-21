package kr.co.metaboss.repository;

import kr.co.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VendorRepository {
    List<VendorVO> getVendorList();
    VendorVO getVendor(String vendor);
}
