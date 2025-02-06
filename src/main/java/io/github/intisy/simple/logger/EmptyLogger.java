package io.github.intisy.simple.logger;

/**
 * @author Finn Birich
 */
@SuppressWarnings("unused")
public class EmptyLogger extends SimpleLogger {
    public EmptyLogger() {
        super();
        setLogLevel(0);
    }
    @Override
    public void log(Object log, boolean isMajor, LogMode logMode, Object... args) {
        // Do nothing
    }
}
