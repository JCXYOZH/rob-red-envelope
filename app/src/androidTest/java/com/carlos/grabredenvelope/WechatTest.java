@RunWith(AndroidJUnit4.class)
public class WechatTest {
    private UiDevice mDevice;
    private static final String PACKAGE_NAME = "com.tencent.mm";
    private static final String GROUP_NAME = "JCXYOZH";
    private static final long LAUNCH_TIMEOUT = 10000;
    private static final long ELEMENT_TIMEOUT = 7000;

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
    public void testSendRedEnvelope() {
        enterChatGroup();
        openRedEnvelopeMenu();
        fillRedEnvelopeDetails();
        inputPaymentPassword();
    }

    private void enterChatGroup() {
        List<UiObject2> chatLists = mDevice.findObjects(By.res("com.tencent.mm:id/bah"));
        for (UiObject2 list : chatLists) {
            UiObject2 target = list.wait(Until.findObject(By.text(GROUP_NAME)), ELEMENT_TIMEOUT);
            if (target != null) {
                target.click();
                break;
            }
        }
    }

    private void fillRedEnvelopeDetails() {
        List<UiObject2> editTexts = mDevice.findObjects(By.clazz("android.widget.EditText"));
        editTexts.get(0).setText("0.03");  // 金额
        editTexts.get(1).setText("3");     // 数量
        mDevice.findObject(By.res("com.tencent.mm:id/ddo")).click();
    }
}