package com.example.aidl;

import com.example.aidl.AIDLSample.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RemoteAidlService extends Service {

	private Stub st;
	
	@Override
	public IBinder onBind(Intent intent) {
		return st;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		st = new MyAIDLBinder();
	}
}
