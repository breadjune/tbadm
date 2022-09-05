package kr.co.metaboss.repository.smm;

import kr.co.metaboss.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SmmVendorRepository {
    List<VendorVO> getVendorList();
    VendorVO getVendor(String vendor);
    String getKey(String vendor);
    List<String> getVendorByName();
}
