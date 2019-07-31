package com.example;

public class Add {
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 编写手动测试
     * @param args
     */
    public static void main(String[] args) {
        Add add = new Add();
        if (add.add(1, 1) == 2) {
            System.out.println("Test pass");
        } else {
            System.out.println("Test fail");
        }
    }
}

