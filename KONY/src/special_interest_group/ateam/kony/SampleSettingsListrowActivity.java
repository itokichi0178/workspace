package special_interest_group.ateam.kony;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SampleSettingsListrowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_settings_listrow);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sample_settings_listrow, menu);
		return true;
	}

}
