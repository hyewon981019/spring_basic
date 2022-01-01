package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository; //이거 보고 SpringDataJpaMemberRepository
    //이해 x

    @Autowired //생략가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    //private DataSource dataSource;
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    //    @Autowired
//    public SpringConfig(DataSource dataSource)
//    {
//        this.dataSource = dataSource;
//    }
    @Bean
    public MemberService memberService()
    {
        return new MemberService(memberRepository);
    }

    //빈에 직접 등록
//    @Bean
//    public TimeTraceAop timeTraceAop()
//    {
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberRepository memberRepository()
//    {
//        return new MemoryMemberRepository(); //구현체로 생성
//    }

//    @Bean
//    public MemberRepository memberRepository()
//    {
//        //return new JdbcMemberRepository(dataSource); //구현체로 생성
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }
}
