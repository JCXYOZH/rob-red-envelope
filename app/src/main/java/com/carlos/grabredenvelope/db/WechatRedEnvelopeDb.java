// 数据库操作转换
public class WechatRedEnvelopeDb {
    private static DaoSession daoSession;

    public static void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "redenvelope.db");
        SQLiteDatabase db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static List<WechatRedEnvelope> getAll() {
        return daoSession.getWechatRedEnvelopeDao().loadAll();
    }

    public static void insert(WechatRedEnvelope entity) {
        daoSession.getWechatRedEnvelopeDao().insert(entity);
    }
}