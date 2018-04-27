package com.shiluns.netty.test;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author fengchuanbo
 */
public class StreamTest {

    @Test
    public void testNativeCollectionNullCheck(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Optional.ofNullable(list).orElse(Collections.emptyList()).stream().forEach(System.out::println);
    }

    @Test
    public void testCollectionUtilsNullCheck(){
        // 1. Collection.stream()
        // 2. Stream.of()
        // 3. Arrays.stream()
        // 4. Stream.iterator(0, (x) -> x + 1);
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        ListUtils.emptyIfNull(list).stream().forEach(System.out::println);
    }
}
