package com.lucas.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucasfen
 * @Date 2021/06/22
 */
public class ListPartitionUtils {

    public static List<List<String>> groupList(List<String> list, int subListLength) {
        List<List<String>> listGroup = new ArrayList<List<String>>();
        int listSize = list.size();
        int toIndex = subListLength;
        for (int i = 0; i < list.size(); i += subListLength) {
            if (i + subListLength > list.size()) {
                toIndex = listSize - i;
            }
            List<String> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }
}
