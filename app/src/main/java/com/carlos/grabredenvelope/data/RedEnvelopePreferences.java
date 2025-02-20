public class RedEnvelopePreferences {
    private static final String PREFS_NAME = "RedEnvelopePrefs";
    private static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Kotlin扩展函数
    public static String getStopTime() {
        return prefs.getString("stopTime", "2030-12-31 00:00:00");
    }

    public static void setWechatControl(WechatControlVO vo) {
        Gson gson = new Gson();
        prefs.edit().putString("wechatControl", gson.toJson(vo)).apply();
    }
}