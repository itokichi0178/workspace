package special_interest_group.ateam.kony;

import java.util.ArrayList;
import java.util.List;

import special_interest_group.ateam.kony.db.AlarmSettingsDao;
import special_interest_group.ateam.kony.db.AlarmSettingsEntity;
import special_interest_group.ateam.kony.db.AlarmSettingsHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SampleSettingsActivity extends Activity {

	static final String TAG = "SampleSettings";
	static final int MENUITEM_ID_DELETE = 1;

	ListView itemListView;
	EditText editTextSound;
	Button btnInsert;

	static AlarmSettingsDao dao;
	static MemoListAdapter listAdapter;
	static List<AlarmSettingsEntity> alarmSettingsEntityList = new ArrayList<AlarmSettingsEntity>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_settings);

		// ====
		// 初期設定
		// ====
		// 追加ボタン
		btnInsert = (Button) findViewById(R.id.btn_insert);
		// データ入力欄
		editTextSound = (EditText) findViewById(R.id.edittext_alarmsettings_sound);
		editTextSound.setOnKeyListener(new AddressBarOnKeyListener());
		// 表示領域
		itemListView = (ListView) findViewById(R.id.listview_alarmsettings);

		// ====
		// イベント
		// ====
		// 追加ボタンクリック時の動作
		btnInsert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// テキストフィールドの内容をSound列に設定し、INSERT
				dao.insert(
						   0
						 , 0
						 , 0
						 , 0
						 , ""
						 , editTextSound.getText().toString()
						 , 0);
				editTextSound.setText("");
				// 保存後のテーブルの内容をリストビューへロード
				loadMemo();
			}
		});

		// リストビューの項目を長押ししたときにポップアップメニューを作成
		itemListView
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
					@Override
					public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
						// 削除メニューを追加
						menu.add(0, MENUITEM_ID_DELETE, 0, "削除");
					}
				});


		// SQLiteの準備
		AlarmSettingsHelper helper = new AlarmSettingsHelper(this, null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		dao = new AlarmSettingsDao(db);

		listAdapter = new MemoListAdapter();
		itemListView.setAdapter(listAdapter);

		// データを ListView にロード
		loadMemo();
	}

	/*
	 * データを ListView にロード
	 */
	protected void loadMemo() {
		// データリストをクリア
		alarmSettingsEntityList.clear();

		// データ全件を取得
		alarmSettingsEntityList = dao.findAll();
		for (AlarmSettingsEntity entity : alarmSettingsEntityList) {
			Log.v(TAG, entity.toString());
		}

		// リストビューのアダプターにデータリストの変更を通知
		listAdapter.notifyDataSetChanged();
	}

	/**
	 * ポップアップメニューのメニュー項目がクリックされたときの処理
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case MENUITEM_ID_DELETE:
				// 削除メニューが選択された
				AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
						.getMenuInfo();

				AlarmSettingsEntity memo = alarmSettingsEntityList.get(menuInfo.position);
				final int memoId = memo.getRowId();

				// 確認ダイアログを表示
				new AlertDialog.Builder(this)
						// .setIcon(R.drawable.icon)
						.setTitle("このメモを削除してもよろしいですか?")
						.setPositiveButton("削除",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (dao.delete(memoId) > 0) {
											loadMemo();
										}
									}
								}).setNegativeButton("取消", null).show();

				return true;
		}
		return super.onContextItemSelected(item);
	}

	/**
	 * データリストと ListView をリンクするためのアダプタ
	 */
	private class MemoListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return alarmSettingsEntityList.size();
		}

		@Override
		public Object getItem(int position) {
			return alarmSettingsEntityList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		/**
		 * ListViewの指定行の行イメージを取得
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView memoTextView;
			View v = convertView;
			if (v == null) {
				// 定義したListView の行レイアウト(listrow1.xml) を取得する
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.activity_sample_settings_listrow, null);
			}
			// データリストから指定位置の AlarmSettingsEntity データを取得
			AlarmSettingsEntity entity = (AlarmSettingsEntity) getItem(position);
			if (entity != null) {
				// 取得した AlarmSettingsEntityデータを行レイアウト定義を使って行イメージを作成
				memoTextView = (TextView) v.findViewById(R.id.textview_alarmsettingsInfo);
				memoTextView.setText("rowId列=[" + entity.getRowId() + "] Sound列=[" + entity.getSound() + "]");
			}
			return v;
		}
	}

	/**
	 * ソフトキーボードの「確定」が押された時にソフトキーボードを消す
	 *
	 */
	private class AddressBarOnKeyListener implements OnKeyListener {

		public boolean onKey(View view, int keyCode, KeyEvent event) {

			//EnterKeyが押されたかを判定
			if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

				//ソフトキーボードを閉じる
				InputMethodManager inputMethodManager =
					(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

				return true;
			}

			return false;
		}
	}

}
