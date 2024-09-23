package io.github.intisy.simple.logger;

public class StaticLogger {
    private static final SimpleLogger logger = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();
    public static void debug(Object string) {
        logger.debug(string, 4);
    }
    public static void debug(Object string, boolean log) {
        if (log) {
            logger.debug(string, 4);
        }
    }
    public static void note(Object string) {
        logger.note(string);
    }
    public static void printStackTrace(Object string) {
        logger.printStackTrace();
    }
    public static void success(Object string) {
        logger.success(string);
    }
    public static void warning(Object string) {
        logger.warning(string, 4);
    }
    public static void major(Object string) {
        logger.major(string);
    }
    public static void error(Object log) {
        logger.error(log, 4);
    }
    public static void exception(Exception exception) {
        exception(exception, null);
    }
    public static void exception(Exception exception, String message) {
        logger.exception(exception, message);
    }
}
