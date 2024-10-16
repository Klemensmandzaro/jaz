package org.example;

public class Job implements IWork{
    public Job(IRunNotSafeAction action, IScheduleWork scheduler) {
    }

    private IRunNotSafeAction action;
    private IProvideNextExecutionTime nextTimeProvider = () -> null;
    private IHandleErrors handleExeptions = ex->{};
    private IComplete singleActionCompleted =()->{};
    private IComplete completed = ()->{};
    private IScheduleWork scheduler;

    @Override
    public IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider) {
        return null/*()->timeProvider.provideNextExecutionTime()*/;
    }

    @Override
    public IWork onError(IHandleErrors errorHandler) {
        return null;
    }

    @Override
    public IWork onSingleActionCompleted(IComplete onSingleActionCompleted) {
        return null;
    }

    @Override
    public IWork onCompleted(IComplete onCompleted) {
        return null;
    }

    @Override
    public void schedule() {
        this.scheduler.addJob(this);
    }

    @Override
    public void execute() {

    }



}
