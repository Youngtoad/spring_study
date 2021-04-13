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
public class MemberController {

    private final MemberService memberService;

    //처음 세팅하고 값을 변경시키지 않으니 생성자 주입이 제일 낫다. 세터 주입과 필드 주입보다 낫다
    //세터 주입은 퍼블릭으로 노출되어 값이 변질될 수 있다. 의존관계가 실행중 동적으로 변할일 없음!!
    //실무에서는 주로 컴포넌트 스캔으로 생성한다. 상황에 따라 구현체를 변경하기 위해 빈 객체 등록(ex. 나중에 memorymemberrepository변경 가능)
    //나중에 config파일만 수정하면 따로 건들이지 않아도 된다!!
    //클래스가 먼저 등록되어야 안에 객체 주입하여 사용가능, 스프링 컨테이너에 등록된 것만 작동된다~
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
