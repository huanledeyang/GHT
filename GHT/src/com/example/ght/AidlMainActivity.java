package com.example.ght;

import com.example.AIDL.AIDLSample;

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

public class AidlMainActivity extends Activity {

	private TextView secondView;
	private TextView startBtn;
	
	private  AIDLSample as = null;
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
				if(as !=  null) {
	        		try {
						secondView.setText(as.update("hello remote service "));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
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
			as = AIDLSample.Stub.asInterface(service);
		}
	};
	
	@Override
	protected void onDestroy() {
		unbindService(mServiceConnection);
	};
}
