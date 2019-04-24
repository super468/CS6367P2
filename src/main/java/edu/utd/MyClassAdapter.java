package edu.utd;





import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;

public class MyClassAdapter extends ClassNode implements Opcodes {
    public MyClassAdapter(ClassVisitor cv) {
        super(ASM5);
        this.cv = cv;
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     java.lang.String name,
                                     java.lang.String descriptor,
                                     java.lang.String signature,
                                     java.lang.String[] exceptions){
        DataTraceCollection.methodName = name;
        Type methodType = Type.getMethodType(descriptor);
        int len = methodType.getArgumentTypes().length;
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if(mv != null){
            return new MyMethodAdapter(access, name, descriptor, signature, exceptions, mv, methodType.getArgumentTypes(), len);
        } else return null;
    }


    @Override
    public void visitEnd() {
        // put your transformation code here accept(cv);
        super.visitEnd();
        accept(cv);
    }
}