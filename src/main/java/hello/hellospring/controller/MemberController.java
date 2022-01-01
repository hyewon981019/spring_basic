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

    @Autowired
    public MemberController(MemberService memberService)
    {
        //컨트롤러 생성시 이게 호출되는데 스프링에 있는 멤버서비스를 여기에 주입한다
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm()
    {
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form)
    {
        Member member = new Member();
        member.setName(form.getName());

        //System.out.println("member : " + member.getName());
        
        memberService.join(member);


        return "redirect:/" ;//홈 화면으로 보낸다
        
    }

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
