package wildepizza.com.github.simplelogger;

public class EmptyLogger extends Logger {
    public EmptyLogger(int logLevel, boolean enableShortLog, boolean enablePercent) {
        super(logLevel, enableShortLog, enablePercent);
    }
    @Override
    public void log(Object log, boolean m) {
    }
}
