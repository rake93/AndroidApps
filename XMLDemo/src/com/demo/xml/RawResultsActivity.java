package com.demo.xml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;

/**
 * Basic Application to show how to retrieve XML and parse it
 * 
 * @author Nikhil Lingutla
 */

public class RawResultsActivity extends Activity{
	
	EditText results;
	Button backButton;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.rawresultsscreen);
	        
	        results = (EditText)this.findViewById(R.id.resultBox);
	        backButton = (Button)this.findViewById(R.id.backButton);
	        backButton.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		finish();
	        	}
	        });
	        Intent i = getIntent();
	        String response = i.getStringExtra("rawresults");
	        results.setText(response);
	 }
	  
}
