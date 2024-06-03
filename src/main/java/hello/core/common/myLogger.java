package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//적용대상이 인터페이스일 경우에는 .INTERFACE
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class myLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + "[" + message + "]");
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean create: " + this);
    }

    @PreDestroy
    public void colse()
    {
        System.out.println("[" + uuid + "]" + "request scope bean close: " + this);
    }
}
