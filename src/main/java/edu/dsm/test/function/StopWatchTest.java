package edu.dsm.test.function;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * 测试StopWatch对程序部分代码进行计时
 */
public class StopWatchTest {
    /**
     * The entry point of application.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testStopWatch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        // 任务一模拟休眠3秒钟
        stopWatch.start("TaskOneName");
        Thread.sleep(1000 * 3);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 任务一模拟休眠10秒钟
        stopWatch.start("TaskTwoName");
        Thread.sleep(1000 * 10);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 任务一模拟休眠10秒钟
        stopWatch.start("TaskThreeName");
        Thread.sleep(1000 * 10);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 打印出耗时
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.shortSummary());
        // stop后它的值为null
        System.out.println(stopWatch.currentTaskName());

        // 最后一个任务的相关信息
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskInfo());

        // 任务总的耗时  如果你想获取到每个任务详情（包括它的任务名、耗时等等）可使用
        System.out.println("所有任务总耗时：" + stopWatch.getTotalTimeMillis());
        System.out.println("任务总数：" + stopWatch.getTaskCount());
        System.out.println("所有任务详情：" + stopWatch.getTaskInfo());
    }
}
