package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext AC = new AnnotationConfigApplicationContext(client.class, PrototypeBean.class);

        client bean1 = AC.getBean(client.class);
        client bean2 = AC.getBean(client.class);

        int cnt2 = bean1.logic();
        int cnt1 = bean2.logic();

        Assertions.assertThat(cnt1).isEqualTo(1);
        Assertions.assertThat(cnt2).isEqualTo(1);

        AC.close();
    }

    @Configuration
    static class client{
        //하나의 호출  하나씩 만들어 줌
        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;

        public int logic(){
            PrototypeBean object = prototypeBeanObjectProvider.get();
            int count = object.addCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public int addCount(){
            count++;
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("SingletonBean.close");
        }
    }
}
