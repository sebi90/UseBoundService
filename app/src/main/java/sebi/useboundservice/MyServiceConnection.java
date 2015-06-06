package sebi.useboundservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Sebi on 06.06.15.
 */
public class MyServiceConnection implements ServiceConnection{
    private MainActivity activity;
    public MyServiceConnection(MainActivity activity)
    {
        this.activity = activity;
    }

    public void onServiceConnected(ComponentName name, IBinder binder) {
        // teil b):
        Log.d(this.getClass().getName(), "onServiceConnected");
        //CounterImpl c = (CounterImpl)binder;
        Counter c = Counter.Stub.asInterface(binder);
        activity.setCounter(c);
    }
    public void onServiceDisconnected(ComponentName name)
    {
        Log.d(this.getClass().getName(), "onServiceDisconnected");
        activity.setCounter(null);
    }

}
