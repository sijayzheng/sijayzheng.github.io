package sijay.zheng.experience.javase.lambda;

/**
 * @author Sijay
 */
public class Test01 {
    public static void main(String[] args) {
        IHello hello = new Hello();
        hello.lambda();
        hello = new Hello2();
        hello.lambda();
        //4、局部内部类
        class Hello3 implements IHello {
            @Override
            public void lambda() {
                System.out.println("hello lambda 333333");
            }
        }
        hello = new Hello3();
        hello.lambda();

        //5、匿名内部类
        hello = new IHello() {
            @Override
            public void lambda() {
                System.out.println("hello lambda 44444444");
            }
        };
        hello.lambda();
        //6、lambda表达式
        hello = () -> System.out.println("hello lambda 5555");
        hello.lambda();

    }

    //3、静态内部类
    static class Hello2 implements IHello {
        @Override
        public void lambda() {
            System.out.println("hello lambda 22222");
        }
    }
}

//1、接口
interface IHello {
    void lambda();
}

//2、实现类
class Hello implements IHello {
    @Override
    public void lambda() {
        System.out.println("hello lambda");
    }
}