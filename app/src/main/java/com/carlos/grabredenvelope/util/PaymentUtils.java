public class PaymentUtils {
    private static final int[] KEY_CODES = {
            KeyEvent.KEYCODE_1,
            KeyEvent.KEYCODE_3,
            KeyEvent.KEYCODE_4,
            KeyEvent.KEYCODE_6,
            KeyEvent.KEYCODE_7,
            KeyEvent.KEYCODE_9
    };

    public static void inputPaymentPassword(UiDevice device) {
        for (int keyCode : KEY_CODES) {
            device.pressKeyCode(keyCode);
            SystemClock.sleep(300); // 模拟真实输入间隔
        }
    }
}