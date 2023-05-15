package hello.hellospring.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
// controller
// 1. 사용자의 요청이 진입하는 지점
// 2. 요청에 따라 어떤 처리를 할지 결정
//    but, 결정만 하고 실질적 처리는 서비스에서 수행
// 3. 사용자에게 View를 응답으로 보냄
// 역할분담 위해 사용
public class HelloController {
    @GetMapping("hello") // spring에서 알아서 hello 이름의 View를 반환
    public String hello(Model model) { // Model 객체는 컨트롤러에서 데이터를 생성해 이를 View에 전달하는 역할
        model.addAttribute("data", "hello!!!"); // hello!!!라는 객체를 data에 추가
        return "hello";
        // -> return 타입을 String 값으로 뷰의 이름을 지정해주면 뷰로 데이터가 전송
        // -> 뷰에서는 해당 데이터의 key값을 객체 이름으로 하여 그 안에 데이터를 조회
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        // @RequestParam("가져올 데이터의 이름") [데이터타입] [가져온데이터를 담을 변수]
        // Model 객체를 이용해서, 뷰로 값을 넘김
        model.addAttribute("name",name);
        return "hello-template";
    }
    @GetMapping("hello-spring")
    @ResponseBody
    //이 어노테이션이 붙은 파라미터에는 http요청의 body에 그대로 전달
    //xml이나 json기반의 메시지를 사용하는 요청의 경우에 이 방법이 매우 유용
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;//문자 그대로 내려감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //객체생성
        hello.setName(name);
        return hello;
    }
    // JSON형식(key:value)으로 넘겨주는 방식

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
        //매개 변수와 객체 자신이 가지고 있는 변수의 이름이 같은 경우 이를 구분하기 위해 this를 사용
    }
}
