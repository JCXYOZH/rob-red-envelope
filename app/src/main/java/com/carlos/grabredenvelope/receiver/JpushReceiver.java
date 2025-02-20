// 极光推送接收器
public class JpushReceiver extends JPushMessageReceiver {
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        try {
            JSONObject json = new JSONObject(message.notificationExtras);
            String alert = json.optString("alert", "新版本通知");

            Intent intent = new Intent(context, MessageActivity.class)
                    .putExtra("data", alert)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } catch (JSONException e) {
            LogUtils.e("解析推送消息异常", e);
        }
    }
}