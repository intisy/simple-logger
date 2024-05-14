package com.github.WildePizza;

@SuppressWarnings("unused")
public class LoggerBuilder {
    private final SimpleLogger logger = new SimpleLogger();
    private LoggerBuilder withLogLevel(int level) {
        logger.setLogLevel(level);
        return this;
    }
    public LoggerBuilder withEnablePercent(boolean enable) {
        logger.setEnablePercent(enable);
        return this;
    }
    public LoggerBuilder withEnableShortLog(boolean enable) {
        logger.setEnableShortLog(enable);
        return this;
    }
    private SimpleLogger build() {
        return logger;
    }
}
