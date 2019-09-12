package com.jaafar.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.poc.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 8/2/2019 1:20 PM
 */
@RunWith(JUnit4.class)
public class PoCTests {

    @Test
    public void test() {
        String content = "adadfadsf\n";
        boolean b = this.checkUTF8Control(content);
        Assert.assertTrue(b);

        b = this.checkDigital("0123");
        Assert.assertTrue(b);
    }

    private boolean checkUTF8Control(String content) {
        String format = ".*[\\x00-\\x1F\\x7F-\\x9F].*";
        return Pattern.matches(format, content);
    }

    private boolean checkDigital(String content) {
        String format = "^\\d+$";
        return Pattern.matches(format, content);
    }

    @Test
    public void testLambdaSequence() {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            list.add(String.valueOf(i));
//        }
//        list.parallelStream().forEach((item) -> {
//            int i = list.indexOf(item);
//            System.out.println(i);
//        });
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.getAndIncrement());
        System.out.println(i.getAndIncrement());
        System.out.println(i.getAndIncrement());
        System.out.println(i.getAndIncrement());
        System.out.println(i.getAndIncrement());
        System.out.println(i.getAndIncrement());




    }

    @Test
    public void testAtomicIntegerForLeveraginInLambda() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        strings.add("f");
        strings.add("g");
        strings.add("h");
        strings.add("i");
        strings.add("j");

//        int i = 0;
//        strings.stream().collect(Collectors.toMap(s -> i ++, s -> s));

        AtomicInteger atomicInteger = new AtomicInteger();
        strings.stream().collect(Collectors.toMap(s -> atomicInteger.getAndIncrement(), s -> s));

        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
    }


    @Test
    public void getFormattedESTDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String dateTime = simpleDateFormat.format(new Date());
        System.out.println(dateTime);
    }

    @Test
    public void testBooleanNull() {
        Boolean aBoolean = Boolean.valueOf(null);
        Assert.assertFalse(aBoolean);
    }

    @Test
    public void testEmailFormat() {
        String pattern = "^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String email = "office@decatur-coca-cola.com";
        boolean matches = Pattern.matches(pattern, email);
        Assert.assertFalse(matches);
    }

    @Test
    public void testDefaultPriorityQueue() {
        Queue<String> queue = new PriorityQueue<>();

        queue.offer("A");
        queue.offer("a");
        queue.offer("b");
        queue.offer("B");

        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }

    @Test
    public void testPriorityQueue() {
        Queue<String> queue = new PriorityQueue<>(new TestComparator());

        queue.offer("A01");
        queue.offer("A02");
        queue.offer("V01");
        queue.offer("V02");

        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }

    class TestComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String category1 = o1.substring(0, 1);
            String category2 = o2.substring(0, 1);
            String number1 = o1.substring(1);
            String number2 = o2.substring(1);

            if (category1.equals(category2)) {
                return Integer.valueOf(number1) - Integer.valueOf(number2);
            } else {
                return "V".equals(category1) ? -1 : 1;
            }
        }
    }

    @Test
    public void testStreamBeenClosedIssue() {
        List<String> strings = new ArrayList<>();
        strings.add(null);

        // will throw below exception at strings2 variable:
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        Stream<String> stringStream = strings.stream().filter(Objects::nonNull);
        List<String> strings1 = stringStream.collect(Collectors.toList());
//        List<String> strings2 = stringStream.collect(Collectors.toList());

        // supplier.get() will create new stream object, this makes the lambda operation can be reused.
        // but unknown whether the get() method will executed each time when calling
        // unfortunately, it will. System.ou.println(s) will print twice.
        Supplier<Stream<String>> supplier = () -> strings.stream().filter(s -> {
            System.out.println(s);
            return s != null;
        });
        List<String> collect1 = supplier.get().collect(Collectors.toList());
        List<String> collect2 = supplier.get().collect(Collectors.toList());
    }

    @Test
    public void testSerialize() throws IOException {
        String json = "12312312xxxx";

        new ObjectMapper().readValue(json, User.class);
    }

    @Test
    public void testStringSubString() {
        String content = "API returned 123 adfasdfasdf";
        boolean api_returned_ = content.startsWith("API returned ") & "123".equals(content.substring(13, 16));
        Assert.assertTrue(api_returned_);
    }

    @Test
    public void testDynamicParameters() {
        User user = new User();
        user.setAnswerOne("1");
        user.setAnswerTwo("2");
        user.setPassword("123");
        User user1 = user.protectSensitiveData();
        System.out.println(user1);
    }


}
