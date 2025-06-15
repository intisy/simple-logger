package io.github.intisy.simple.logger;

/**
 * @author Finn Birich
 */
@SuppressWarnings("unused")
public class StaticLogger {
    private static final SimpleLogger logger = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();

    public static void debug(Object string, Object... args) {
        logger.debug(string, 5, args);
    }

    public static void debug(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.debug(string, 4, logMode, args);
    }

    public static void note(Object string, Object... args) {
        logger.note(string, args);
    }

    public static void note(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.note(string, logMode, args);
    }

    public static void info(Object string, Object... args) {
        info(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void info(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.note(string, logMode, args);
    }

    public static void printStackTrace() {
        logger.printStackTrace();
    }

    public static void success(Object string, Object... args) {
        success(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void success(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.success(string, logMode, args);
    }

    public static void warning(Object string, Object... args) {
        logger.warning(string, 4, args);
    }

    public static void warning(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.warning(string, 4, logMode, args);
    }

    public static void warn(Object string, Object... args) {
        logger.warning(string, 4, args);
    }

    public static void warn(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.warning(string, 4, logMode, args);
    }

    public static void major(Object string, Object... args) {
        major(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void major(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.major(string, logMode, args);
    }

    public static void error(Object log, Object... args) {
        logger.error(log, 5, args);
    }

    public static void error(Object log, SimpleLogger.LogMode logMode, Object... args) {
        logger.error(log, 5, logMode, args);
    }

    public static void error(Object log, Exception exception, Object... args) {
        logger.error(log, exception, 4, args);
    }

    public static void error(Object log, Exception exception, SimpleLogger.LogMode logMode, Object... args) {
        logger.error(log, exception, 4, logMode, args);
    }
}