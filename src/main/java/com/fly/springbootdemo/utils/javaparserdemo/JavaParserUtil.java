package com.fly.springbootdemo.utils.javaparserdemo;


import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
public class JavaParserUtil {
    public JavaParserUtil() {
    }

    /**
     * 获取所有注释
     *
     * @param cu
     * @return
     */
    public List<Comment> getAllComments(CompilationUnit cu) {
        return cu.getAllComments();
    }

    public CompilationUnit getCompilationUnit(String filePath) {
        log.info("file path is: {}", filePath);
        File file = new File(filePath);
        try {
            return StaticJavaParser.parse(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
