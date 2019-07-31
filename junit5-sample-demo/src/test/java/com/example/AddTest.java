package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Add Tester.
 *
 * @author zhangyu
 * @version 1.0
 * @since <pre>六月 2, 2019</pre>
 */
public class AddTest {
    public static Add add;

    @BeforeAll // 在所有测试方法运行前运行，并且只能修饰静态方法（除非修改测试实例生命周期）
    public static void setUp() throws Exception {

        add = new Add();
    }

    @Test // 表示这是个测试方法
    void add() {

        // 断言相等，比较2和add.add(1,1)的返回值是否相等
        assertEquals(2, add.add(1, 1));
    }

    @ParameterizedTest
    @DisplayName("Condition Decision coverage sample test result true")
    @CsvSource({"0,2,2",
            "1,0,1",
            "-1, 1, 0",
            "2147483647, 2147483647, -2",
            "-2147483647, -2147483647, 2",
            "2147483647, -2147483647, 0"})
    public void testAdd3(int a, int b, int c) {
        Assertions.assertEquals(c, add.add(a, b));
    }
}
