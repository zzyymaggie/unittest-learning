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
 * @since <pre>���� 2, 2019</pre>
 */
public class AddTest {
    public static Add add;

    @BeforeAll // �����в��Է�������ǰ���У�����ֻ�����ξ�̬�����������޸Ĳ���ʵ���������ڣ�
    public static void setUp() throws Exception {

        add = new Add();
    }

    @Test // ��ʾ���Ǹ����Է���
    void add() {

        // ������ȣ��Ƚ�2��add.add(1,1)�ķ���ֵ�Ƿ����
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
