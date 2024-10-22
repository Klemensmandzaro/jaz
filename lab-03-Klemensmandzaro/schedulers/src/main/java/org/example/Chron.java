package org.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chron {
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime;
    private int maxExecutionTimes=-1;
    private Duration intervalDuration = Duration.ofSeconds(1);
    private int counter=0;

    public static Chron builder() {

        return new Chron();
    }

    public Chron setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public Chron setEndDate(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Chron setMaxExecutionTimes(int maxExecutionTimes) {
        this.maxExecutionTimes = maxExecutionTimes;
        return this;
    }

    public Chron setIntervalDuration(Duration intervalDuration) {
        this.intervalDuration = intervalDuration;
        return this;
    }

    public IProvideNextExecutionTime buildNextTimeExecutionProvider() {
        return this::getNextTime;

        }

        private LocalDateTime getNextTime(){
                    if(endTime!=null)
        {
            if (startTime.isAfter(endTime)) {
                return null;
            }
            if (endTime.isBefore(LocalDateTime.now())) {
                return null;
            }
            if (maxExecutionTimes == 0)
            {
                return null;
            }
            counter++;
            maxExecutionTimes--;
            return startTime.plus(intervalDuration);
        }
        else
        {
            if (maxExecutionTimes == 0)
            {
                return null;
            }
            counter++;
            maxExecutionTimes--;
            return startTime.plus(intervalDuration);

        }
        // dodac warunki i countera
    }

}
