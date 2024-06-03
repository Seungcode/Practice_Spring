package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
//test용 구현 -> 추후 디비가 정해지면 그걸로 바꿔주기
public class MemoryMemberRepository implements MemberRepository{

    public static Map<Long, Member> store = new HashMap<>();

    //ID 넣어주고
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    //ID 찾아주기
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
