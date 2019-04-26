package edu.utd;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import edu.utd.object.Variable;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class MyMethodAdapter extends MethodNode implements Opcodes{
    private String className;
    private String methodName;
    private Type[] paramTypes;
    private int argLen;
    private String signature;
    private int access;
    private MethodVisitor mv;

    public MyMethodAdapter(int access, String name, String desc,
                           String signature, String[] exceptions, MethodVisitor mv, Type[] paramTypes, int argLen) {
        super(ASM5, access, name, desc, signature, exceptions);
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.argLen = argLen;
        this.signature = signature;
        this.access = access;
        this.mv = mv;
    }

    @Override
    public void visitCode(){
        List<Variable> list = this.localVariables;
        // avoid construct method
        if(!methodName.equals("<init>")) {
            int paramLength = paramTypes.length;
            System.out.println(className + "." + methodName + "." + signature + ": paramLength = " + paramLength);

            int i = 1;
            if((this.access & Opcodes.ACC_STATIC) != 0){
                System.out.println("in visitCode " + className + "/" + methodName + "/" + signature + "is a static method");
                i = 0;
            }
//            if (!DataTraceCollection.staticmap.get(className + "/" + methodName + "/" + signature)) {
//                //System.out.println("in visitCode " + className + "/" + methodName + "/" + signature + "is a virtual method");
//                i = 1;
//            }
            //System.out.println("xxxxxxxxx");
            for (Type tp : paramTypes) {
                System.out.println("tp.getClassName() = " + tp.getClassName());
                if (tp.equals(Type.BOOLEAN_TYPE)) {
                    mv.visitVarInsn(Opcodes.ILOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                } else if (tp.equals(Type.BYTE_TYPE)) {
                    mv.visitVarInsn(Opcodes.ILOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
                } else if (tp.equals(Type.CHAR_TYPE)) {
                    mv.visitVarInsn(Opcodes.ILOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
                } else if (tp.equals(Type.SHORT_TYPE)) {
                    mv.visitVarInsn(Opcodes.ILOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
                } else if (tp.equals(Type.INT_TYPE)) {
                    mv.visitVarInsn(Opcodes.ILOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
                } else if (tp.equals(Type.LONG_TYPE)) {
                    mv.visitVarInsn(Opcodes.LLOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
                    // long may need two indices
                    i++;
                } else if (tp.equals(Type.FLOAT_TYPE)) {
                    mv.visitVarInsn(Opcodes.FLOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
                } else if (tp.equals(Type.DOUBLE_TYPE)) {
                    mv.visitVarInsn(Opcodes.DLOAD, i);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
                    // double may need two indices
                    i++;
                } else
                    mv.visitVarInsn(Opcodes.ALOAD, i);
                mv.visitLdcInsn(className);
                mv.visitLdcInsn(methodName);
                mv.visitLdcInsn(signature);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utd/DataTraceCollection", "addVariable", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
                i++;
            }
        }
        super.visitCode();
    }

    @Override
    public void visitEnd() {
        // put your transformation code here
        accept(mv);
    }


}
