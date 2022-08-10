package kr.co.metaboss.dto.traffic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    String vendor;
    String action;
    String link;
    String orderId;
    String service;
    int quantity;
    int runs;
    int interval;
}
