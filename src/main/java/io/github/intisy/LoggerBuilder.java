package io.github.intisy;

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
    public LoggerBuilder withEnableShortLog(boolean enable) {
        logger.setEnableShortLog(enable);
        return this;
    }
    public SimpleLogger build() {
        return logger;
    }
}
