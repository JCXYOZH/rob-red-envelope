@RunWith(AndroidJUnit4.class)
public class AutoSendEmojiTest {
    private static final String TAG = "AutoSendEmojiTest";
    private UiDevice mDevice;
    private static final String PACKAGE_NAME = "com.tencent.mm";
    private static final int LAUNCH_TIMEOUT = 10000;

    @Before
    public void setUp() {
        LogUtils.d("测试开始时间" + new Date());
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testSendMultipleEmojis() {
        final int SEND_TIMES = 13;
        clickChatList("JCXYOZH");

        for (int i = 0; i < SEND_TIMES; i++) {
            LogUtils.d("第" + (i+1) + "次发送[烟花]");
            enterText("[烟花]");
            clickSendButton();
        }
    }

    private void clickChatList(String chatName) {
        List<UiObject2> lists = mDevice.findObjects(By.clazz("android.widget.ListView"));
        for (UiObject2 list : lists) {
            UiObject2 target = list.findObject(By.text(chatName));
            if (target != null) {
                target.click();
                break;
            }
        }
    }
}