package kr.co.metaboss.dto.smm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    String vendor;
    String action;
    String service;
    String link;
    String quantity;
    String run;
    String interval;
}
