package kr.co.metaboss.service.traffic;

import kr.co.metaboss.repository.VendorRepository;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TrafficVendorService {

    private final VendorRepository vendorRepository;

    public VendorVO getVendor(String vendor) {
        return vendorRepository.getVendor(vendor);
    }

    public String getKey(String vendor) { return vendorRepository.getKey(vendor); }

    public List<String> getVendorByName() { return vendorRepository.getVendorByName(); }
}
