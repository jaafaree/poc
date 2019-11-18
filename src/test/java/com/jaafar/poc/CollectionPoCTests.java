package com.jaafar.poc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 11/18/2019 4:06 PM
 */
@RunWith(JUnit4.class)
public class CollectionPoCTests {


    @Test
    public void testForeachParallel() {
        List<Integer> objects = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15);
        List<Integer> integers = this.parallelForeach(objects, 4);
        System.out.println(integers);
        Assert.assertEquals(objects, integers);
    }


    private List<Integer> parallelForeach(List<Integer> list, int threadCount) {
        // TODO: 11/18/2019 parameters' validation
        int step = list.size() % threadCount == 0 ? list.size() / threadCount : list.size() / threadCount + 1;
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        List<Future<List<Integer>>> futures = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            List<Integer> sub = list.subList(i * step, (i + 1) * step > list.size() ? list.size() : (i + 1) * step);
            Callable<List<Integer>> callable = new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    List<Integer> rst = new ArrayList<>(sub.size());
                    for (Integer e : sub) {
                        rst.add(e);
                    }
                    return rst;
                }
            };
            futures.add(service.submit(callable));
        }
        return futures.stream()
                .map(listFuture -> {
                    try {
                        return listFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .reduce((e1, e2) -> {
                    e1.addAll(e2);
                    return e1;
                })
        .orElse(new ArrayList<>());
    }


}
