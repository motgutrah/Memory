package edu.ncc.memorygame;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{

	private ImageButton[] buttons;
	private Button reset;
	private int numClicked;
	private int[] imageNums;
	private int[]buttonsClicked;
	private TextView welcomeText;
	private Boolean[] clicked;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle b = this.getIntent().getExtras();
		String userName = b.getString("name");
		welcomeText = ((TextView)findViewById(R.id.welcome_text));
		welcomeText.setText("welcome " + userName);
		// create the array to store references to the buttons
		buttons = new ImageButton[12];
		clicked = new Boolean[12];
		// get the id of the first button
		int idIndex = R.id.button0;

		// store the references into the array by cycling through the id indices & set the listener
		for (int i=0; i<buttons.length; i++) 
		{
			buttons[i] = (ImageButton)findViewById(idIndex++);
			buttons[i].setOnClickListener(this);
			clicked[i] = false;
		}

		//get the id's for the images and store 2 of each
		imageNums = new int[12];
		imageNums[0] = R.drawable.angry;
		imageNums[1] = R.drawable.angry;
		imageNums[2] = R.drawable.blushing;
		imageNums[3] = R.drawable.blushing;
		imageNums[4] = R.drawable.crying;
		imageNums[5] = R.drawable.crying;
		imageNums[6] = R.drawable.haha;
		imageNums[7] = R.drawable.haha;
		imageNums[8] = R.drawable.sad;
		imageNums[9] = R.drawable.sad;
		imageNums[10] = R.drawable.smile;
		imageNums[11] = R.drawable.smile;

		// randomize the values
		int r1, r2;
		int temp;
		for (int i=0; i<20; i++)
		{
			r1 = (int)(Math.random()*12);
			r2 = (int)(Math.random()*12);
			temp = imageNums[r1];
			imageNums[r1] = imageNums[r2];
			imageNums[r2]= temp;
		}

		numClicked = 0;
		buttonsClicked = new int[2];
		
    }

 
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setTitle("Memory Game");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	  int id = item.getItemId();
    	if(id == R.id.action_settings )
    	{
    	return true;
    	//switch(item.getItemId())
          //{
          //case R.id.action_reset:
      			//for (int j=0; j<buttons.length;j++)
      			//{
      				//buttons[j].setImageDrawable(null);
      				//buttons[j].setEnabled(true);
              //default:
    	}
    	
    	else if(id == R.id.action_reset)
    	{
    		for(int j = 0;j<buttons.length;j++)
    		{
    			buttons[j].setImageResource(R.drawable.defaultsmile);
    		}
    		int r1, r2;
    		int temp;
    		for (int i=0; i<20; i++)
    		{
    			r1 = (int)(Math.random()*12);
    			r2 = (int)(Math.random()*12);
    			temp = imageNums[r1];
    			imageNums[r1] = imageNums[r2];
    			imageNums[r2]= temp;
    		}

    		numClicked = 0;
    		buttonsClicked = new int[2];
        }
    	return super.onOptionsItemSelected(item);
    	}

	
	@Override
	public void onClick(View view) 
	{
		//if bittons and imageNumsa are parallel arrays
		//then you should be able to do something like:
		//buttons[x].setImageREsource(imageNums[x]);
		
		int index = view.getId() - R.id.button0;
		buttons[index].setImageResource(imageNums[index]);
		clicked[index] = true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
	int[] currentState = new int[12];
	int[] currentClick = new int[2];
	boolean[] currentClicked = new boolean[12];
	for(int i=0;i< imageNums.length;i++)
	{
		currentState[i] = imageNums[i];
		currentClicked[i] = clicked[i];
	}
	currentClick[0] = buttonsClicked[0];
	currentClick[1] = buttonsClicked[1];

	savedInstanceState.putIntArray("images", currentState);
	savedInstanceState.putIntArray("2click", buttonsClicked);
	savedInstanceState.putBooleanArray("clicked", currentClicked);
	super.onSaveInstanceState(savedInstanceState);
	}
	
	public void onRestoreInstanceState(Bundle restoreInstanceState)
	{	
		int[] currentState = new int[12];
		int[] currentClick = new int[2];
		boolean[] currentClicked = new boolean[12];
		super.onRestoreInstanceState(restoreInstanceState);
		currentState = restoreInstanceState.getIntArray("images");
		currentClick = restoreInstanceState.getIntArray("2click");
		currentClicked = restoreInstanceState.getBooleanArray("clicked");
		for(int i=0;i<currentState.length;i++)
		{
			imageNums[i]=currentState[i];
			clicked[i]= currentClicked[i];
				if(clicked[i] == true)
				{
					buttons[i].setImageResource(imageNums[i]);
				}
		}
		buttonsClicked[0] = currentClick[0];
		buttonsClicked[1] = currentClick[1];
				}
	
}
