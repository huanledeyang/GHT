package com.example.ght;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView secondView;
	private TextView startBtn;
	
	private IBinder ib = null;
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
				if(ib !=  null) {
	        		Parcel data = Parcel.obtain();
	    	        Parcel reply = Parcel.obtain();
	    	        data.writeString("hello message");
	    	        secondView.setText(data.readString());
	        		try {
						ib.transact(0, data, reply, 0);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
	        		secondView.setText(reply.readString());
	        	}else {
	        		Log.e("IBinder", "ib is null !!!!");
	        	}				
			}
		});
        
        //本地service
       /*MyService.setListener(new Listener());
       startService(new Intent(this, MyService.class));*/
        
        //remote service
        Intent intent = new Intent();
        intent.setAction("com.example.ght.REMOTE_SERVICE");
        intent.setPackage(getPackageName());
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        
        try {
        	if(ib !=  null) {
        		Parcel data = Parcel.obtain();
    	        Parcel reply = Parcel.obtain();
    	        data.writeString("hello message");
    	        secondView.setText(data.readString());
        		ib.transact(0, data, reply, 0);
        		secondView.setText(reply.readString());
        	}else {
        		Log.i("IBinder", "ib is null !!!!");
        	}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public class Listener implements IListener {

		@Override
		public void update(String s) {
			secondView.setText(s);
		}
    	
    }
    
    private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			ib = service;
		}
	};
	
	@Override
	protected void onDestroy() {
		unbindService(mServiceConnection);
	};
}
