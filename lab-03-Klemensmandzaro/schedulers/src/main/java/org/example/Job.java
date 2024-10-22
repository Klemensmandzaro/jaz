package org.example;

public class Job implements IWork{
    public Job(IRunNotSafeAction action, IScheduleWork scheduler) {
        this.action = action;
        this.scheduler = scheduler;
    }

    private IRunNotSafeAction action;
    private IProvideNextExecutionTime nextTimeProvider = () -> null;
    private IHandleErrors handleExeptions = ex->{};
    private IComplete singleActionCompleted =()->{};
    private IComplete completed = ()->{};
    private IScheduleWork scheduler;

    @Override
    public IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider) {
        this.nextTimeProvider = timeProvider;
        return this;
    }

    @Override
    public IWork onError(IHandleErrors errorHandler) {
        this.handleExeptions = errorHandler;
        return this;
    }

    @Override
    public IWork onSingleActionCompleted(IComplete onSingleActionCompleted) {
        this.singleActionCompleted = onSingleActionCompleted;
        return this;
    }

    @Override
    public IWork onCompleted(IComplete onCompleted) {
        completed = onCompleted;
        return this;
    }

    @Override
    public void Schedule() {
        scheduler.addJob(this);
    }

    @Override
    public void execute() {
        try {
            action.executeNotSafeAction();
            singleActionCompleted.complete();
            if (nextTimeProvider.provideNextExecutionTime() == null)
            {
                completed.complete();
            }
        } catch (Exception e) {
            handleExeptions.handle(e);
        }

    }



}
