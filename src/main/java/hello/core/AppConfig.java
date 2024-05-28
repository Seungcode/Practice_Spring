package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정정보라는 뜻
@Configuration
public class AppConfig {
    //사용시 Bean Contaioner에 등록
    @Bean
    //생성자 주입
    public MemberService memberService(){
        return new MemberServiceImpl(MemberRepository());
    }
    @Bean
    public static MemoryMemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(), DiscountPolicy());
    }
    @Bean
    public static DiscountPolicy DiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
