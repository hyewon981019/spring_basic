package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;

public class MemoryMemberRepositoryTest { //여기서 돌리면 모든 테스트를 실행해볼수있음

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach()
    {
        repository.clearStore(); //테스트 하나 끝날 때마다 하나씩 ㅣㅈ움
    }

    @Test
    public void save()
    {
        //저장 잘 되는지
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장, 아이디 세팅되었을 것
        
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);//같은지 보기(출력되진 않음)
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get(); //spring1이라고하는 이름 가진 멤버 객체 꺼낸다

       Assertions.assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }


}
