package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    //Optional<> :
    //  1. null이 올 수 있는 값을 감싸는 Wrapper 클래스
    //  2. 여러 메서드 제공
    //findById : 매개변수에 해당하는 값을 토대로 DB에서 값을 조회해오는 역할
    Optional<Member> findByName(String name);
    //이름 찾기
    List<Member> findAll();
    //findall() 메서드
    //1. 패턴을 모두 찾아 리스트로 반환한다
    //2. search문이나 match문은 제대로 찾았는지 확인하는 절차가 따로 필요하지만
    //3. findall() 메서드로 찾으면 결과값이 빈 리스트로 []로 나온다
}