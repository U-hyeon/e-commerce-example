package com.e_commerce.e_commerce_example;

import com.e_commerce.e_commerce_example.test.St1;
import com.e_commerce.e_commerce_example.test.St2;
import com.e_commerce.e_commerce_example.test.TestService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockingTest {
    @Test
    public void test() {
        // SomeService를 모킹합니다.
        TestService mockedSomeService = mock(TestService.class);

        List<String> mockedData = new ArrayList<>();
        mockedData.add("하나");
        mockedData.add("둘");

        // SomeService의 method3() 호출 시 "mocked data"를 반환하도록 설정
        when(mockedSomeService.method3("one", "two")).thenReturn(mockedData);

        // ClassB의 static 필드 someService를 모킹된 객체로 설정
        // Reflection을 사용하여 private static 필드에 접근하여 모킹된 객체를 설정합니다.
        try {
            java.lang.reflect.Field testServiceField = St2.class.getDeclaredField("TestService");
            testServiceField.setAccessible(true);
            testServiceField.set(null, mockedSomeService);  // static 변수에 mock 객체 할당
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // ClassA의 method1을 호출하여, B.method2()에서 SomeService의 method3()이 mock되었는지 테스트합니다.
        List<String> result = St1.method1();

        System.out.println(result);
        // 결과가 예상되는 값인지 확인
//        assertEquals("final processed processed mocked data", result);
    }
}
