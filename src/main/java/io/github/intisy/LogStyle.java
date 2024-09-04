package io.github.intisy;

@SuppressWarnings("unused")
enum LogStyle {
    RESET("\u001B[0m"),
    BOLD("\u001B[1m"),
    UNDERLINE("\u001B[4m");

    private final String code;

    LogStyle(String code) {
        this.code = code;
    }

    public String apply(String text) {
        return code + text + RESET.code;
    }
}
