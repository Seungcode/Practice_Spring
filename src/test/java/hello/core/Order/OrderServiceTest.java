package hello.core.Order;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        ApplicationContext AC = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = AC.getBean("memberService", MemberService.class);
        orderService = AC.getBean("orderService", OrderService.class);
    }

    @Test
    void createOrder() {
        Long memeberId = 1L;
        Member member = new Member(memeberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memeberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
