package com.app.jpushtest.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.app.jpushtest.R;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/2/24.
 */
public class PushReceiver extends BroadcastReceiver {
    private static final String TAG="PushReceiver";
    private NotificationManager mNotificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("","极光action:"+action);
        if (action.equals(JPushInterface.ACTION_REGISTRATION_ID)) {//注册到极光
//            String jpushUid = intent.getExtras().getString(JPushInterface.EXTRA_REGISTRATION_ID);
        }else if (action.equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){              //自定义消息
            if(intent.getExtras()!=null){
                String data = intent.getExtras().getString(JPushInterface.EXTRA_MESSAGE);
                String type = intent.getExtras().getString(JPushInterface.EXTRA_EXTRA); //'FOLLOW', 'LIKE', 'COMMENT',
                Log.e(TAG, "极光消息内容:" + data + "type:" + type);
                try {
                    if(!TextUtils.isEmpty(type)){
                        JSONObject jsonObject=new JSONObject(type);
                        if(jsonObject.has("type")){
                            String typeValue=jsonObject.getString("type");
                            if("FOLLOW".equals(typeValue)){
                            }
                            if("LIKE".equals(typeValue)){
                            }
                            if("COMMENT".equals(typeValue)){
                            }
                            Log.e(TAG,"收到push通知");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else if(action.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){          //通知
            if(intent.getExtras()!=null){
                int notifactionId = intent.getExtras().getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                String data = intent.getExtras().getString(JPushInterface.EXTRA_NOTI_TYPE);
                Log.e(TAG,"通知id:"+notifactionId+"data:"+data);
            }
        }else if(action.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){            //打开通知
            Log.e(TAG,"打开通知");
        }else if(action.equals(JPushInterface.ACTION_RICHPUSH_CALLBACK)){            //
            Log.e(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: ");
        }else if(action.equals(JPushInterface.ACTION_CONNECTION_CHANGE)){            //
//            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        }
    }
    private void sendNotification(Context context,String content){
        if (mNotificationManager == null){
            mNotificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Intent intent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(content))
                        .setContentText(content);
        mBuilder.setContentIntent(contentIntent);
        Notification notification = mBuilder.build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(1, notification);
    }
}
