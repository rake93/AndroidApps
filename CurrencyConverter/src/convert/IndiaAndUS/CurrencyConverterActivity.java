package convert.IndiaAndUS;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class CurrencyConverterActivity extends Activity {
	
	TextView rupees,dollars;
	EditText rupeesText,dollarsText;
	RadioButton rtod,dtor;
	Button convert;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        rupeesText = (EditText)this.findViewById(R.id.rupees_box);
        dollarsText = (EditText)this.findViewById(R.id.dollars_box);
        
        rtod = (RadioButton)this.findViewById(R.id.rtod);
        dtor = (RadioButton)this.findViewById(R.id.dtor);
        
        convert = (Button)this.findViewById(R.id.convert);
        convert.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		if(rtod.isChecked())
        			convertRupeeToDollar();
        		else
        			converDollarToRupee();
        	}
        });
   }
    
    private void convertRupeeToDollar()
    {
    	int rupee = Integer.parseInt(rupeesText.getText().toString());
    	dollarsText.setText(Integer.toString(rupee / 50));
    }
    
    private void converDollarToRupee()
    {
    	int dollar = Integer.parseInt(dollarsText.getText().toString());
    	rupeesText.setText(Integer.toString(dollar * 50));
    }
    
}