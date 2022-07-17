package kr.co.trafficboss.metaboss.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class VendorVO {

    private int vendorCode;
    private String vendorName;
    private String url;
    private String apiKey;

}
