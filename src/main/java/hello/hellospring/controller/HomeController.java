package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //홈화면 매핑 순서에 따라 가장 먼저 등장 웰컴 페이지 보다 빠름름
   @GetMapping("/")
    public String home(){
        return "home";
    }
}
