package hello.core.scan.allBean;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){
        ApplicationContext AC = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = AC.getBean(DiscountService.class);
        Member mem = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(mem, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int discountPrice2 = discountService.discount(mem, 20000, "rateDiscountPolicy");
        assertThat(discountPrice2).isEqualTo(2000);
    }

    @RequiredArgsConstructor
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public int discount(Member mem, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(mem, price);
        }
    }

}
