package special_interest_group.ateam.kony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import special_interest_group.ateam.kony.db.AlarmSettingsDao;
import special_interest_group.ateam.kony.db.AlarmSettingsEntity;
import special_interest_group.ateam.kony.db.AlarmSettingsHelper;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

/**
 * アラーム一覧
 * */
public class ListActivity extends Activity
{
    private ArrayList<HashMap<String, String>> data;
    private HashMap<String, String> map;

    /**
     * DBアクセスクラス
     * */
    private AlarmSettingsDao alarmDao;
    
    /**
     * アラームデータ格納
     * */
    private List<AlarmSettingsEntity> listAlarm;
    
    /**
     * アラームリスト
     * */
    private ArrayList<HashMap<Integer, AlarmSettingsDao>> alarmList;
    
    @Override
    /**
     * 画面作成時
     * */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        // SQLiteの準備
        AlarmSettingsHelper helper = new AlarmSettingsHelper(this, null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
//        dao = new AlarmSettingsDao(db);
//
//        listAdapter = new MemoListAdapter();
//        itemListView.setAdapter(listAdapter);
//
//        
//        alarmDao = new AlarmSettingsDao(SQLiteDatabase db);

        ListView mListView = (ListView)findViewById(R.id.AlarmList);

        listAlarm = alarmDao.findAll();

//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                data,
//                R.layout.alarm_row,
//                new String[]{"hoge", "piyo"},
//                new int[]{R.id.HogeTextId, R.id.PiyoTextId}
//                );
//        mListView.setAdapter(adapter);

//        alarmList = new ArrayList<HashMap<Integer, AlarmSettingsDao>>();
//        alarmData = new HashMap<Integer, AlarmSettingsDao>();

        // リストデータを詰め込む前にインスタンス生成
        //data = new ArrayList<HashMap<String, String>>();

        // 一行の複数項目を HashMap で詰め込む
//        map = new HashMap<String, String>();
//        map.put("hoge", "hogeString");
//        map.put("piyo", "piyoString");
//        data.add(map);

//        map = new HashMap<String, String>();
//        map.put("piyo", "piyoString");
//        data.add(map);  
        
//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                data,
//                R.layout.alarm_row,
//                new String[]{"hoge", "piyo"},
//                new int[]{R.id.HogeTextId, R.id.PiyoTextId}
//                );
//                mListView.setAdapter(adapter);
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
}
