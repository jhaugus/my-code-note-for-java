package com.base.ee007_Timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {
    public static void main(String[] args) {
        Timer timer = new Timer();

        // 创建一个 TimerTask 任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务执行！");
            }
        };
        // 安排任务在延迟 2 秒后，每 1 秒执行一次
        timer.schedule(task, 2000, 1000);
    }
}