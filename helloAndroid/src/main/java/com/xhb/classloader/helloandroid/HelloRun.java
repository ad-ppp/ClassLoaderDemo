package com.xhb.classloader.helloandroid;

import spica.exception.SpicaException;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 打包一个可执行 jar 运行示例
public class HelloRun {
    private static int count = 0;
    private static ScheduledExecutorService scheduledExecutorService;

    public static void main(String[] args) {
        System.out.println(String.format("输入的参数 args:[%s]", spanArgs(args)));
        System.out.println("test crash after 20s");

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                helloAndroid();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private static void helloAndroid() {
        count++;
        ISayHello hello = new HelloAndroid();
        System.out.println(String.format(Locale.ENGLISH, "[%d]\t%s", count, hello.say()));

        if (count == 20) {
            scheduledExecutorService.shutdown();
            try {
                throw new SpicaException("spica exception...");
            } catch (SpicaException e) {
                e.printStackTrace();
            }
        }
    }

    private static String spanArgs(String[] args) {
        if (args.length == 0) {
            return null;
        }

        final StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg).append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
