package kr.co.trafficboss.metaboss.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ProductVO {

    private int no;
    private String vendor;
    private String service;
    private String productName;
    private String productType;
    private String category;
    private String rate;
    private int minOrder;
    private int maxOrder;
    private String refill;
    private String dripfeed;
    private String cancel;

}
