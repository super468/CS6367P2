package edu.utd;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        //System.out.println("Java Agent is running");
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
                //System.out.println("transforming " + className);
                if (className.startsWith("org/apache/commons/lang3")||
                        className.startsWith("org/apache/commons/dbutils")||
                        className.startsWith("org/joda/time")
                ){
                    System.out.println(className);
                    ClassReader cr = new ClassReader(bytes);
                    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                    ClassTransformVisitor ca = new ClassTransformVisitor(cw);
                    cr.accept(ca, 0);
                    return cw.toByteArray();
                }
                return bytes;
            }
        });
    }
}
