package com.github.WildePizza;

@SuppressWarnings("unused")
public class EmptyLogger extends SimpleLogger {
    public EmptyLogger() {
        super();
        setLogLevel(0);
    }
    @Override
    public void log(Object log, boolean m) {
    }
}
