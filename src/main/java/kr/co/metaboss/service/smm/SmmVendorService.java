package kr.co.metaboss.service.smm;

import kr.co.metaboss.repository.smm.SmmVendorRepository;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SmmVendorService {

    private final SmmVendorRepository smmVendorRepository;

    public VendorVO getVendor(String vendor) {
        return smmVendorRepository.getVendor(vendor);
    }

    public String getKey(String vendor) { return smmVendorRepository.getKey(vendor); }

    public List<String> getVendorByName() { return smmVendorRepository.getVendorByName(); }
}
