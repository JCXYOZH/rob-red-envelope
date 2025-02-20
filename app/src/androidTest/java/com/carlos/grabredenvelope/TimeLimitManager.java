public class TimeLimitManager {
    private boolean isStop = false;
    private String message;

    public TimeLimitManager() {
        getStopTime();
        setLimitTime();
    }

    private void setLimitTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        LogUtils.i("是否停止使用\n现在是" + year + "年" + month + "月" + day + "日"

                + hour + "时" + minute + "分");

        message = "本软件设定使用时限已到时间，谢谢使用，请点击确定退出。如想继续用可联系JCXYOZH，谢谢！";

        // 时间限制逻辑
        String stoptime = "2030-12-31 00:00:00"; // 小于此时间可用
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date stopDate = dateFormat.parse(stoptime);
            String savedTime = RedEnvelopePreferences.getStopTime();

            if (savedTime != null && !savedTime.isEmpty()) {
                stopDate = dateFormat.parse(savedTime);
            }

            Date currentDate = new Date();
            if (currentDate.after(stopDate)) {
                showExpirationDialog();
                isStop = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showExpirationDialog() {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> System.exit(0))
                .setCancelable(false)
                .show();
    }
}