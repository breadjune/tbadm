package kr.co.trafficboss.tbadm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String userId;
    private String userPw;
    private String userLevel;
}
