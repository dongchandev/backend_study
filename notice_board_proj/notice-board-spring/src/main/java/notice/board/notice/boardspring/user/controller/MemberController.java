package notice.board.notice.boardspring.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final MemberService userService;

//    @GetMapping("api/userInfo")
//    public User signIn(
//            @RequestParam String login_id,
//            @RequestParam String pw,
//            @RequestParam String name
//    ){
//        User user = new User();
//        user.setLoginId(login_id);
//        user.setPw(pw);
//        user.setName(name);
//        userService.saveUser(user);
//        return user;
//    }


}
