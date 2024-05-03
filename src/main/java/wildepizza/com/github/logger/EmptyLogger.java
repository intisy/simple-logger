package wildepizza.com.github.logger;

public class EmptyLogger extends Logger {
    public EmptyLogger(int logLevel, boolean enableShortLog, boolean enablePercent) {
        super(logLevel, enableShortLog, enablePercent);
    }
    @Override
    public void log(Object log, boolean m) {
    }
}
