package hello.core.singleton;

public class SingletonService {
    //하나만 생김
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //생성을 막아버린다!! -> 하나만 생기고 다른 곳에서는 생성 불가 -> 싱글톤 패턴
    private SingletonService(){
    }

}
