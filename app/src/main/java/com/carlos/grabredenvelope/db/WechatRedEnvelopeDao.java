package com.carlos.grabredenvelope.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

public class WechatRedEnvelopeDao extends AbstractDao<WechatRedEnvelope, Long> {

    public static final String TABLENAME = "WECHAT_RED_ENVELOPE";

    /**
     * Properties of entity WechatRedEnvelope.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Time = new Property(1, long.class, "time", false, "TIME");
        public final static Property Count = new Property(2, String.class, "count", false, "COUNT");
    }


    public WechatRedEnvelopeDao(DaoConfig config) {
        super(config);
    }
    
    public WechatRedEnvelopeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WECHAT_RED_ENVELOPE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TIME\" INTEGER NOT NULL ," + // 1: time
                "\"COUNT\" TEXT);"); // 2: count
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WECHAT_RED_ENVELOPE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WechatRedEnvelope entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getTime());
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(3, count);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WechatRedEnvelope entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getTime());
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(3, count);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public WechatRedEnvelope readEntity(Cursor cursor, int offset) {
        WechatRedEnvelope entity = new WechatRedEnvelope( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // time
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // count
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WechatRedEnvelope entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTime(cursor.getLong(offset + 1));
        entity.setCount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(WechatRedEnvelope entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(WechatRedEnvelope entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WechatRedEnvelope entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
