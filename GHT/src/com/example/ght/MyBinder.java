package com.example.ght;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

public class MyBinder extends Binder {
	@Override
	protected boolean onTransact(int code, Parcel data, Parcel reply, int flags)
			throws RemoteException {
		reply.writeString(data.readString() + ",  have readed the message !");
		return true;
	}
}
