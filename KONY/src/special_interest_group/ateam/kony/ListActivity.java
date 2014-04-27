package special_interest_group.ateam.kony;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// テスト
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
