package kr.co.metaboss.service;

import kr.co.metaboss.repository.VendorRepository;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    private VendorVO getVendor(String vendor) {
        return vendorRepository.getVendor(vendor);
    }
}
