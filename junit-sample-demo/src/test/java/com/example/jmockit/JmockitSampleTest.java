package com.example.jmockit;

import com.example.Add;
import mockit.Expectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Jmockit可以和junit和TestNG配合使用。需要注意的是：
 *
 * 如果使用Junit4.5以上，jmockit依赖需要在junit4之前；或者在测试类上添加注解 @RunWith(JMockit.class)。
 * 如果是TestNG 6.2+ 或者 JUnit 5+， 没有位置限制
 */
@RunWith(JMockit.class)
public class JmockitSampleTest {
    @Test
    public void testMockStaticMethod() {
        new Expectations(Add.class) {
            {
                Add.getDouble(1);
                result = 3;
            }
        };

        assertEquals(3, Add.getDouble(1));

        new Verifications() {
            {
                Add.getDouble(1);
                times = 1;
            }
        };
    }
}
