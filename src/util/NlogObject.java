package util;

public interface NlogObject
{
    public abstract String toString();

    public abstract String toString(boolean flag);

    public abstract String getClientInfo();

    public abstract long getCreationTime();

    public abstract boolean isRecord();
}
