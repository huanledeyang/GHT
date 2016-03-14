package com.example.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.ght.R;

public class MessengerMainActivity extends Activity {

	private TextView secondView;
	private TextView startBtn;
	private Messenger serviceMessenger;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        secondView = (TextView) findViewById(R.id.second);
        startBtn = (TextView) findViewById(R.id.start);
        
        startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("IBinder", "clicked !!!!");
				Bundle bd = new Bundle();
	    		bd.putString("message", "hello, remote service !");
				Message message = Message.obtain(null, 0, bd);
				message.replyTo = mMessenger;
				try {
					serviceMessenger.send(message);
				} catch (RemoteException e) {
					e.printStackTrace();
				}			
			}
		});
        
        
        //remote service
        Intent intent = new Intent();
        intent.setAction("com.example.messenger.REMOTE_SERVICE");
        intent.setPackage(getPackageName());
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
       
    }
    
     class mHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		secondView.setText(((Bundle)msg.obj).getString("message"));
    	}
    }
    private final Messenger mMessenger = new Messenger(new mHandler());
    
    private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			serviceMessenger = new Messenger(service);
		}
	};
	
	@Override
	protected void onDestroy() {
		unbindService(mServiceConnection);
	};
}
