package com.fly.springbootdemo.demo;

public class TestDemo {
    public static void main(String[] args) {
        Student student1=new Student();
        student1.setId(1);
        student1.setName("zhangsan");
        student1.setAge(18);

        Student student2=new Student();
        student2.setId(12);
        student2.setName("zhangsan");
        student2.setAge(18);

        System.out.println(student1.equals(student2));
    }

}
