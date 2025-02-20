package com.carlos.grabredenvelope.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import com.carlos.cutils.thirdparty.database.MigrationHelper
import com.carlos.grabredenvelope.db.DaoMaster.OpenHelper
import org.greenrobot.greendao.AbstractDao

class UpgradeHelper(
    context: Context?,
    name: String?,
    factory: CursorFactory?,
    vararg daoClasses: Class<out AbstractDao<*, *>?>
//     daoClasses: Class<out AbstractDao<*, *>?>
) : OpenHelper(context, name, factory) {
    private val mClasses: Array<Class<out AbstractDao<*, *>?>>
    init {
        mClasses = daoClasses as Array<Class<out AbstractDao<*, *>?>>
    }

    override fun onUpgrade(
        sqLiteDatabase: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        MigrationHelper.migrate(
            sqLiteDatabase,
            *mClasses
        )
    }
}
