package com.example.ght;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class MyService extends Service {

	private static IListener listener;
	private static int sec;
	private Timer timer = new Timer();
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		timer.schedule(tt, 1000, 1000);
	}
	
	public static void setListener(IListener l) {
		listener = l;
	}
	
	private TimerTask tt = new TimerTask() {
		
		@Override
		public void run() {
			sec++;
			mHandler.sendEmptyMessage(0);
		}
	};
	
	private static Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if(listener != null) {
				listener.update(String.valueOf(sec));
			}
		}
	};
}
