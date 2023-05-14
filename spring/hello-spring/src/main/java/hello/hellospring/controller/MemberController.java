package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// 스프링 창에 뜰 떄, 스프링 컨테이너가 생성되는데
// @Controller가 있으면 MemberController객체를 생성 후 스프링에 넣어둠
// 스프링 컨테이너:
//  스프링 프레임워크의 핵심 컴포넌트
//  스프링 컨테이너는 자바 객체의 생명 주기를 관리
//  생성된 자바 객체들에게 추가적인 기능을 제공
//  스프링에서 자바 객체 = 빈(Bean)
public class MemberController {
    private final MemberService memberService;
    //스프링이 관리를 하게 되면 타 스프링 컨테이너에 등록 후,
    //자바소스로 바꾸어 받아야함
    //@Autowired
    //멤버 컨트롤러가 생성될 떄 생성자를 호출
    //@Autowired memberService를 스프링 컨테이너의 memberService와 연결
    //DI(의존성 종속, Dependency Injection):클래스간 의 의존관계를 스프링 컨테이너가 자동으로 연결해주는 것
    //DI가 필요한 이유 : 객체 간 의존성
    //  예를 들어 Factory 인터페이스를 상속받는 ConsoleFactory와 UserFactory가 있을 때
    //  SW를 사용하는 고객은 Factory 클래스만을 호출해야함
    //  또한, 그것이 ConsoleFactory인지 UserFactory인지 몰라야함
    //  고객마다 전용 Factory를 생성할 경우 코드 생산성이 떨어지며, 고객이 몰라도 되는 코드가 노출되기 때문
    //  ->스프링은 Factory가 ConsoleFactory인지 UserFactory인지를 프레임워크가 자동으로 객체간 의존성을 주입

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    //1) @PostMapping : HTTP Post Method에 해당하는 단축 표현으로 서버에 리소스를 등록(저장)할 때 사용합니다.
    //2) @GetMapping : HTTP Get Method에 해당하는 단축 표현으로 서버의 리소스를 조회할 때 사용합니다.
    //3) @DeleteMapping : 서버의 리소스를 삭제
    //4) @PutMapping : 서버의 리소스를 모두 수정
    //5) @PatchMapping : 서버의 리소스를 일부 수정
    //post는 데이터 전달,등록 get은 조회

    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        //"members"키를 통해 members를 리스트형태로 넣어둠
        return "members/memberList";
    }
    //memory에 있어서 서버를 내리면 데이터가 사라짐
    //->데이터를 파일/DB에 저장해야함
}