package io.github.intisy.simple.logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class SimpleLogger {
    private int logLevel;
    private int percent = 0;
    private boolean enablePercent;
    private boolean enableLogToFile;
    private boolean enableDuplicateLog;
    private String last = "";
    static String formattedDateTime;
    private File logFile;
    private File logFolder;
    private boolean enableShortLog;
    private LogMode logMode;
    final List<String> logs = new ArrayList<>();
    static {
        LocalDateTime now = LocalDateTime.now();
        String format = "yyyy-MM-dd-HH-mm";
        formattedDateTime = now.format(DateTimeFormatter.ofPattern(format));
    }
    public SimpleLogger() {
        this.enablePercent = false;
        this.logLevel = LogLevel.NOTE;
        this.enableShortLog = false;
        this.enableLogToFile = true;
        this.enableDuplicateLog = false;
        this.logMode = LogMode.LINE;
    }

    public void setLogFolder(File logFolder) {
        this.logFolder = logFolder;
    }

    public void setEnablePercent(boolean enable) {
        enablePercent = enable;
    }

    public void setEnableShortLog(boolean enable) {
        enableShortLog = enable;
    }

    public void setLogLevel(int level) {
        logLevel = level;
    }

    public void setEnableLogToFile(boolean enable) {
        enableLogToFile = enable;
    }

    public void setEnableDuplicateLog(boolean enable) {
        enableDuplicateLog = enable;
    }

    public boolean getEnableDuplicateLog() {
        return enableDuplicateLog;
    }

    public boolean getEnableLogToFile() {
        return enableLogToFile;
    }

    public boolean getEnablePercent() {
        return enablePercent;
    }

    public boolean getEnableShortLog() {
        return enableShortLog;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void addPercent(int percent) {
        this.percent += percent;
    }

    public int getPercent() {
        return this.percent;
    }

    public void setLogMode(LogMode logMode) {
        this.logMode = logMode;
    }

    public LogMode getLogMode() {
        return logMode;
    }

    private String getStackTraceElement(int line) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[line];
        String fileName = element.getFileName();
        int lineNumber = element.getLineNumber();
        return " (" + fileName + ":" + lineNumber + ")";
    }

    public void debug(Object log, Object... args) {
        debug(log, 4, args);
    }
    public void debug(Object log, int line, Object... args) {
        if (logLevel >= LogLevel.DEBUG) {
            log(LogColor.GRAY.apply(log + getStackTraceElement(line)), args);
        }
    }

    public void note(Object log, Object... args) {
        if (logLevel >= LogLevel.NOTE)
            log(LogColor.WHITE.apply(String.valueOf(log)), args);
    }

    public void success(Object log, Object... args) {
        if (logLevel >= LogLevel.SUCCESS)
            log(LogColor.GREEN.apply(String.valueOf(log)), args);
    }

    public void warning(Object log, Object... args) {
        warning(log, 4, args);
    }

    public void warning(Object log, int line, Object... args) {
        if (logLevel >= LogLevel.WARN) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[3];
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            log(LogColor.YELLOW.apply(log + getStackTraceElement(line)), args);
        }
    }

    public void major(Object log, Object... args) {
        if (logLevel >= LogLevel.MAJOR)
            log(LogColor.BLUE_BACKGROUND.apply(LogColor.GRAY.apply(String.valueOf(log))), true, args);
    }

    public void error(Object log, Object... args) {
        error(log, 5, args);
    }

    public void error(Object log, int line, Object... args) {
        error(log, null, line, args);
    }

    public void error(Exception exception, Object... args) {
        error(null, exception, 5, args);
    }

    public void error(Object log, Exception exception, Object... args) {
        error(log, exception, 4, args);
    }

    public void error(Object log, Exception exception, int line, Object... args) {
        if (logLevel >= LogLevel.WARN) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[3];
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            if (exception != null) {
                log = Utils.removeDuplicateLines(LoggerUtils.exceptionToString(exception, log));
            } else {
                log += getStackTraceElement(line);
            }
            log(LogColor.RED.apply(log.toString()), args);
        }
    }

    public void printStackTrace() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            error(element.toString());
        }
    }

    public void horrible(Exception exception, Object... args) {
        horrible(exception, null, args);
    }

    public void horrible(Object log, Object... args) {
        horrible(null, log, args);
    }

    public void horrible(Exception exception, Object log, Object... args) {
        if (log == null)
            log = "";
        if (exception != null) {
            log += LoggerUtils.exceptionToString(exception);
        }
        log(LogColor.RED.apply((String) log), args);
        System.exit(0);
    }

    public void log(Object log, Object... args) {
        log(log, false, args);
    }

    public void log(Object log, boolean isMajor, Object... args) {
        log(log, false, logMode, args);
    }

    public void log(Object log, boolean isMajor, LogMode logMode, Object... args) {
        if (enablePercent)
            log = "(" + percent + "%) " + log;
        if (!enableDuplicateLog && last.equals(String.valueOf(log)))
            return;
        else
            last = String.valueOf(log);
        if (enableLogToFile && logFolder != null) {
            try {
                Files.write(getLogFile().toPath(), log.toString().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!enableShortLog)
            if (logMode == LogMode.LINE) {
                System.out.println(log);
            } else if (logMode == LogMode.NORMAL) {
                System.out.print(log);
            } else {
                System.out.format((String) log, args);
            }
        else {
            if (isMajor) {
                System.out.println(log);
            } else
                logs.add(String.valueOf(log));
            if (!logs.isEmpty()) {
                String lastLine = logs.get(logs.size() - 1);
                System.out.print("\r" + lastLine + String.join("", Collections.nCopies(Math.max(last.length() - lastLine.length(), 0), " ")));
                last = lastLine;
            }
        }
    }

    private File getLogFile() throws IOException {
        if (!logFolder.exists())
            if (!logFolder.mkdirs())
                throw new RuntimeException("Failed to create log folder");
        if (logFile == null) {
            int i = 0;
            while ((logFile = new File(logFolder.getAbsoluteFile() + "/" + formattedDateTime + (i > 0 ? i : "") + ".log")).exists()) {
                i++;
            }
            if (!logFile.createNewFile())
                throw new RuntimeException("Failed to create log file");
        }
        return logFile;
    }

    private void flush() {
        System.out.flush();
    }

    public enum LogMode {
        LINE,
        NORMAL,
        FORMAT
    }
}
