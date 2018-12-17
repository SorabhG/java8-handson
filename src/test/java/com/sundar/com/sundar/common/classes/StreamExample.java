package com.sundar.com.sundar.common.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StreamExample {

    private long counter;

    private void wasCalled() {
        counter++;
    }


    @Before
    public void executedBeforeEach() {
        System.out.println("@Before: executedBeforeEach");
    }

    @Test
    public void whenAnElementExits_thenFilter() {
        Optional<String> anyElement = Stream.of("a", "b", "c").filter(e -> e.contains("b")).findAny();
        Assert.assertEquals("Optional[b]", anyElement.toString());
        Assert.assertEquals("b", anyElement.get());
        assertThat(anyElement.get(), equalTo("b"));
        Assert.assertTrue(anyElement.isPresent());
        System.out.println(anyElement);
    }

    @Test
    public void whenAElementExitsinStream_thenFilter2() {
        List<String> stringList = Stream.of("a", "b", "c").filter(e -> e.contains("b")).collect(Collectors.toList());
        Optional<String> anyElement = stringList.stream().findFirst();
        assertTrue(anyElement.isPresent());
        assertEquals("b", anyElement.get());
    }

    @Test
    public void only1TerminalOperationPerStream() {
        Stream.of("abcd", "bbcd", "cbcd").skip(1).map(e -> e.substring(0, 3)).forEach(System.out::println);
        Stream.of("abcd", "bbcd", "cbcd").skip(1).map(e -> e.substring(0, 3)).collect(Collectors.toList()).forEach(System.out::println);
        long count = Stream.of("abcd", "bbcd", "cbcd").skip(1).map(e -> e.substring(0, 3)).count();
        assertEquals(2, count);
    }

    @Test
/*
    pipeline executes vertically. In our example the first element of the stream didn’t satisfy filter’s predicate,
    then the filter() method was invoked for the second element, which passed the filter. Without calling the filter()
    for third element we went down through pipeline to the map() method.
    The findFirst() operation satisfies by just one element. So, in this particular example the lazy invocation allowed to avoid
    two method calls – one for the filter() and one for the map().
*/
    public void intermediateOpsAreLazy() {
        List<String> strings = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        strings.stream().filter(e -> {
            System.out.println("Filter was called");
            wasCalled();
            return e.contains("3");
        });
        assertEquals(0, counter);


        Optional<String> first = strings.stream().filter(e -> {
            System.out.println("Filter was being called");
            wasCalled();
            return e.contains("2");
        }).map(e -> {
            System.out.println("Map is called");
            return e.toUpperCase();
        }).findFirst();
        System.out.println(first.get());
        assertEquals(2, counter);

    }


}
