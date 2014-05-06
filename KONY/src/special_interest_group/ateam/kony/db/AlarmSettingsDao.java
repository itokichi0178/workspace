package special_interest_group.ateam.kony.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * データベースへのアクセスをカプセル化しビジネスロジックから切り離すクラス
 *
 */
public class AlarmSettingsDao {

	// テーブルの定数
	private static final String TABLE_NAME = "AlarmSettings";

	private static final String COLUMN_ROWID      = "rowid";
	private static final String COLUMN_ENABLE     = "enable";
	private static final String COLUMN_HOUR       = "hour";
	private static final String COLUMN_MINUTE     = "minute";
	private static final String COLUMN_REPEAT     = "repeat";
	private static final String COLUMN_DAYSOFWEEK = "daysOfWeek";
	private static final String COLUMN_SOUND      = "sound";
	private static final String COLUMN_SNOOZE     = "snooze";

	private static final String[] COLUMNS = {
		  COLUMN_ROWID
		, COLUMN_ENABLE
		, COLUMN_HOUR
		, COLUMN_MINUTE
		, COLUMN_REPEAT
		, COLUMN_DAYSOFWEEK
		, COLUMN_SOUND
		, COLUMN_SNOOZE
		};

	/**
     * AlarmSettingsテーブル確認用SQL
     * */
    
    private static final String COUNT_TABLE_SQL = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='AlarmSettings';"; 

	/**
	 *  SQLiteDatabase
	 */
	private SQLiteDatabase db;

	/**
	 * コンストラクタ
	 * @param db
	 */
	public AlarmSettingsDao(SQLiteDatabase db) {
		this.db = db;
	}

	/**
	 * 全データの取得
	 * @return
	 */
	public List<AlarmSettingsEntity> findAll() {
		List<AlarmSettingsEntity> entityList = new  ArrayList<AlarmSettingsEntity>();
		Cursor cursor = db.query(
		                  TABLE_NAME   // 1 テーブル名
		                , COLUMNS      // 2 カラムの配列
		                , null         // 3 WHERE句 例)"columnNameXX like ?"
		                , null         // 4 3の'?'部分に該当するパラメータ値を配列で設定
		                , null         // 5 group by句
		                , null         // 6 Having句
		                , COLUMN_ROWID // 7 order by句
		                , null);       // 8 limit句

		while (cursor.moveToNext()) {
			AlarmSettingsEntity entity = new AlarmSettingsEntity();

			int idx = 0;
			entity.setRowId     (cursor.getInt   (idx++));
			entity.setEnable    (cursor.getInt   (idx++));
			entity.setHour      (cursor.getInt   (idx++));
			entity.setMinute    (cursor.getInt   (idx++));
			entity.setRepeat    (cursor.getInt   (idx++));
			entity.setDaysOfWeek(cursor.getString(idx++));
			entity.setSound     (cursor.getString(idx++));
			entity.setSnooze    (cursor.getInt   (idx++));

			entityList.add(entity);
		}

		return entityList;
	}

	/**
	 * 特定IDのデータを取得
	 * @param rowId
	 * @return
	 */
	public AlarmSettingsEntity findById(int rowId) {
		String selection = COLUMN_ROWID + "=" + rowId;
		Cursor cursor = db.query(
		                  TABLE_NAME // 1 テーブル名
		                , COLUMNS    // 2 カラムの配列
		                , selection  // 3 WHERE句
		                , null       // 4 3の'?'部分に該当するパラメータ値を配列で設定
		                , null       // 5 group by句
		                , null       // 6 Having句
		                , null);     // 7 order by句

		cursor.moveToNext();
		AlarmSettingsEntity entity = new AlarmSettingsEntity();

		int idx = 0;
		entity.setRowId     (cursor.getInt   (idx++));
		entity.setEnable    (cursor.getInt   (idx++));
		entity.setHour      (cursor.getInt   (idx++));
		entity.setMinute    (cursor.getInt   (idx++));
		entity.setRepeat    (cursor.getInt   (idx++));
		entity.setDaysOfWeek(cursor.getString(idx++));
		entity.setSound     (cursor.getString(idx++));
		entity.setSnooze    (cursor.getInt   (idx++));

		return entity;
	}

	/**
	 * データの登録
	 * @return
	 */
	public long insert(
		  int    enable
		, int    hour
		, int    minute
		, int    repeat
		, String daysOfWeek
		, String sound
		, int    snooze
		) {
		ContentValues values = new ContentValues();

		if (daysOfWeek == null) {daysOfWeek = "";}
		if (sound      == null) {sound      = "";}

		values.put(COLUMN_ENABLE    , enable    );
		values.put(COLUMN_HOUR      , hour      );
		values.put(COLUMN_MINUTE    , minute    );
		values.put(COLUMN_REPEAT    , repeat    );
		values.put(COLUMN_DAYSOFWEEK, daysOfWeek);
		values.put(COLUMN_SOUND     , sound     );
		values.put(COLUMN_SNOOZE    , snooze    );

		return db.insert(TABLE_NAME, null, values);
	}

	/**
	 * データの更新
	 * @param rowid
	 * @param date
	 * @return
	 */
	public int update(AlarmSettingsEntity entity) {
		ContentValues values = new ContentValues();

		if (entity.getDaysOfWeek() == null) {entity.setDaysOfWeek("");}
		if (entity.getSound()      == null) {entity.setSound     ("");}

		values.put(COLUMN_ENABLE    , entity.getEnable    ());
		values.put(COLUMN_HOUR      , entity.getHour      ());
		values.put(COLUMN_MINUTE    , entity.getMinute    ());
		values.put(COLUMN_REPEAT    , entity.getRepeat    ());
		values.put(COLUMN_DAYSOFWEEK, entity.getDaysOfWeek());
		values.put(COLUMN_SOUND     , entity.getSound     ());
		values.put(COLUMN_SNOOZE    , entity.getSnooze    ());

		String whereClause = COLUMN_ROWID + "=" + entity.getRowId();
		return db.update(TABLE_NAME, values, whereClause, null);
	}

	/**
	 * データの削除
	 * @param rowId
	 * @return
	 */
	public int delete(int rowId) {
		String whereClause = COLUMN_ROWID + "=" + rowId;
		return db.delete(TABLE_NAME, whereClause, null);
	}

	
	/**
	 * テーブルの存在有無確認
	 * @return テーブルの行数
	 * */
	public int tableCount()
	{
	    Cursor c = db.rawQuery(COUNT_TABLE_SQL, null); 
	    c.moveToFirst();
	    
	    return c.getColumnCount();
	}
}
