package com.fly.springbootdemo.utils.javaparserdemo;


import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class MethodNamePrinter extends VoidVisitorAdapter<Void> {

    /**
     * 获取方法名
     * @param methodDeclaration
     * @param arg
     */
    @Override
    public void visit(MethodDeclaration methodDeclaration, Void arg) {
        super.visit(methodDeclaration,null);
        System.out.println("method name:"+methodDeclaration.getName());
    }
}
