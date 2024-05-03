package com.github.WildePizza;

public class EmptyLogger extends SimpleLogger {
    public EmptyLogger(int logLevel, boolean enableShortLog, boolean enablePercent) {
        super(logLevel, enableShortLog, enablePercent);
    }
    @Override
    public void log(Object log, boolean m) {
    }
}
