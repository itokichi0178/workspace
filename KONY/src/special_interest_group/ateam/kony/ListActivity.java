package special_interest_group.ateam.kony;

import java.util.ArrayList;
import java.util.HashMap;

import special_interest_group.ateam.kony.db.AlarmSettingsDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListActivity extends Activity
{
	
    private ArrayList<HashMap<String, String>> data;
    private HashMap<String, String> map;
    
    private AlarmSettingsDao test;
    private ArrayList<HashMap<Integer, AlarmSettingsDao>> test2;

    
	@Override
	/**
	 * 画面作成時
	 * */
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		ListView mListView = (ListView)findViewById(R.id.AlarmList);
		
		// リストデータを詰め込む前にインスタンス生成
		data = new ArrayList<HashMap<String, String>>();
		
		
	
		// 一行の複数項目を HashMap で詰め込む
		map = new HashMap<String, String>();
		map.put("hoge", "hogeString");
		map.put("piyo", "piyoString");
		data.add(map);

//        map = new HashMap<String, String>();
//        map.put("piyo", "piyoString");
//        data.add(map);  

		SimpleAdapter adapter = new SimpleAdapter(
		        this,
                data,
                R.layout.alarm_row,
                new String[]{"hoge", "piyo"},
                new int[]{R.id.HogeTextId, R.id.PiyoTextId}
		        );
		        mListView.setAdapter(adapter);
		/***/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		// テスト
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
