package Mystudy.spring.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    @Test
    void testEqualsAndNotEquals(){

        // given
        DemoUtils demoUtils = new DemoUtils();

        int expected = 6;
        int unexpected = 8;

        // when
        int actual = demoUtils.add(2,4);

        // then
        Assertions.assertEquals(expected, actual, "2+4 must be 6");
        Assertions.assertNotEquals(unexpected, actual, "2+4 must not be 8");
    }

    @DisplayName("null인 경우엔 반드시 결과값도 null이어아 햔다.")
    @Test
    void testNullAndNotNull(){
        // given
        DemoUtils demoUtils = new DemoUtils();
        String str1 = null;
        String str2 = "luv2code";

        // when // then
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }
}