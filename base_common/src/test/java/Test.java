import java.util.Optional;
import java.util.stream.Stream;

import static rx.schedulers.Schedulers.test;

public class Test {
    @org.junit.Test
    public void test001(){
        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
//查找所有包含t的元素并进行打印
        s.filter(n -> n.contains("t")).forEach(System.out::println);
    }


    @org.junit.Test
    public void test002(){
        String s = "test()";
        if (null != s) {
            System.out.println(s);
        }

        Optional<String> ss = Optional.ofNullable("test()");
        ss.ifPresent(System.out::println);
    }

}
