package kr.co.trafficboss.tbadm.repository;

import kr.co.trafficboss.tbadm.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUserId(String userId);
}
