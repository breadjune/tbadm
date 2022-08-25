package kr.co.metaboss.service.traffic;

import kr.co.metaboss.repository.traffic.TrafficVendorRepository;
import kr.co.metaboss.vo.VendorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TrafficVendorService {

    private final TrafficVendorRepository trafficVendorRepository;

    public VendorVO getVendor(String vendor) {
        return trafficVendorRepository.getVendor(vendor);
    }

    public String getKey(String vendor) { return trafficVendorRepository.getKey(vendor); }

    public List<String> getVendorByName() { return trafficVendorRepository.getVendorByName(); }
}
