package com.ds.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

/**
 * @author writiger
 * @description 测试重复率
 * @create_at 2024-03-12 14:10
 */
public class UUIDTest {
    @Test
    public void generateUUIDTest() throws InterruptedException {

        CyclicBarrier barrier=new CyclicBarrier(50);
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100; j++) {
                        strings.add(ShortUUID.generateShortUUID());
                    }
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        sleep(1000);
        System.out.println(strings.size());
    }
}
