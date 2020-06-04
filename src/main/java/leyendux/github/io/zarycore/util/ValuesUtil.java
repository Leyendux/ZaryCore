package leyendux.github.io.zarycore.util;

public enum ValuesUtil {
    MAINTENANCE(true),
    MAINTENANCE_KICKMSG("§cServer hasn't been released yet, join our discord for updates!"),
    PREFIX("§3§lZaryNetwork §8» ");

    private boolean enabled;
    private String message;

    ValuesUtil(boolean b) {
        this.enabled = b;
    }

    ValuesUtil(String s) {
        message = s;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setValue(boolean value) {
        this.enabled = value;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
