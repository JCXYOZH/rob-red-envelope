public class WechatHandler {
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("\\d+\\.\\d{2}");

    public static void handleEnvelopeOpening(AccessibilityNodeInfo rootNode) {
        AccessibilityNodeInfo openButton = findOpenButton(rootNode);
        if (openButton != null) {
            simulateHumanClick(openButton);
            scheduleCloseTask();
        }
    }

    private static void simulateHumanClick(AccessibilityNodeInfo node) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Rect rect = new Rect();
            node.getBoundsInScreen(rect);
            GestureDescription.Builder builder = new GestureDescription.Builder();
            Path path = new Path();
            path.moveTo(rect.centerX(), rect.centerY());
            builder.addStroke(new GestureDescription.StrokeDescription(
                    path, 0, 50));
            dispatchGesture(builder.build(), null, null);
        }, getRandomDelay());
    }

    private static int getRandomDelay() {
        return new Random().nextInt(300) + 200;
    }
}
