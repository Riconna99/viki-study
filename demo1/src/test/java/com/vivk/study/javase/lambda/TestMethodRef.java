package com.vivk.study.javase.lambda;

import com.vivk.study.javase.stream.Employee;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一：方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * （可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * 主要有三种语法格式：
 * 注意：
 * 1.Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 2.若Lambda参数列表中第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 * 对象::实例方法名
 *
 * 类：静态方法名
 *
 * 类：：实例方法名
 *
 * 二：构造器应用：
 *  格式：
 *  ClassName::new
 *
 *  注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！！
 *
 *  三、数组引用：
 *    Type[]::new;
 */
public class TestMethodRef {
    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> con=(x)-> System.out.println(x);
        PrintStream ps = System.out;
        Consumer<String > con1= ps::println;

        Consumer<String> con2=System.out::println;
        con2.accept("abcdefg");
    }
    @Test
    public void test2(){
        Employee emp = new Employee();
        Supplier<String> sup = ()-> emp.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = emp::getAge;
        String str2 = sup.get();
        System.out.println(str2);
    }
    //类：静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        Comparator<Integer> com1=Integer::compare;
    }
    //类：实例方法名
    @Test
    public void test4(){
        BiPredicate<String,String> bp=(x,y)->x.equals(y);

        BiPredicate<String,String>bp2=String::equals;
    }
    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> sup=()->new Employee();
        //构造器引用方式
        Supplier<Employee> sup2 = Employee::new;
        Employee emp = sup2.get();
        System.out.println(emp);
    }
    @Test
    public void test6(){
        Function<String,Employee> fun=(x)->new Employee(x);
        Function<String,Employee> fun2=Employee::new;
        Employee emp = fun2.apply("张三");
        System.out.println(emp);

        BiFunction<Integer,Integer,Employee> bf=Employee::new;
    }
    //数组引用
    @Test
    public void test7(){
       Function<Integer,String[]> fun=(x)->new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer,String[]> fun2= String[]::new;
        String[] str2 = fun2.apply(20);
        System.out.println(str2.length);
    }
}
