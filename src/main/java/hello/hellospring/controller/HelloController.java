package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello !");
        return "hello"; //hello.html로 가서 페이지 띄워라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("name", name);
        return "hello-template"; //hello.html로 가서 페이지 띄워라
    }

    //api 방식
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello " + name; //뷰 없이 이 문자열이 그대로 내려간다(페이지 소스 보면 알 수 있음)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json 형식으로 리턴한다

    }

    static class Hello {
        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }


    }
}
