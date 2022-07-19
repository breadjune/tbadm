package kr.co.metaboss.service;

import kr.co.metaboss.repository.ProductRepository;
import kr.co.metaboss.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductVO> getProductByVendor(String vendor) {
        return  productRepository.getProductByVendor(vendor);
    }

}
