package com.sundar;

import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by SorabhG on 10/10/2018.
 */
public class SortHuman {

    List<Human> humans;

    @Before
    public void executedBeforeEach() {
        humans = constructHumanList();
        System.out.println("@Before: executedBeforeEach");
    }

    @Test
    public void sortByName_withBasicLambda_withNoTypeDefination() {
        Collections.sort(humans, (o1, o2) ->o1.getName().compareTo(o2.getName())


        );
        //humans.stream().forEach(System.out::println);
        assertThat(humans.get(0), equalTo(new Human("Ajay", 15)));
        assertThat(humans.get(5), equalTo(new Human("Tejas", 35)));
        System.out.println(humans);
    }

    @Test
    public void sortUsingReferenceToStaticMethod_ByNameThenAge_thenCorrectlySorted() {
        humans.sort(Human::compareByNameThenAge);
        assertThat(humans.get(0), equalTo(new Human("Ajay", 15)));
        System.out.println(humans);
    }

    @Test
    //Comparator.comparing method – which extracts and creates a Comparable based on that function.
    public void extractAndCreateComparableBasedFun_sortByNameThenAge() {
        Collections.sort(humans, Comparator.comparing(Human::getName).thenComparing(Human::getAge));
        assertThat(humans.get(0), equalTo(new Human("Ajay", 15)));
        System.out.println(humans);

    }


    @Test
    //Comparator.comparing method – which extracts and creates a Comparable based on that function.
    public void extractAndCreateComparableBasedFun_sortByNameThenAge_reverse() {
        Collections.sort(humans, Comparator.comparing(Human::getName).thenComparing(Human::getAge).reversed());
        assertThat(humans.get(0), equalTo(new Human("Tejas", 35)));
        System.out.println(humans);

    }

    private List<Human> constructHumanList() {
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Jack", 15));
        humans.add(new Human("Ajay", 15));
        humans.add(new Human("Tejas", 35));
        humans.add(new Human("Govind", 25));
        humans.add(new Human("Dholeshwar", 10));
        humans.add(new Human("Jill", 25));
        return humans;
    }


}
