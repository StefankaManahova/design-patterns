package progress;

import fileTree.SimpleFile;

import java.util.ArrayList;
import java.util.List;

public class Observable
{
    protected List<Observer> subscribers;

    public Observable()
    {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Observer s)
    {
        subscribers.add(s);
    }

    public void removeSubscriber(Observer s)
    {
        subscribers.remove(s);
    }

    public void notify(int eventType, Object message)
    {
        for (Observer s : subscribers)
        {
            s.update(eventType, message);
        }
    }
}
