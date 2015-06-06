package sebi.useboundservice;

import android.os.Binder;
import android.os.RemoteException;

/**
 * Created by Sebi on 06.06.15.
 */

public class CounterImpl extends Counter.Stub{
    private int counter;
    public synchronized int increment() throws RemoteException
    {
        counter++;
        return counter;
    }
    public synchronized int reset() throws RemoteException
    {
        counter = 0;
        return counter;
    }
}
