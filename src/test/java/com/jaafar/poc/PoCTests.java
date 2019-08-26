package com.jaafar.poc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

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



}
