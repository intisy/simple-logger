package io.github.intisy.simple.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SimpleLogger {
    private int logLevel;
    private int percent = 0;
    private boolean enablePercent;
    private boolean enableLogToFile;
    private boolean enableDuplicateLog;
    private long lastTime = 0;
    private final long startTime;
    String major;
    String last = "";
    static String formattedDateTime;
    private File logFile;
    private File logFolder;
    private boolean enableShortLog;
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
        this.startTime = System.currentTimeMillis();
        this.enableLogToFile = true;
        this.enableDuplicateLog = false;
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
    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public void addPercent(int percent) {
        this.percent += percent;
    }
    public int getPercent() {
        return this.percent;
    }
    public void error(Object log) {
        error(log, 4);
    }
    public void error(Object log, int line) {
        if (logLevel >= LogLevel.WARN) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[3];
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            log(LogColor.RED.apply(log + getStackTraceElement(line)));
        }
    }
    public void printStackTrace() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            error(element.toString());
        }
    }
    public void exception(Exception exception) {
        exception(exception, null);
    }
    public void exception(Exception exception, String message) {
        error(Utils.removeDuplicateLines(LoggerUtils.exceptionToString(exception, message)));
    }
    public void horrible(Object log) {
        horrible(log, null);
    }
    public void horrible(Object log, String message) {
        if (log instanceof Exception) {
            log = LoggerUtils.exceptionToString((Exception) log);
        }
        log(LogColor.RED.apply(message == null ? "" : message) + log);
        System.exit(0);
    }
    public void debug(Object log) {
        debug(log, 4);
    }
    public void debug(Object log, int line) {
        if (logLevel >= LogLevel.DEBUG) {
            log(LogColor.WHITE.apply(log + getStackTraceElement(line)));
        }
    }
    private String getStackTraceElement(int line) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[line];
        String fileName = element.getFileName();
        int lineNumber = element.getLineNumber();
        return " (" + fileName + ":" + lineNumber + ")";
    }
    public void note(Object log) {
        if (logLevel >= LogLevel.NOTE)
            log(LogColor.GRAY.apply(String.valueOf(log)));
    }
    public void success(Object log) {
        if (logLevel >= LogLevel.SUCCESS)
            log(LogColor.GREEN.apply(String.valueOf(log)));
    }
    public void warning(Object log) {
        warning(log, 4);
    }
    public void warning(Object log, int line) {
        if (logLevel >= LogLevel.WARN) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[3];
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            log(LogColor.YELLOW.apply(log + getStackTraceElement(line)));
        }
    }
    public void major(Object log) {
        if (logLevel >= LogLevel.MAJOR)
            log(LogColor.BLUE_BACKGROUND.apply(LogColor.GRAY.apply(String.valueOf(log))), true);
    }
    public void log(Object log) {
        log(log, false);
    }
    public void log(Object log, boolean m) {
        if (enablePercent)
            if (lastTime != 0)
                log = "(" + ((int) ((double) (System.currentTimeMillis()-startTime)/lastTime*100)) + "%) " + log.toString();
            else
                log = "(" + percent + "%) " + log.toString();
        if (enableDuplicateLog || !last.equals(String.valueOf(log))) {
            last = String.valueOf(log);
            if (enableLogToFile && logFolder != null) {
                try {
                    if (!logFolder.exists())
                        if (!logFolder.mkdirs())
                            throw new RuntimeException("Failed to create log folder");
                    if (logFile == null) {
                        logFile = new File(logFolder.getAbsoluteFile() + "/" + formattedDateTime + ".log");
                        if (logFile.exists()) {
                            int i = 1;
                            while (!(logFile = new File(logFolder.getAbsoluteFile() + "/" + formattedDateTime + i + ".log")).exists()) {
                                i++;
                            }
                        }
                        if (!logFile.createNewFile())
                            throw new RuntimeException("Failed to create log file");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try (FileWriter writer = new FileWriter(logFile, true)) {
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write(LogColor.removeAll((String) log) + "\n");
                    bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!enableShortLog)
                System.out.println(LogColor.WHITE.apply(String.valueOf(log)));
            else {
                if (m)
                    major = String.valueOf(log);
                else
                    logs.add(String.valueOf(log));
                List<String> finalLogs = logs;
                if (major != null)
                    if (finalLogs.size() > 12) {
                        if (finalLogs.size() > 13)
                            logs.remove(0);
                        finalLogs.set(finalLogs.size() - 13, major);
                    } else
                        finalLogs.set(0, major);
                for (String v : finalLogs)
                    System.out.println(v);
            }
        }
    }
}
