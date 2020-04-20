package com.sorftbuild.program.model;

public class Exercise {
    private int id;             // 从开始
    private int leftPart;		// 式子左边部分
    private char op;			// 式子运算符
    private int rightPart;		// 式子右边部分
    private int result;			// 式子结果



    public int getLeftPart() {
        return leftPart;
    }
    public void setLeftPart(int leftPart) {
        this.leftPart = leftPart;
    }
    public char getOp() {
        return op;
    }
    public void setOp(char op) {
        this.op = op;
    }
    public int getRightPart() {
        return rightPart;
    }
    public void setRightPart(int rightPart) {
        this.rightPart = rightPart;
    }
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
}
