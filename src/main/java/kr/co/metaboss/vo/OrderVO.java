package kr.co.metaboss.vo;

import lombok.Getter;

@Getter
public class OrderVO {
    String orderId;
    String vendor;
    String orderAction;
    String service;
    String link;
    int quantity;
    int runs;
    int orderInterval;
    String regDate;
}
