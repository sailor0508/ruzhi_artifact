package com.ruzhi.demo.util.apt.sqlCreate;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.util.*;

import java.util.*;

import static com.sun.mirror.util.DeclarationVisitors.*;

/**
 * 访问这模式 + 注解器： 扩展性更强 比 自己实现注解处理器
 *
 * 能 生成和 TableCreator 一样的结果
 */
public class TableCreationProcessorFactory implements AnnotationProcessorFactory {
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList("annotations.database.DBTable", "annotations.database.Constraints",
                "annotations.database.SQLString", "annotations.database.SQLInteger");
    }

    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    //处理器  内部类
    private static class TableCreationProcessor implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        private String sql = "";

        public TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        public void process() {
            for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
                // getDeclarationScanner 中的两个参数分别是处理程序访问每个声明前后使用的访问者；因为处理后无需做什么事情
                // 所以使用一个什么都不做的访问这 NO_OP
                typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }
        //访问者   内部类
        private class TableCreationVisitor extends SimpleDeclarationVisitor {
            public void visitClassDeclaration(ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if (dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
                    sql += " (";
                }
            }

            public void visitFieldDeclaration(FieldDeclaration d) {
                String columnName = "";
                if (d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(SQLInteger.class);
                    // Use field name if name not specified
                    if (sInt.name().length() < 1) columnName = d.getSimpleName().toUpperCase();
                    else columnName = sInt.name();
                    sql += "\n    " + columnName + " INT" + getConstraints(sInt.constraints()) + ",";
                }
                if (d.getAnnotation(SQLString.class) != null) {
                    SQLString sString = d.getAnnotation(SQLString.class);
                    // Use field name if name not specified.
                    if (sString.name().length() < 1) columnName = d.getSimpleName().toUpperCase();
                    else columnName = sString.name();
                    sql += "\n    " + columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()) + ",";
                }
            }

            private String getConstraints(Constraints con) {
                String constraints = "";
                if (!con.allowNull()) constraints += " NOT NULL";
                if (con.primaryKey()) constraints += " PRIMARY KEY";
                if (con.unique()) constraints += " UNIQUE";
                return constraints;
            }
        }
    }
} ///:~