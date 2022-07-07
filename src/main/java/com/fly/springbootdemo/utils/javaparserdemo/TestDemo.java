package com.fly.springbootdemo.utils.javaparserdemo;


import com.github.javaparser.ast.CompilationUnit;
import org.springframework.beans.factory.annotation.Value;


public class TestDemo {
    private static final String FILE_PATH="../springbootdemo/src/main/java/com/fly/springbootdemo/utils/javaparserdemo/DemoClassModel.java";

    @Value("${authority.flag}")
    public boolean isOpen;

    public  boolean isOpen() {
        return isOpen;
    }

    public static void main(String[] args) {
        CompilationUnit cu= new JavaParserUtil().getCompilationUnit(FILE_PATH);

        MethodNamePrinter methodNamePrinter =new MethodNamePrinter();
        methodNamePrinter.visit(cu,null);

        System.out.println(new TestDemo().isOpen());
    }
}
