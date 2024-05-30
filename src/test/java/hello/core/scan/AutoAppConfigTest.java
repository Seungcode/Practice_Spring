package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext AC = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = AC.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
