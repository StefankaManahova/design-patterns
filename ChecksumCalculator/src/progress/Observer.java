package progress;

import fileTree.SimpleFile;

public interface Observer
{
    public void update(int eventType, Object message);
}
