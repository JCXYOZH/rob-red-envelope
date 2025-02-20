// 网络请求模块
public class ControlFetcher {
    private static final String API_URL = "https://github.com/JCXYOZH/rob-red-envelope/control.json";

    public void fetchControlData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                String jsonData = NetUtils.get(API_URL);
                JSONObject jsonObj = new JSONObject(jsonData);

                String currentVersion = AppUtils.getVersionName();
                String stopTime = jsonObj.getString(currentVersion);

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                editor.putString("stopTime", stopTime);
                editor.apply();

            } catch (JSONException e) {
                Log.e("ControlFetcher", "JSON解析错误", e);
            }
        });
    }
}