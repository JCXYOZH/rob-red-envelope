public class GreenDaoManager {
    private static volatile GreenDaoManager instance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private GreenDaoManager(Context context) {
        UpgradeHelper helper = new UpgradeHelper(context, "grabredenvelope",
                null, WechatRedEnvelopeDao.class, DingDingRedEnvelopeDao.class);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static GreenDaoManager getInstance(Context context) {
        if (instance == null) {
            synchronized (GreenDaoManager.class) {
                if (instance == null) {
                    instance = new GreenDaoManager(context);
                }
            }
        }
        return instance;
    }

    public DaoSession getSession() {
        return daoSession;
    }
}