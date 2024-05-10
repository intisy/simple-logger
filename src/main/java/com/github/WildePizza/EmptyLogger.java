package com.github.WildePizza;

@SuppressWarnings("unused")
public class EmptyLogger extends SimpleLogger {
    public EmptyLogger() {
        super(0, false, false);
    }
    @Override
    public void log(Object log, boolean m) {
    }
}
