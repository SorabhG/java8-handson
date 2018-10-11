package com.sundar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by SorabhG on 10/10/2018.
 */
public class SortingExample {
    List<String> expected = Arrays.asList("abc", "ajay", "bac", "jay", "sorabh");
    List<String> names;


    @Before
    public void executedBeforeEach() {
        names = Arrays.asList("abc", "bac", "jay", "ajay", "sorabh");
        System.out.println("@Before: executedBeforeEach");
    }

    @Test
    public void sortInJava8() {
        Collections.sort(names, (a, b) -> a.compareTo(b));
        names.forEach(System.out::println);
        Assert.assertEquals(5, names.size());
        assertThat(names, equalTo(expected));
        assertThat(names, is(expected));

    }

    @Test
    //We can sort the stream using natural ordering as well as ordering provided by a Comparator
    public void anotherWayToSort() {
        List<String> actual = names.stream().sorted().collect(Collectors.toList());
        System.out.println(names);
        Assert.assertEquals(5, names.size());
        assertThat(actual, equalTo(expected));
        assertThat(actual, is(expected));
    }
}
