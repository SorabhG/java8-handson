package com.sundar;

import com.sundar.com.sundar.common.classes.Modem;
import org.junit.Test;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.Assert.*;

/**
 * Created by SorabhG on 11/10/2018.
 */
public class OptionalExample {

    @Test
    public void whenCreateEmptyOptional_thencorrect(){
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }

    @Test
    //getting va
    public void givenNonNull_whenCreateOptional_thenCorrect(){
        String name = "Sorabh";
        Optional<String> name1 = Optional.of(name);
        String s = name1.get();
        assertEquals(s,name);
        assertTrue(name1.isPresent());
        assertEquals("Optional[Sorabh]",name1.toString());
    }

    @Test(expected = NoSuchElementException.class)
    //the argument passed to the of method cannot be null, otherwise, we will get a NullPointerException:
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect(){
        Optional<String> name1 = Optional.ofNullable(null);
        String s = name1.get();
    }

    @Test(expected = NullPointerException.class)
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect(){
        String name = null;
        Optional<String> name1 = Optional.of(name);
    }

    @Test
    //But, in case we expect some null values for the passed in argument, we can use the ofNullable API:
    public void givenNonNull_whenCreatesNullable_thenCorrect(){
        String name = null;
        Optional<String> name1 = Optional.ofNullable(name);
        System.out.println(name1);
        assertFalse(name1.isPresent());
    }

    @Test
    //Conditional Action With ifPresent()
    public void givenOptional_whenIfPresentWorks_thenCorrect(){
        Optional<String> sorabh = Optional.of("Sorabh");
        sorabh.ifPresent(abc-> System.out.println(abc.length()));
        sorabh.ifPresent(a->assertEquals(6,a.length()));
    }

    @Test
    //Default Value With orElse
    public void whenOrElseWorks_thenCorrect(){
        String name = null;
        String jonny = Optional.ofNullable(name).orElse("Jonny");
        assertEquals("Jonny",jonny);
    }

    @Test
    //Default Value With orElseGet
    public void whenOrElseGetWorks_thenCorrect(){
        String name = null;
        String s = Optional.ofNullable(name).orElseGet(() -> "Jonny");
        assertEquals("Jonny",s);
    }

    @Test
    //Difference Between orElse and orElseGet
    //the getMyDefault API is not even invoked with orElseGet
    public void whenOrElseGetAndOrElseDiffer_thenCorrect(){
        String text="Text Present";

        System.out.println("Using orElseGet");
        String s = Optional.ofNullable(text).orElseGet(() -> getDefault());
        assertEquals(text,s);

        System.out.println("Using orElse");
        String s1 = Optional.ofNullable(text).orElse(getDefault());
        assertEquals(text,s1);

    }

    private String getDefault(){
        System.out.println("Getting Default value");
        return "Default Value";
    }

    @Test
    //Conditional Return with filter()
    public void whenOptionalFilterWorks_thenCorrect(){
        Integer year=2016;
        Optional<Integer> year1 = Optional.of(year);
        boolean is2016 = year1.filter(y->y==2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = year1.filter(y->y==2017).isPresent();
        assertFalse(is2017);
    }

    @Test
    public void whenFilterWithoutOptional_thenCorrect(){
        assertTrue(priceInRange(new Modem(10.0)));
        assertFalse(priceInRange(new Modem(9.0)));
        assertFalse(priceInRange(new Modem(15.5)));
        assertFalse(priceInRange(new Modem(null)));
        assertFalse(priceInRange(null));
    }

    @Test
    public void whenFilterWithOptional_thenCorrect(){
        assertTrue(priceInRange2(new Modem(11.0)));
        assertFalse(priceInRange2(new Modem(9.0)));
        assertFalse(priceInRange2(new Modem(15.5)));
        assertFalse(priceInRange2(new Modem(null)));
        assertFalse(priceInRange2(null));
    }


    private boolean priceInRange(Modem modem) {
        boolean isInRange = false;
        if (modem != null && modem.getPrice() != null
                && (modem.getPrice() >= 10) && (modem.getPrice() <= 15)) {
            isInRange = true;
        }
        return isInRange;
    }

    private boolean priceInRange2(Modem modem){
        Optional<Modem> modem1 = Optional.ofNullable(modem);
        boolean present = modem1.map(Modem::getPrice).filter(p -> p > 10).filter(q -> q < 15).isPresent();
        return present;

    }




}
