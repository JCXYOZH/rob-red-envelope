public class UpgradeHelper extends DaoMaster.OpenHelper {
    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         Class<? extends AbstractDao<?, ?>>... daoClasses) {
        super(context, name, factory);
        this.daoClasses = daoClasses;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, daoClasses);
    }
}