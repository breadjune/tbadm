package kr.co.trafficboss.tbadm.repository;

import kr.co.trafficboss.tbadm.dao.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {
    Member findByUserId(String userId);
    int insertMember(Member member);
}
