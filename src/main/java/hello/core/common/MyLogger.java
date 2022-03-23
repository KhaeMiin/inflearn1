package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)//가짜 프록시 클래스를 만들어서 HTTP request와 상관없이  다른 빈에 미리 주입
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct//의존관계 주입 후 실행
    public void init() {
        uuid = UUID.randomUUID().toString();//글로벌하게 유니크한 아이디생성
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy//종료시
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
