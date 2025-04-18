package io.github.intisy.simple.logger;

/**
 * @author Finn Birich
 */
@SuppressWarnings("unused")
public class LoggerPresets {
    public static final SimpleLogger DEFAULT = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();
    public static final SimpleLogger DEBUG = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();
    public static final SimpleLogger INFO = new LoggerBuilder().withLogLevel(LogLevel.NOTE).build();
    public static final SimpleLogger WARNING = new LoggerBuilder().withLogLevel(LogLevel.WARN).build();
    public static final SimpleLogger ERROR = new LoggerBuilder().withLogLevel(LogLevel.MAJOR).build();
    public static final SimpleLogger SUCCESS = new LoggerBuilder().withLogLevel(LogLevel.SUCCESS).build();
    public static final SimpleLogger MAJOR = new LoggerBuilder().withLogLevel(LogLevel.MAJOR).build();
}
