package com.example.ght;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RemoteService extends Service{

	private IBinder mb;
	
	@Override
	public IBinder onBind(Intent intent) {
		return mb;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		mb = new MyBinder();
	}
}
