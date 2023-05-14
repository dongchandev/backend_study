package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    //MemberRepository라는 안터페이스를 MemoryMemberRepository가 상속받음

    private static Map<Long, Member> store=new HashMap<>();
    //Map:key와 value의 대응관계를 쉽게 표현할 수 있게 해 주는 자료형
    //HashMap<>:put,get등을 쓰게 해줌
    private static long sequence =0L;
    @Override
    //해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언
    //컴파일러에게 문법 체크를 하도록 알린다(부모 클래스의 있는 메서드명과 매개변수를 동일하게 했는지)
    public Member save(Member member) {
        member.setId(++sequence);
        //++sequence를 하여 id 값을 설정
        store.put(member.getId(),member);
        //HashMap에 key와 value을 저장.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    //ofNullable:null값을 허용 , Optional의 메서드
    //get(key):key의 value를 가져옴
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                //values():열거된 모든 원소를 배열에 담아 순서대로 리턴
                //stream():Collection 인터페이스에는 stream()이 정의되어 있음
                //         ->Collection 인터페이스를 구현한 객체들(List, Set 등)은 모두 이 메소드를 이용해 Stream을 생성 가능
                //         stream()을 사용하면 해당 Collection의 객체를 소스로 하는 Stream을 반환
                .filter(member -> member.getName().equals(name))
                //Filter는 Stream에서 조건에 맞는 데이터만을 정제하여 더 작은 컬렉션을 만들어내는 연산
                //boolean을 반환하는 람다식을 작성하여 filter 함수를 구현 가능
                .findAny();
        //findAny():조건에 일치하는 요소 1개를 리턴
        //멤버에서 가져온 이름과 매개변수로 받은 이름이 같은 것을 하나 리턴
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //ArrayList 1. 표준 배열보다는 느리지만 배열에서 많은 조작이 필요한 경우 유용하게 사용
    //          2. 객체가 추가되어 용량을 초과하면 자동으로 부족한 크기만큼 용량이 늘어남

    public void clearStore(){
        store.clear();
        //store의 모든 값 제거
    }
}