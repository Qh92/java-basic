package com.qinh.generateclass;

import javassist.*;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-17-0:09
 */
public class GenerateClassTest {

    @Test
    public void t1(){
        DynGenerateClass();
    }


    public void DynGenerateClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.makeClass("GenerateClass");//创建类
        ct.setInterfaces(new CtClass[]{pool.makeInterface("java.lang.Cloneable")});//让类实现Cloneable接口
        try {
            CtField f= new CtField(CtClass.intType,"id",ct);//获得一个类型为int，名称为id的字段
            f.setModifiers(AccessFlag.PUBLIC);//将字段设置为public
            ct.addField(f);//将字段设置到类上
            //添加构造函数
            CtConstructor constructor1= CtNewConstructor.make("public GeneratedClass(){}",ct);
            ct.addConstructor(constructor1);

            CtConstructor constructor2= CtNewConstructor.make("public GeneratedClass(int pId){this.id=pId;}",ct);
            ct.addConstructor(constructor2);

            ClassFile ccFile = ct.getClassFile();
            ConstPool constpool = ccFile.getConstPool();

            // create the annotation
            AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
            Annotation annot = new Annotation("MyAnnotation", constpool);
            annot.addMemberValue("name", new IntegerMemberValue(ccFile.getConstPool(), 0));
            attr.addAnnotation(annot);
            //添加方法
            CtMethod helloM=CtNewMethod.make("public void hello(String des){ System.out.println(des);}",ct);

            ct.addMethod(helloM);

            helloM.getMethodInfo().addAttribute(attr);

            ct.writeFile();//将生成的.class文件保存到磁盘

            //下面的代码为验证代码
            Field[] fields = ct.toClass().getFields();
            System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
