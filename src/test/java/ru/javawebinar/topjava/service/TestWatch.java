package ru.javawebinar.topjava.service;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TestWatch extends TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(TestWatch.class);

    static String watchedLog = "";

    private LocalDateTime beginDateTime;

    @Override
    public Statement apply(Statement base, Description description) {
        return super.apply(base, description);
    }

    @Override
    protected void succeeded(Description description) {
        watchedLog +="\n" + "NameTest: " + description.getDisplayName() + " " + "success!\n";
    }

    @Override
    protected void failed(Throwable e, Description description) {
        watchedLog += "NameTest: " + description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        watchedLog += "NameTest: " + description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);
        beginDateTime = LocalDateTime.now();

    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        watchedLog += "BeginTime: " + beginDateTime + ", ";
        long timeLine = ChronoUnit.MILLIS.between(beginDateTime, LocalDateTime.now());
        watchedLog += "EndTime: " + LocalDateTime.now() + ", Time: " +
                timeLine + " ms\n";

        logger.info("Duration of the test: " + description.getDisplayName() + ": " + timeLine + "ms");

    }


}
