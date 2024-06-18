package com.github.WildePizza;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
    private boolean enableShortLog;
    final List<String> logs = new ArrayList<>();
    public SimpleLogger() {
        this.enablePercent = false;
        this.logLevel = 4;
        this.enableShortLog = false;
        this.startTime = System.currentTimeMillis();
        this.enableLogToFile = false;
        this.enableDuplicateLog = false;
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
        System.out.println(LogColor.RED.apply(String.valueOf(log)));
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
        System.out.println(LogColor.RED.apply(message == null ? "" : message) + log);
        System.exit(0);
    }
    public void debug(Object log) {
        if (logLevel >= 5)
            log(LogColor.WHITE.apply(String.valueOf(log)), false);
    }
    public void note(Object log) {
        if (logLevel >= 4)
            log(LogColor.GRAY.apply(String.valueOf(log)), false);
    }
    public void success(Object log) {
        if (logLevel >= 3)
            log(LogColor.GREEN.apply(String.valueOf(log)), false);
    }
    public void warning(Object log) {
        if (logLevel >= 2)
            log(LogColor.YELLOW.apply(String.valueOf(log)), false);
    }
    public void major(Object log) {
        if (logLevel >= 1)
            log(LogColor.BLUE_BACKGROUND.apply(String.valueOf(log)), true);
    }
    public void log(Object log, boolean m) {
        if (enablePercent)
            if (lastTime != 0)
                log = "(" + ((int) ((double) (System.currentTimeMillis()-startTime)/lastTime*100)) + "%) " + log.toString();
            else
                log = "(" + percent + "%) " + log.toString();
        if (enableDuplicateLog || !last.equals(String.valueOf(log))) {
            last = String.valueOf(log);
            if (enableLogToFile) {
                LocalDate today = LocalDate.now();
                String format = "yyyy-MM-dd-HH";
                String formattedDate = today.format(DateTimeFormatter.ofPattern(format));
                File logFile = new File(formattedDate + ".log");
                try {
                    if (!logFile.exists() && !logFile.createNewFile())
                        throw new RuntimeException("Failed to create log file");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try (FileWriter writer = new FileWriter(logFile, true)) {
                    // Wrap the FileWriter in a BufferedWriter for better performance
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    // Write the content to the file
                    bufferedWriter.write(String.valueOf(log));
                    // Flush the writer to ensure data is saved to the file
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
