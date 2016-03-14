package com.example.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class RemoteMessengerService extends Service {

	private static Messenger clientMessenger;
	
	@Override
	public IBinder onBind(Intent intent) {
		return mMessenger.getBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
	}
	
	static class mHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		clientMessenger = msg.replyTo;

    		Bundle bd = new Bundle();
    		String msg1 = ((Bundle)msg.obj).getString("message");
    		bd.putString("message", msg1 + " \n hello client !");
    		Message message = Message.obtain(null, 0, bd);
    		try {
				clientMessenger.send(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }
	private final Messenger mMessenger = new Messenger(new mHandler());
}
