package com.lucas.demo.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Lucasfen
 * @Date 2021/07/01
 */
@Service
public class StreamService {

    public void testList() {
        ArrayList<String> validList = new ArrayList<>();
        validList.add("Lucas");
        validList.add("qhf");
        validList.add("aaa");
        ArrayList<String> recordList = new ArrayList<>();
        recordList.add("qhf");
        recordList.add("lucas");
        List<String> noRecordEmailList = validList.stream().filter(
                item -> recordList.stream().noneMatch(str -> str.equalsIgnoreCase(item))).collect(toList());
        System.out.println(noRecordEmailList);
    }
}
