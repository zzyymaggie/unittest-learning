package com.example;

import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Add Tester.
 *
 * @author zhangyu
 * @version 1.0
 * @since <pre>六月 2, 2019</pre>
 */
public class AddTest {
    private static Add add;

    @BeforeClass
    public static void beforeClass() throws  Exception {
        System.out.println("before class");
        add = new Add();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("after class");
    }

    @Before
    public void before() throws Exception {
        System.out.println("before method");
    }

    @After
    public void after() throws Exception {
        System.out.println("after method");
    }

    /**
     * 相加测试用例
     */
    @Test
    public void testAdd() throws Exception {
        assertEquals(2, add.add(1, 1));
        assertEquals(0, add.add(-1, 1));
    }

    /**
     * 临界值测试用例
     */
    @Test
    public void testAdd2() throws Exception {
        System.out.format("%s + %s = %s\n", Integer.MAX_VALUE, Integer.MAX_VALUE, add.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
        System.out.format("%s + %s = %s\n", Integer.MIN_VALUE, Integer.MIN_VALUE, add.add(Integer.MIN_VALUE, Integer.MIN_VALUE));
        System.out.format("%s + %s = %s\n", Integer.MAX_VALUE, Integer.MIN_VALUE, add.add(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    @Ignore
    public void testAdd3() throws Exception {
        assertEquals(-1, add.add(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }
}
