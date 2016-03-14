package com.example.aidl;

import android.os.RemoteException;
import android.util.Log;

public class MyAIDLBinder extends AIDLSample.Stub {

	@Override
	public String update(String s) throws RemoteException {
		Log.i("aidl", "receive the message : " + s);
		return s + " , message had been checked !";
	}
}