// 版本更新工具
public class Update {
    private static final String UPDATE_URL = "https://github.com/JCXYOZH/rob-red-envelope/blob/master/update.xml";
    private final Context context;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public void checkUpdate() {
        new Thread(() -> {
            try {
                URL url = new URL(UPDATE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                if (conn.getResponseCode() == 200) {
                    parseUpdateInfo(conn.getInputStream());
                }
            } catch (IOException e) {
                mainHandler.post(() -> Toast.makeText(context, "更新检查失败", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // XML解析实现
    private void parseUpdateInfo(InputStream input) {
        ;
    }
}