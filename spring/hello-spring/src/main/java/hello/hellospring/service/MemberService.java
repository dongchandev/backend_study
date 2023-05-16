package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//스프링이 올라올 때 스프링 컨테이너에 이 애노테이션의 프로그램을 올려줌
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //MemberService는 MemberRepository가 필요
    //Autowired를 사용함으로써 스프링 컨테이너에 MemberRepository의 구현체를 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 X
        /*Optional 을 쓰면 그 안에 멤버  변수가 있는것과 같은 효과*/
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent/*값이 있으면 동작*/(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");/*예외처리 / throw:강제예외발생*/
                });
    }
    /*전체 회원 조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
