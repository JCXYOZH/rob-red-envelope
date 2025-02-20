public class WechatService extends BaseAccessibilityService {
    private static final String TAG = "WechatService";
    private static final int MAX_RETRY = 3;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            handleWindowChange(event);
        }
    }

    private void handleWindowChange(AccessibilityEvent event) {
        String className = event.getClassName().toString();
        switch (className) {
            case WechatConstants.WECHAT_LUCKYMONEY_ACTIVITY:
                openRedEnvelope(event);
                break;
            case WechatConstants.WECHAT_LUCKYMONEYDETAILUI_ACTIVITY:
                closeRedEnvelope(event);
                break;
        }
    }

    private void openRedEnvelope(AccessibilityEvent event) {
        WechatControlVO control = RedEnvelopePreferences.getWechatControl();
        handler.postDelayed(() -> {
            AccessibilityNodeInfo root = getRootInActiveWindow();
            performClick(root, WechatConstants.RED_ENVELOPE_OPEN_ID);
        }, control.getDelayOpenTime() * 1000L);
    }
}
