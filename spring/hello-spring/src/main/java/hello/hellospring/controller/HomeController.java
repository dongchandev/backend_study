package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    // HTTP GET 요청을 처리하는 메서드를 맵핑(@RequestMapping) 하는 어노테이션
    // 메서드(url)에 따라 어떤 페이지를 보여줄지 결정하는  역할
    public String home(){
        return "home";
    }
}
