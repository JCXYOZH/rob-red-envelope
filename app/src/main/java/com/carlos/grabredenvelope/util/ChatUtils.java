public class ChatUtils {
    private static final String WECHAT_PACKAGE = "com.tencent.mm";
    private static final long UI_TIMEOUT = 5000;

    public static void sendChatMessage(UiDevice device, String message) {
        // 查找所有输入框
        List<UiObject2> editFields = device.findObjects(By.clazz("android.widget.EditText"));
        if (!editFields.isEmpty()) {
            editFields.get(0).setText(message);
            SystemClock.sleep(300);

            // 查找发送按钮
            List<UiObject2> buttons = device.findObjects(By.clazz("android.widget.Button"));
            for (UiObject2 btn : buttons) {
                if ("发送".equals(btn.getText())) {
                    btn.click();
                    break;
                }
            }
        }
    }

    public static void openRedEnvelope(UiDevice device) {
        // 查找红包控件
        UiObject2 redEnvelope = device.wait(Until.findObject(
                By.res("com.tencent.mm:id/atb")), UI_TIMEOUT);
        if (redEnvelope != null) {
            redEnvelope.click();
            SystemClock.sleep(1000);

            // 拆红包操作
            UiObject2 openBtn = device.wait(Until.findObject(
                    By.res("com.tencent.mm:id/dan")), UI_TIMEOUT);
            if (openBtn != null) {
                openBtn.click();
            }
        }
    }
}