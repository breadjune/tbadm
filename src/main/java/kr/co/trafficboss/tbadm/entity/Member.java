package kr.co.trafficboss.tbadm.entity;

import lombok.*;
import javax.persistence.*;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String userId;
    private String userPw;
    private String userLevel;

}
