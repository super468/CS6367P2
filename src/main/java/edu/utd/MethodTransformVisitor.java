package edu.utd;
import edu.utd.object.Variable;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;


class MethodTransformVisitor extends MethodVisitor implements Opcodes {
    private String className;
    private String methodName;
    private Type[] paramTypes;
    private int argLen;
    private String signature;
    private int access;

    /**
     *
     * @param mv
     * @param className
     * @param methodName
     * @param paramTypes
     * @param argLen
     */
    public MethodTransformVisitor(final MethodVisitor mv, String className, String methodName, String signature, Type[] paramTypes, int argLen, int access) {
        super(ASM5, mv);
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.argLen = argLen;
        this.signature = signature;
        this.access = access;
    }


    @Override
    public void visitCode(){
        // avoid construct method
        if(!methodName.equals("<init>")) {
            int paramLength = paramTypes.length;
            System.out.println(className + "." + methodName + "." + signature + ": paramLength = " + paramLength);
            // set the starting index, if the method is static starting from 0,
            // otherwise starting from 1
            int i = 1;
            if((this.access & Opcodes.ACC_STATIC) != 0){
                System.out.println("in visitCode " + className + "/" + methodName + "/" + signature + "is a static method");
                i = 0;
            }
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
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index){
        if("this".equals(name)){
            //DataTraceCollection.staticmap.put(className + "/" + methodName + "/" + this.signature, false);
            //System.out.println(className + "/" + methodName + "/" + this.signature + "is a virtual method");
        } else if(argLen-- > 0) {
            String s = className + "/" + methodName + "/" + this.signature;
            if(!DataTraceCollection.map.containsKey(s)){
                DataTraceCollection.map.put(s, new ArrayList<Variable>());
            }
            Variable v = new Variable(Type.getType(desc), name, index);
            DataTraceCollection.map.get(s).add(v);
        }
        super.visitLocalVariable(name,desc,signature,start,end,index);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
