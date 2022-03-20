package hello.core.singelton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //실행시 자신을 생성해서 참조에 넣어둠

    public static SingletonService getInstance() {//이 메서드를 통해서만 조회할 수 있다.(1개의 인스턴스만 반환한다.(static final)
        return instance;
    }

    private SingletonService() { // 프라이빗 생성자를 이용해서 생성을 막아버림(외부에서 new키워드로 인스턴스가 생성되는 것을 막는다.)

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
