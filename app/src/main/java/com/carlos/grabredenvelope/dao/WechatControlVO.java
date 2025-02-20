@Data  // Lombok注解
@AllArgsConstructor
@NoArgsConstructor
public class WechatControlVO {
    private boolean monitorNotification;
    private boolean monitorChat;
    private boolean ifGrabSelf;
    private int delayOpenTime;
    private int delayCloseTime;
    private boolean customClick;
    private int pointX;
    private int pointY;
}