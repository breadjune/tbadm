package kr.co.trafficboss.tbadm.service;

import kr.co.trafficboss.tbadm.dao.Member;
import kr.co.trafficboss.tbadm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("UserDetailsService invoked!");
        Member member = memberRepository.findByUserId(userId);
        System.out.println("member vo : " + member.toString());
        if (member == null) throw new UsernameNotFoundException("Not Found account.");
        return member;
    }

    public int addMember(Member member) {
        return memberRepository.insertMember(member);
    }
}
