package edu.dsm.test.function;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void streamTest() {
        //Stream 流
        Stream<String> stream1 = Stream.of("aaa", "bbb", "ccc", "bbb");
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ccc", "bbb");
        Stream<String> stream3 = Stream.of("aaa", "bbb", "ccc", "bbb");
        Stream<String> stream4 = Stream.of("aaa", "bbb", "ccc", "bbb");
        //收集流中的数据到集合中
        //1.收集流中的数据到 list
        List<String> list = stream1.collect(Collectors.toList());
        System.out.println(list);

        //2.收集流中的数据到 set
        Set<String> collect = stream2.collect(Collectors.toSet());
        System.out.println(collect);

        //3.收集流中的数据(ArrayList)(不收集到list,set等集合中,而是)收集到指定的集合中
        ArrayList<String> arrayList = stream3.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(arrayList);

        //4.收集流中的数据到 HashSet
        HashSet<String> hashSet = stream4.collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSet);
    }
}
