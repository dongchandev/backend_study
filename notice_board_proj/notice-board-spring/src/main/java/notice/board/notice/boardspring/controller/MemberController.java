package notice.board.notice.boardspring.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice.boardspring.domain.Member;
import notice.board.notice.boardspring.domain.MemberForm;
import notice.board.notice.boardspring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/register")
    public String Registration(){
        return "registration-form";
    }
    @PostMapping("/register")
    public String Registrant(MemberForm form) {
        try {
            Member member = new Member(form);
            memberService.join(member);
            return "/login-form";
        } catch (IllegalStateException e) {
            return "redirect:/register";
        }
    }
    @GetMapping("/registration")
    public String list(Model model){
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members",members);
        return "registration-form";
    }

//    @GetMapping("/registrant/{id}")
//    public String showPostDetail(@PathVariable("id") Long id, Model model) {
//        Optional<Post> optionalPost = postService.findPostById(id);
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            model.addAttribute("post", post);
//            return "posㅋt-view";
//        } else {
//            return "error"; // 적절한 에러 페이지로 이동
//        }
//    }
}
