package org.example;

import java.util.ArrayList;
import java.util.List;

public class Scheduler implements IScheduleWork{
    private static Scheduler instance;
    private Scheduler(){}
    List<IWork> jobs = new ArrayList<>();

    @Override
    public IWork forAction(IRunNotSafeAction action) {
        return new Job(action, instance);
    }

    @Override
    public List<IWork> getJobs() {
        return jobs;
    }

    @Override
    public void addJob(IWork job) {
        jobs.add(job);
    }

    static{
        instance = new Scheduler();
    }

    static Scheduler getInstance() {
        return instance;
    }
}
