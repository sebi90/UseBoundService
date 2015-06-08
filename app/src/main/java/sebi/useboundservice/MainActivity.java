package sebi.useboundservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import sebi.boundservice.Counter;

public class MainActivity extends Activity {


    private static final String serviceName = "dma.lecture.localboundservice.LOCALBOUNDSERVICE";
    private Counter counter;
    private MyServiceConnection serConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setCounter(Counter counter) {

        this.counter = counter;
        TextView tv = (TextView) findViewById(R.id.textView1);
        if (counter != null) {
            tv.setText(R.string.msgBound);
        } else {
            tv.setText(R.string.msgUnbound);
        }
    }

    public void onBindService(View v) {

        Log.d(this.getClass().getName(), "onBindService");
        Intent newIntent = new Intent(serviceName);
        serConn = new MyServiceConnection(this);
        newIntent.setPackage("sebi.boundservice");
        bindService(newIntent, serConn, Context.BIND_AUTO_CREATE);
    }

    public void onUnbindService(View v)
    {
        if(serConn != null)
        {
            unbindService(serConn);
        }
        serConn = null;
        setCounter(null);
    }


    public void increment(View view) throws RemoteException
    {
        action(1); }
    public void reset(View view) throws RemoteException
    {
        action(2); }


    private void action(int function) throws RemoteException {
        TextView tv = (TextView) findViewById(R.id.textView2);
        if (counter != null) {
            int newValue = 0;
            switch (function) {

                case 1:
                    try {
                        newValue = counter.increment();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        newValue = counter.reset();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    return;
            }
            tv.setText("" + newValue);
        } else {
            tv.setText(R.string.msgNoOpPossible);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
