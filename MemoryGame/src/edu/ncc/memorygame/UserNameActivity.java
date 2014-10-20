package edu.ncc.memorygame;
//finish the onClick
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserNameActivity extends Activity implements OnClickListener {

	private Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name);
		button1 =(Button)findViewById(R.id.done);
		button1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_name, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) 
	{
		int id = v.getId();
		if(id == R.id.done)
		{
			Bundle b = new Bundle();
			String userName = ((EditText)findViewById(R.id.edit_box)).getText().toString();
			b.putString("name", userName);
			Intent nameIntent = new Intent(this,MainActivity.class);
			nameIntent.putExtras(b);
			startActivity(nameIntent);
		}
	}
}
