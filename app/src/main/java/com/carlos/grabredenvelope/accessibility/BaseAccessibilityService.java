package com.carlos.grabredenvelope.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

public abstract class BaseAccessibilityService extends AccessibilityService {
    protected static final int STATE_IDLE = 0;
    protected static final int STATE_WAIT_RED_ENVELOPE = 1;

    private int currentState = STATE_IDLE;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            handleNotification(event);
        } else if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            handleWindowStateChanged(event);
        }
    }

    protected void handleNotification(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        for (CharSequence text : texts) {
            if (text.toString().contains("[微信红包]")) {
                openRedEnvelope(event);
                currentState = STATE_WAIT_RED_ENVELOPE;
                break;
            }
        }
    }

    protected abstract void handleWindowStateChanged(AccessibilityEvent event);

    protected AccessibilityNodeInfo findNodeByText(String text) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return null;

        List<AccessibilityNodeInfo> nodes = rootNode.findAccessibilityNodeInfosByText(text);
        return nodes.isEmpty() ? null : nodes.get(0);
    }
}