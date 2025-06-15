package io.github.intisy.simple.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Finn Birich
 */
@SuppressWarnings("unused")
public class SimpleLogger {
    private int logLevel;
    private int percent = 0;
    private boolean enablePercent;
    private boolean enableLogToFile;
    private boolean enableDuplicateLog;
    private String last = "";
    private String prefix = "";
    private String suffix = "";
    static String formattedDateTime;
    private File logFile;
    private File logFolder;
    private boolean enableShortLog;
    private LogMode logMode;
    private PrintStream outputSteam;
    final List<String> logs = new ArrayList<>();

    public void setPrefix(String prefix) {
        this.prefix = prefix != null ? prefix : "";
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix != null ? suffix : "";
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
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

    public void setOutputSteam(PrintStream outputSteam) {
        this.outputSteam = outputSteam;
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

    public PrintStream getOutputSteam() {
        return outputSteam == null ? LoggerSettings.outputSteam : outputSteam;
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

    public void info(Object log, Object... args) {
        note(log, args);
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

    public void warn(Object log, Object... args) {
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
        if (log instanceof Exception)
            error(null, (Exception) log, line, args);
        else
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
        String logStr = prefix + log + suffix;

        if (enablePercent)
            logStr = "(" + percent + "%) " + logStr;

        if (!enableDuplicateLog && last.equals(logStr))
            return;
        else
            last = logStr;

        if (enableLogToFile && logFolder != null) {
            try {
                Files.write(getLogFile().toPath(), logStr.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!enableShortLog) {
            if (logMode == LogMode.LINE) {
                getOutputSteam().println(logStr);
            } else if (logMode == LogMode.NORMAL) {
                getOutputSteam().print(logStr);
            } else {
                getOutputSteam().format(logStr, args);
            }
        } else {
            if (isMajor) {
                getOutputSteam().println(logStr);
            } else {
                logs.add(logStr);
            }
            if (!logs.isEmpty()) {
                String lastLine = logs.get(logs.size() - 1);
                getOutputSteam().print("\r" + lastLine + String.join("", Collections.nCopies(Math.max(last.length() - lastLine.length(), 0), " ")));
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
        getOutputSteam().flush();
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public SimpleLogger clone() {
        SimpleLogger cloned = new SimpleLogger();

        cloned.logLevel = this.logLevel;
        cloned.percent = this.percent;
        cloned.enablePercent = this.enablePercent;
        cloned.enableLogToFile = this.enableLogToFile;
        cloned.enableDuplicateLog = this.enableDuplicateLog;
        cloned.last = this.last;
        cloned.setPrefix(this.prefix);
        cloned.setSuffix(this.suffix);

        cloned.setLogFolder(this.logFolder);

        cloned.enableShortLog = this.enableShortLog;
        cloned.setLogMode(this.logMode);
        cloned.setOutputSteam(this.outputSteam);

        cloned.logs.addAll(this.logs);

        return cloned;
    }

    public enum LogMode {
        LINE,
        NORMAL,
        FORMAT
    }
}