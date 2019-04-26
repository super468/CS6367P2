package edu.utd;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class ClassTransformVisitor extends ClassVisitor implements Opcodes {

    private String className;

    public ClassTransformVisitor(final ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        super.visit(version, access, name, signature,
                superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if(!DataTraceCollection.staticmap.containsKey(className + "/" + name + "/" + signature)){
            DataTraceCollection.staticmap.put(className + "/" + name + "/" + signature, true);
        }
        DataTraceCollection.methodName = name;
        Type methodType = Type.getMethodType(desc);
        int len = methodType.getArgumentTypes().length;
        return mv == null ? null : new MethodTransformVisitor(mv, className, name, signature, methodType.getArgumentTypes(), len, access);
    }
}

