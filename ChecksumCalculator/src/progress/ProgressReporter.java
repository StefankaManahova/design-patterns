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
        // Save cursor, move to top line, write bar, then restore cursor
        /*System.out.print("\0337"); // Save cursor position
        System.out.print("\033[s"); // Another save for safety
        System.out.print("\033[2d"); // Move up (top of console)
        System.out.print("\033[K"); // Move up (top of console)
        System.out.print("\033[1d"); // Move up (top of console)
        System.out.printf("\r\033[K Progress: %d%%   Processing file " + currentFile + "...", percentage);
        System.out.print("\033[u"); // Restore cursor
        System.out.flush();*/
        System.out.printf("\rProgress: %d%%   Processing file " + currentFile + "...", percentage);
    }
}
