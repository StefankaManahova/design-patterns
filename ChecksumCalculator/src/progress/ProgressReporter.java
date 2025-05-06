package progress;

import fileTree.SimpleFile;

public class ProgressReporter implements Observer
{
    String currentFile;
    long processedBytes;
    long totalBytes;
    boolean first;

    @Override
    public void update(int eventType, Object message)
    {
        if (eventType == 1)
        {
            this.totalBytes = (long)message;
            this.processedBytes = 0;
        }
        else if (eventType == 2)
        {
            this.currentFile = ((SimpleFile)message).getPath();
            this.processedBytes += ((SimpleFile)message).getSize();
            refreshDisplay();
        }
    }

    public void refreshDisplay()
    {
        if (first)
        {
            System.out.print("\n");
            first = false;
        }
        int percentage = (int)(100.0 * processedBytes / totalBytes);
        System.out.printf("\rProgress: %d%%   Processing file " + currentFile + "...", percentage);
    }
}
