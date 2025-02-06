package io.github.intisy.simple.logger;

/**
 * @author Finn Birich
 */
@SuppressWarnings("unused")
public class StaticLogger {
    private static final SimpleLogger logger = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();
    public static void debug(Object string, Object... args) {
        logger.debug(string, 4, args);
    }
    public static void note(Object string, Object... args) {
        logger.note(string, args);
    }
    public static void printStackTrace() {
        logger.printStackTrace();
    }
    public static void success(Object string, Object... args) {
        logger.success(string, args);
    }
    public static void warning(Object string, Object... args) {
        logger.warning(string, 4, args);
    }
    public static void major(Object string, Object... args) {
        logger.major(string, args);
    }
    public static void error(Object log, Object... args) {
        logger.error(log, 5, args);
    }
    public static void error(Object log, Exception exception, Object... args) {
        logger.error(log, exception, 4, args);
    }
}
