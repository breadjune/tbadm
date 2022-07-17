package kr.co.trafficboss.metaboss.repository;

import kr.co.trafficboss.metaboss.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {
    Member findByUserId(String userId);
    int insertMember(Member member);
}
