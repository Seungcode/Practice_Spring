package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        ApplicationContext AC = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = AC.getBean("memberService", MemberService.class);
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        //assertj.core에 있는거 사용! -> 유용한 내용이 많은 라이브러리
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
