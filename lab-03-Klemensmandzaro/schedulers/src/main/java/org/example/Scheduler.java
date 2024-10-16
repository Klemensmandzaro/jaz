package org.example;

import java.util.List;

public class Scheduler implements IScheduleWork{
    private static Scheduler instance;
    private Scheduler(){}
    List<IWork> jobs;

    @Override
    public IWork forAction(IRunNotSafeAction action) {
        return action.executeNotSafeAction();
    }

    @Override
    public List<IWork> getJobs() {
        return List.of();
    }

    @Override
    public void addJob(IWork job) {

    }

    static{
        instance = new Scheduler();
    }

    static Scheduler getInstance() {
        return instance;
    }
}
