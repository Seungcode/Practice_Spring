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

public class AppConfig {

    //생성자 주입
    public MemberService memberService(){
        return new MemberServiceImpl(MemberRepository());
    }

    private static MemoryMemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(), DiscountPolicy());
    }

    private static DiscountPolicy DiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
