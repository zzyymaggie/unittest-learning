package com.example.powermock;

import com.example.Add;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;
@RunWith(PowerMockRunner.class)
@PrepareForTest(Add.class)
public class PowerMockSampleTest {

    @Test
    public void testMockStaticMethod() {
        mockStatic(Add.class);
        when(Add.getDouble(1)).thenReturn(3);

        int actual = Add.getDouble(1);
        assertEquals(3, actual);

        verifyStatic();
    }
}
