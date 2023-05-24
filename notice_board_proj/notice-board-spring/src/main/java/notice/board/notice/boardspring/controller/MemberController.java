package notice.board.notice.boardspring.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice.boardspring.domain.Member;
import notice.board.notice.boardspring.domain.Post;
import notice.board.notice.boardspring.repository.MemberRepository;
import notice.board.notice.boardspring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/register")
    public String registrationForm(){
        return "registration-form";
    }
//    @PostMapping("/")
//    public String write(
//            @RequestParam Long id,
//            @RequestParam Long pw,
//            @RequestParam String name
//    ){
//
//    }
}
