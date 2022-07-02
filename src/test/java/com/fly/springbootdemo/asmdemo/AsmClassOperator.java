package com.fly.springbootdemo.asmdemo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AsmClassOperator {
    public static void main(String[] args) throws IOException {
        // 读取指定类
        ClassReader classReader=new ClassReader("com.fly.springbootdemo.asmdemo.Calculator");
        ClassWriter classWriter=new ClassWriter(ClassWriter.COMPUTE_MAXS);

        // 处理
        ClassVisitor classVisitor=new MyClassVisitor(classWriter);
        classReader.accept(classVisitor,ClassReader.SKIP_DEBUG);
        byte[] data=classWriter.toByteArray();

        // 输出
        File file=new File("target/test-classes/com/fly/springbootdemo/asmdemo/Calculator.class");

        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
        System.out.println("字节码修改成功");


        new Calculator().add(2,3);
    }
}
