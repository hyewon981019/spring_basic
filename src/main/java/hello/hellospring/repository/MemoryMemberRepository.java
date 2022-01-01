package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ //인터페이스 구현

    private static
    Map<Long, Member> store= new HashMap<>();
    private static long sequence = 0L; //키값을 생성해준다

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //하나 올려주고
        store.put(member.getId(), member); //스토어에 저장하고
        return member; //반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이어도 반환해주도록

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //루프 돌면서 파라미터로 넘어온 네임만 필터링
                .findAny(); //하나라도 찾으면 바로 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //멤버들 모두 반환
    }
    //테스트케이스로 잘 돌아가는지 확인
    
    public void clearStore()
    {
        store.clear(); //스토어 비움
    }
}
