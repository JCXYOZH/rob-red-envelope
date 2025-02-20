public class TimeLimitManager {
    private boolean isStop = false;
    private String message;

    public TimeLimitManager() {
        getStopTime();
        setLimitTime(); // 严格保持初始化顺序
    }

    private void setLimitTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // 0-11取值
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // 完全复制Kotlin日志格式（含换行符）
        LogUtils.i("是否停止使用\n现在是" + year + "年" + (month + 1) + "月"

                + day + "日" + hour + "时" + minute + "分");

        message = "本软件设定使用时限已到时间，谢谢使用，请点击确定退出。如想继续用可联系小不点，谢谢！";
        String defaultStopTime = "2030-12-31 00:00:00.000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        try {
            // 处理默认时间（含毫秒截断）
            Date stopDate = dateFormat.parse(defaultStopTime.substring(0, 19));
            Log.i("停止使用时间", defaultStopTime);

            // 补全Kotlin未完成的代码段
            if (!TextUtils.isEmpty(RedEnvelopePreferences.stopTime)) {
                try {
                    String customTime = RedEnvelopePreferences.stopTime;
                    // 处理超长字符串（超过19字符自动截断）
                    if (customTime.length() > 19) {
                        customTime = customTime.substring(0, 19);
                    }
                    stopDate = dateFormat.parse(customTime);
                    LogUtils.i("更新停止时间为：" + customTime);
                } catch (ParseException e) {
                    LogUtils.e("用户时间解析失败", e);
                }
            }

            // 时间比较逻辑
            if (new Date().after(stopDate)) {
                showExpirationDialog();
                isStop = true;
            }
        } catch (ParseException e) {
            throw new RuntimeException("基础时间配置异常", e);
        }
    }

    private void showExpirationDialog() {
        // 弹窗实现（与Kotlin逻辑保持一致）
    }
}