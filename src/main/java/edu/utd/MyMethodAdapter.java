package edu.utd;

import edu.utd.object.Status;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class MyMethodAdapter extends MethodNode implements Opcodes{
    Type[] paramTypes;
    public List<String> argumentNames;
    public int argLen;
    public MyMethodAdapter(int access, String name, String desc,
                           String signature, String[] exceptions, MethodVisitor mv, Type[] paramTypes, List<String> argumentNames, int argLen) {
        super(ASM5, access, name, desc, signature, exceptions);
        this.mv = mv;
        this.paramTypes = paramTypes;
        this.argumentNames = argumentNames;
        this.argLen = argLen;
    }

    @Override
    public void visitCode(){
        DataTraceCollection.status = new Status();
//        System.out.println("Method Executed");
//        if(this.localVariables == null) System.out.println("local null XXXX");
//        System.out.println(this.localVariables.size());
//        List<LocalVariableNode> loc = this.localVariables;
//        for(LocalVariableNode localVariableNode : loc){
//            System.out.println("The type descriptor of this local variable." + localVariableNode.desc);
//            System.out.println("The local variable's index." + localVariableNode.index);
//            System.out.println("The name of a local variable." + localVariableNode.name);
//            System.out.println("The signature of this local variable." + localVariableNode.signature);
//        }

//        int paramLength = paramTypes.length;
//
//        // Create array with length equal to number of parameters
//        mv.visitIntInsn(Opcodes.BIPUSH, paramLength);
//        mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
//        mv.visitVarInsn(Opcodes.ASTORE, paramLength);
//
//        // Fill the created array with method parameters
//        int i = 0;
//        for (Type tp : paramTypes) {
//            mv.visitVarInsn(Opcodes.ALOAD, paramLength);
//            mv.visitIntInsn(Opcodes.BIPUSH, i);
//
//            if (tp.equals(Type.BOOLEAN_TYPE)) {
//                mv.visitVarInsn(Opcodes.ILOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
//            }
//            else if (tp.equals(Type.BYTE_TYPE)) {
//                mv.visitVarInsn(Opcodes.ILOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
//            }
//            else if (tp.equals(Type.CHAR_TYPE)) {
//                mv.visitVarInsn(Opcodes.ILOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
//            }
//            else if (tp.equals(Type.SHORT_TYPE)) {
//                mv.visitVarInsn(Opcodes.ILOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
//            }
//            else if (tp.equals(Type.INT_TYPE)) {
//                mv.visitVarInsn(Opcodes.ILOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
//            }
//            else if (tp.equals(Type.LONG_TYPE)) {
//                mv.visitVarInsn(Opcodes.LLOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
//                i++;
//            }
//            else if (tp.equals(Type.FLOAT_TYPE)) {
//                mv.visitVarInsn(Opcodes.FLOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
//            }
//            else if (tp.equals(Type.DOUBLE_TYPE)) {
//                mv.visitVarInsn(Opcodes.DLOAD, i);
//                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
//                i++;
//            }
//            else
//                mv.visitVarInsn(Opcodes.ALOAD, i);
//            mv.visitInsn(Opcodes.AASTORE);
//            i++;
//        }
////
////        // Load id, class name and method name
////        this.visitLdcInsn(new Integer(this.methodID));
////        this.visitLdcInsn(this.className);
////        this.visitLdcInsn(this.methodName);
//        // Load the array of parameters that we created
//        this.visitVarInsn(Opcodes.ALOAD, paramLength);
//        mv.visitMethodInsn(INVOKESTATIC, "edu/utd/DataTraceCollection", "getParamsValues", "([Ljava/lang/Object;)V", false);
        super.visitCode();
    }
    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index){
        if("this".equals(name)) {
            return;
        }
        if(argLen-- > 0) {
            //argumentNames.add(name);
            DataTraceCollection.localName = name;
            DataTraceCollection.type = Type.getType(desc);

        }
        System.out.println("The type descriptor of this local variable." + desc);
        System.out.println("The local variable's index." + index);
        System.out.println("The name of a local variable." + name);
        System.out.println("The signature of this local variable." + signature);
        super.visitLocalVariable(name,desc,signature,start,end,index);
    }

    @Override
    public void visitEnd() {
        // put your transformation code here
//        List<FieldNode> fields = ca.fields;
//        for(FieldNode field : fields){
//            System.out.println(field.name + " " + field.value);
//        }
//        System.out.println("Method Executed");
//        if(this.localVariables == null) System.out.println("local null XXXX");
//        System.out.println(this.localVariables.size());
//        List<LocalVariableNode> loc = this.localVariables;
//        for(LocalVariableNode localVariableNode : loc){
//            System.out.println("The type descriptor of this local variable." + localVariableNode.desc);
//            System.out.println("The local variable's index." + localVariableNode.index);
//            System.out.println("The name of a local variable." + localVariableNode.name);
//            System.out.println("The signature of this local variable." + localVariableNode.signature);
//        }
        accept(mv);
    }

}
