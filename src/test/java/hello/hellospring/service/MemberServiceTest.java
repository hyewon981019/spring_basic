package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService; //객체 공간 생성
    MemoryMemberRepository memberRepository;
    //이 테스트 파일에서 레포지토리를 clear하기 위해 선언
    //MemberService에서의 레포지토리와 다른 객체이기 때문에 애매함
    
    @BeforeEach
    public void beforeEach()
    {
        memberRepository = new MemoryMemberRepository(); //레포지토리 객체 생성
        memberService = new MemberService(memberRepository);// 레포 객체를 주입
    }

    @AfterEach
    public void afterEach()
    {
        memberRepository.clearStore(); //테스트 하나 끝날 때마다 하나씩 지움
    }


    @Test
    void join() {

        //given

        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() //join에서 예외 상황이 생기는 것에 대한 테스트 함수
    {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //중복 회원

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class , ()->memberService.join(member2));

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}