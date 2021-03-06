package com.kenny.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * description
 * Created by hugs on 2015/7/24.
 * version
 */
public class MessengerService extends Service {

    private static final int MSG_SUM = 0x110;

    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgfromClient) {

            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
            switch (msgfromClient.what) {

                //msg 客户端传来的消息
                case MSG_SUM:

                    msgToClient.what = MSG_SUM;
                    try {
                        //模拟耗时操作,实际开发中建议使用HandlerThread配合使用
                        Thread.sleep(2000);
                        //将计算结果返回给客户端
                        msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                        msgfromClient.replyTo.send(msgToClient);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            super.handleMessage(msgfromClient);
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
