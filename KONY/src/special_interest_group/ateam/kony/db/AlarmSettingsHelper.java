package special_interest_group.ateam.kony.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * データベースの作成とスキーマのバージョン管理を行うクラス
 *
 */
public class AlarmSettingsHelper extends SQLiteOpenHelper {

	/**
	 * データベース名
	 */
	private static final String DB_NAME = "KonyDB";

	/**
	 * AlarmSettingsテーブル作成用SQL
	 */
	private static final String CREATE_TABLE_SQL =
		  "CREATE TABLE AlarmSettings ("
		+ " rowId      INTEGER PRIMARY KEY AUTOINCREMENT"
		+ ",enable     INTEGER NOT NULL"
		+ ",hour       INTEGER NOT NULL"
		+ ",minute     INTEGER NOT NULL"
		+ ",repeat     INTEGER NOT NULL"
		+ ",daysOfWeek TEXT    NOT NULL"
		+ ",sound      TEXT    NOT NULL"
		+ ",snooze     INTEGER NOT NULL"
		+ ")"
		;

	/**
	 * AlarmSettingsテーブル削除用SQL
	 */
	private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS AlarmSettings";

	/**
	 * コンストラクタ（必須）
	 * @param context
	 * @param factory
	 * @param version
	 */
	public AlarmSettingsHelper(
		  Context context
		, CursorFactory factory
		, int version) {
		super(context, DB_NAME, factory, version);
	}

	/**
	 * テーブルの生成（必須）
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
	}


	/**
	 * テーブルの再作成（必須）
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_SQL);
		db.execSQL(CREATE_TABLE_SQL);
	}

}
