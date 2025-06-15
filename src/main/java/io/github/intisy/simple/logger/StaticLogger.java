package io.github.intisy.simple.logger;

/**
 * @author Finn Birich
 */
@SuppressWarnings("unused")
public class StaticLogger {
    private static final SimpleLogger logger = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();
    public static void debug(Object string, Object... args) {
        debug(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void debug(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.debug(string, 4, args);
    }
    public static void note(Object string, Object... args) {
        note(string, SimpleLogger.LogMode.NORMAL, args);
    }
    public static void note(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.note(string, args);
    }
    public static void info(Object string, Object... args) {
        info(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void info(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.note(string, args);
    }
    public static void printStackTrace() {
        logger.printStackTrace();
    }
    public static void success(Object string, Object... args) {
        success(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void success(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.success(string, args);
    }
    public static void warning(Object string, Object... args) {
        warning(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void warning(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.warning(string, 4, args);
    }
    public static void warn(Object string, Object... args) {
        warn(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void warn(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.warning(string, 4, args);
    }
    public static void major(Object string, Object... args) {
        major(string, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void major(Object string, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.major(string, args);
    }
    public static void error(Object log, Object... args) {
        error(log, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void error(Object log, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.error(log, 5, args);
    }
    public static void error(Object log, Exception exception, Object... args) {
        error(log, exception, SimpleLogger.LogMode.NORMAL, args);
    }

    public static void error(Object log, Exception exception, SimpleLogger.LogMode logMode, Object... args) {
        logger.setLogMode(logMode);
        logger.error(log, exception, 4, args);
    }
}
