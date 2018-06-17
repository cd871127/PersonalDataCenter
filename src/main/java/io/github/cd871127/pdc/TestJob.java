package io.github.cd871127.pdc;

import io.github.cd871127.hodgepodge.quartz.job.SimpleJob;

import java.util.Map;

public class TestJob extends SimpleJob {
    @Override
    protected void doTask(Map<String, String> jobDataMap) {
        if (jobDataMap != null)
            jobDataMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
