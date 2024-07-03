package com.github.WildePizza;

import java.io.File;

@SuppressWarnings("unused")
public class LoggerBuilder {
    private final SimpleLogger logger = new SimpleLogger();
    public LoggerBuilder withLogLevel(int level) {
        logger.setLogLevel(level);
        return this;
    }
    public LoggerBuilder withEnablePercent(boolean enable) {
        logger.setEnablePercent(enable);
        return this;
    }
    public LoggerBuilder withEnableLogToFile(boolean enable) {
        logger.setEnableLogToFile(enable);
        return this;
    }
    public LoggerBuilder withLogFile(File file) {
        logger.setLogFile(file);
        return this;
    }
    public LoggerBuilder withEnableDuplicateLog(boolean enable) {
        logger.setEnableDuplicateLog(enable);
        return this;
    }
    public SimpleLogger build() {
        return logger;
    }
}
