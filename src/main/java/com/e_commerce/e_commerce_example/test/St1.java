package com.e_commerce.e_commerce_example.test;

import java.util.ArrayList;
import java.util.List;

public class St1 {
    public static List<String> method1() {
        List<String> list = St2.method2("one", "two");
        list.add("five");
        list.add("six");

        return list;
    }
}
