package com.juc;

// jmh_performance 测试
// java -jar -Xmx2G benchmarks.jar
import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.FutureTask;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class juc_demo_parallel_demo {


    static int[] arr  = new int[1000_000_00];
    static {
        Arrays.fill(arr, 1);
    }


    @Benchmark
    public int c() throws Exception{
        int[] array = arr;
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            int sum = 0;
            for(int i = 0 ; i < 250_000_00; i++){
                sum += array[0+i];
            }
            return sum;
        });
        FutureTask<Integer> t2 = new FutureTask<>(() -> {
            int sum = 0;
            for(int i = 0 ; i < 250_000_00; i++){
                sum += array[250_000_00+i];
            }
            return sum;
        });
        FutureTask<Integer> t3 = new FutureTask<>(() -> {
            int sum = 0;
            for(int i = 0 ; i < 250_000_00; i++){
                sum += array[500_000_00+i];
            }
            return sum;
        });
        FutureTask<Integer> t4 = new FutureTask<>(() -> {
            int sum = 0;
            for(int i = 0 ; i < 250_000_00; i++){
                sum += array[750_000_00+i];
            }
            return sum;
        });

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        return t1.get() + t2.get() + t3.get() + t4.get();
    }


    @Benchmark
    public int d() throws Exception{
        int[] array = arr;
        FutureTask<Integer> t = new FutureTask<>(() -> {
            int sum = 0;
            for(int i = 0 ; i < 1000_000_00; i++){
                sum += array[i];
            }
            return sum;
        });
        new Thread(t).start();
        return t.get();
    }



}
