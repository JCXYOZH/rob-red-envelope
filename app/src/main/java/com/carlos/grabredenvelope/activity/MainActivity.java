public class MainActivity extends BaseActivity {
    private static final String WECHAT_SERVICE_NAME = "com.carlos.grabredenvelope/.services.WechatService";
    private ActivityMainBinding binding;
    private final List<Fragment> fragments = Arrays.asList(
            new ControlFragment(), new GuideFragment(), new AboutFragment(),
            new CodeFragment(), new RewardFragment(), new RecordFragment(), new EmojiFragment()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewPager();
        checkPermissions();
    }

    private void initViewPager() {
        CBaseMyPagerAdapter adapter = new CBaseMyPagerAdapter(
                getSupportFragmentManager(),
                fragments,
                Arrays.asList("控制", "教程", "说明", "源码", "打赏", "微信", "表情")
        );
        binding.viewPager.setAdapter(adapter);
    }

    private void checkPermissions() {
        requestPermission(100, new PermissionListener() {
                    @Override
                    public void permissionSuccess() {}

                    @Override
                    public void permissionFail() {}
                }, Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}