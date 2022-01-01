package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    
//    @BeforeEach
//    public void beforeEach()
//    {
//        memberRepository = new MemoryMemberRepository(); //레포지토리 객체 생성
//        memberService = new MemberService(memberRepository);// 레포 객체를 주입
//    }

    //이제는 직접 내놔!


    @Test
    void join() {

        //given

        Member member = new Member();
        member.setName("hyewon"); //오류남, 근데 테스트는 독립적이어야한다고 했는데? -> transactional 사용

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception //join에서 예외 상황이 생기는 것에 대한 테스트 함수
    {
        //given
        Member member1 = new Member();
        member1.setName("spring11");

        Member member2 = new Member();
        member2.setName("spring11"); //중복 회원

        //when
        memberService.join(member1);
        IllegalStateException e= assertThrows(IllegalStateException.class , ()->memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}