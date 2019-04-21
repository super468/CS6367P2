package edu.utd;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


class MethodTransformVisitor extends MethodVisitor implements Opcodes {


    private String className;
    private int line;

    /**
     * Since we only care the className not the methodName in this project.
     * so we input the className
     * @param mv
     * @param className
     */
    public MethodTransformVisitor(final MethodVisitor mv, String className) {
        super(ASM5, mv);
        this.className = className;
    }


    @Override
    public void visitLineNumber(int line, Label start) {
        if (0 != line) {
            this.line = line;
            /**
             * Generated by ASMfier
             * Equals to edu.utd.CoverageCollection.visitLine("className, 1)
             */
            mv.visitLdcInsn(className);
            mv.visitLdcInsn(new Integer(line));
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "edu/utd/CoverageCollection", "visitLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
            super.visitLineNumber(line, start);
        }
    }

    @Override
    public void visitLabel(Label arg0) {
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(new Integer(this.line));
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKESTATIC, "edu/utd/CoverageCollection", "visitLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        super.visitLabel(arg0);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
