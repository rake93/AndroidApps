package com.demo.xml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Basic Application to show how to retrieve XML and parse it
 * 
 * @author Nikhil Lingutla
 */

public class ParsedResultsActivity extends Activity{
	
	EditText results;
	Button backButton;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.parsedresultsscreen);
	        
	        results = (EditText)this.findViewById(R.id.parsedResults);
	        backButton = (Button)this.findViewById(R.id.backButton);
	        backButton.setOnClickListener(new OnClickListener()
	        {
				@Override
				public void onClick(View v) {
					finish();
				}
	        });
	        Intent i = getIntent();
	        String response = i.getStringExtra("parsedresults");
	        results.setText(response);
	 }
	  
}
