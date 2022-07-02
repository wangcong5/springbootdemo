package com.fly.springbootdemo.asmdemo;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor implements Opcodes {

    protected MyClassVisitor(ClassVisitor cv) {
        // Opcodes.ASM4~Opcodes.ASM9标识了ASM的版本信息
        // 应用场景：用于创建具体的ClassVisitor实例，例如ClassVisitor(int api, ClassVisitor classVisitor)中的api参数
        super(ASM5,cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv= cv.visitMethod(access,name,descriptor,signature,exceptions);

        if (name.equals("add")&&mv!=null){
            mv=new MyMethodVisitor(mv);
        }
        return mv;
    }
}
