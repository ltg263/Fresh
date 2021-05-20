package com.alienchao.javatestlib;

/**
 * @author AlienChao
 * @date 2020/09/29 10:15
 */
public class CodeBlockForJava extends BaseCodeBlockParent {
    {
        System.out.println("这里是子类的普通代码块8");
    }
    public CodeBlockForJava() {
        System.out.println("这里是子类的构造方法7");
    }
    @Override
    public void msg() {
        System.out.println("这里是子类的普通方法2");
    }

    public static void msg2() {
        System.out.println("这里是子类的静态方法4");
    }

    static {
        System.out.println("这里是子类的静态代码块5");
    }

    public static void main(String[] args) {
        BaseCodeBlockParent bcb = new CodeBlockForJava();
        bcb.msg();
        System.out.println("123");
    }
    Other o = new Other();
}

class BaseCodeBlockParent {

    public BaseCodeBlockParent() {
        System.out.println("这里是父类的构造方法22");
    }

    public void msg() {
        System.out.println("这里是父类的普通方法25");
    }

    public static void msg2() {
        System.out.println("这里是父类的静态方法27");
    }

    static {
        System.out.println("这里是父类的静态代码块28");
    }

    Other2 o2 = new Other2();

    {
        System.out.println("这里是父类的普通代码块29");
    }
}

class Other {
    Other() {
        System.out.println("初始化子类的属性值1");
    }
}

class Other2 {
    Other2() {
        System.out.println("初始化父类的属性值30");
    }
}
