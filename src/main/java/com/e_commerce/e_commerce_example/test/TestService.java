package com.e_commerce.e_commerce_example.test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    public List<String> method3(String param1, String param2) {
        List<String> list = new ArrayList<>();
        list.add(param1);
        list.add(param2);

        return list;
    }
}
