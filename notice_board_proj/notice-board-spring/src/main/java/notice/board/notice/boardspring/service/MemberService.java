package notice.board.notice.boardspring.service;

import notice.board.notice.boardspring.domain.Member;
import notice.board.notice.boardspring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
