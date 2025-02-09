package com.e_commerce.e_commerce_example.test;

import java.util.ArrayList;
import java.util.List;

public class St2 {
    public static TestService testService = new TestService();

    public static List<String> method2(String param1, String param2) {
        List<String> temp1 = testService.method3(param1, param2);

        temp1.add("three");
        temp1.add("four");

        return temp1;
    }
}
