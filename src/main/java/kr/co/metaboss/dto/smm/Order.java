package kr.co.metaboss.dto.smm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    String vendor;
    String action;
    String service;
    String link;
    String quantity;
    String runs;
    String interval;
}
