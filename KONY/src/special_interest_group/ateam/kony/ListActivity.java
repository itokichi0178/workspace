package special_interest_group.ateam.kony;

import java.util.ArrayList;
import java.util.List;

import special_interest_group.ateam.kony.db.AlarmSettingsDao;
import special_interest_group.ateam.kony.db.AlarmSettingsEntity;
import special_interest_group.ateam.kony.db.AlarmSettingsHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * アラーム一覧
 * */
public class ListActivity extends Activity
{
    static final String TAG = "SampleSettings";

    ListView itemListView;
    Button btnAdd;

    /**
     * DBアクセスクラス
     * */
    static AlarmSettingsDao dao;
    static AlarmListAdapter listAdapter;
    static List<AlarmSettingsEntity> alarmSettingsEntityList = new ArrayList<AlarmSettingsEntity>();

    @Override
    /**
     * 画面作成時
     * */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 初期設定
        btnAdd = (Button) findViewById(R.id.btnAdd);
        itemListView = (ListView)findViewById(R.id.AlarmList);

        // +ボタン押下時
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 詳細画面へ遷移（新規登録）
                Intent intent = new Intent();
                intent.setClassName(
                        "special_interest_group.ateam.kony",
                        "special_interest_group.ateam.kony.DetailActivity");
                startActivity(intent);
            }
        });

        // SQLiteの準備
        AlarmSettingsHelper helper = new AlarmSettingsHelper(this, null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        dao = new AlarmSettingsDao(db);

        listAdapter = new AlarmListAdapter();
        itemListView.setAdapter(listAdapter);

        loadList();
    }

    // リスト読み込み
    protected void loadList()
    {
        // リストをクリア
        alarmSettingsEntityList.clear();

        // データ全件を取得
        alarmSettingsEntityList = dao.findAll();
        for (AlarmSettingsEntity entity : alarmSettingsEntityList)
        {
            Log.v(TAG, entity.toString());
        }

        // リストビューのアダプターにデータリストの変更を通知
        listAdapter.notifyDataSetChanged();
    }

    @Override
    /**
     * メニューボタン押下時
     * */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        // テスト
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public class AlarmListAdapter extends BaseAdapter
    {
        @Override
        public int getCount()
        {
            return alarmSettingsEntityList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return alarmSettingsEntityList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        /**
         * ListViewの指定行の行イメージを取得
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            TextView tvTime;
            Switch swtOnOff;
            Button btnDetail;
            View v = convertView;
            if (v == null)
            {
                // 定義したListView の行レイアウト(listrow1.xml) を取得する
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.alarm_row, null);
            }
            // データリストから指定位置の AlarmSettingsEntity データを取得
            AlarmSettingsEntity entity = (AlarmSettingsEntity) getItem(position);
            if (entity != null)
            {
                // 取得した AlarmSettingsEntityデータを行レイアウト定義を使って行イメージを作成
                tvTime = (TextView) v.findViewById(R.id.tvTimes);
                tvTime.setText(entity.getHour() + ":" + entity.getMinute());
                swtOnOff = (Switch) v.findViewById(R.id.swtOnOff);
                if(entity.getEnable() > 0)
                {
                    swtOnOff.setChecked(true);
                }
                else
                {
                    swtOnOff.setChecked(false);
                }
                btnDetail = (Button)v.findViewById(R.id.btnDetail);
                btnDetail.setText(">");
            }
            return v;
        }
    }
}
