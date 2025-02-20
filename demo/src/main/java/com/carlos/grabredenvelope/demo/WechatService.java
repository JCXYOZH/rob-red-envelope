public class WechatService extends BaseAccessibilityService {
    private static final String TAG = "WechatService";
    private static final int MAX_RETRY = 3;
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        threadPool.execute(() -> processEvent(event));
    }

    private void processEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;

        try {
            if (isChatListPage(rootNode)) {
                handleChatList(rootNode);
            } else if (isLuckyMoneyDetailPage(rootNode)) {
                handleMoneyDetail(rootNode);
            }
        } finally {
            rootNode.recycle();
        }
    }

    private void handleChatList(AccessibilityNodeInfo rootNode) {
        List<AccessibilityNodeInfo> nodes = rootNode.findByViewId(
                WechatConstants.RED_ENVELOPE_RECT_TITLE_ID
        );
        for (AccessibilityNodeInfo node : nodes) {
            if (isValidRedEnvelope(node)) {
                performClickWithRetry(node, MAX_RETRY);
                break;
            }
        }
    }

    private boolean isValidRedEnvelope(AccessibilityNodeInfo node) {
        return node.getText() != null &&
                node.getText().toString().contains(WechatConstants.RED_ENVELOPE_TITLE);
    }
}
