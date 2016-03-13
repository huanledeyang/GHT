package com.example.AIDL;

import android.os.RemoteException;

public class MyAIDLBinder extends AIDLSample.Stub {

	@Override
	public String update(String s) throws RemoteException {
		return s + " , message had been checked !";
	}
}