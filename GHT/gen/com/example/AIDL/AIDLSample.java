/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\Android\\GitHub\\GHT\\GHT\\src\\com\\example\\AIDL\\AIDLSample.aidl
 */
package com.example.AIDL;
public interface AIDLSample extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.AIDL.AIDLSample
{
private static final java.lang.String DESCRIPTOR = "com.example.AIDL.AIDLSample";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.AIDL.AIDLSample interface,
 * generating a proxy if needed.
 */
public static com.example.AIDL.AIDLSample asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.AIDL.AIDLSample))) {
return ((com.example.AIDL.AIDLSample)iin);
}
return new com.example.AIDL.AIDLSample.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_update:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.update(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.AIDL.AIDLSample
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String update(java.lang.String s) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(s);
mRemote.transact(Stub.TRANSACTION_update, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_update = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String update(java.lang.String s) throws android.os.RemoteException;
}
