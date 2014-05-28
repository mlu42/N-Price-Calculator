package ipcm.calc.nprice;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Calculator extends Activity {
	
	// Layout objects
	private LinearLayout mainLayout;
	private LinearLayout fertPriceLayout;
	private LinearLayout nPercentLayout;
	private TextView fertPriceLabel;
	private TextView nPercentLabel;
	private TextView fert1;
	private TextView fert2;
	private TextView fert3;
	private TextView fert4;
	private TextView fert5;
	private TextView fert6;
	private EditText fertPriceInput;
	private EditText nPercentInput;
	private TextView result;
	private TextView resultExplanation;
	private TextView addListButton;
	private TextView compareButton;
	
	// Other instance variables
	private LayoutParams params;
	Context context = this;
	final DataHelper dataHelper = new DataHelper(this);
	
	/*
     * Creates the context menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.context_menu, menu);
        return true;
    }
    
    /*
     * Specifies the behavior of the options menu item(s)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.help:	Intent intent = new Intent(this, Info.class);
            				startActivity(intent);
            				return true;
            default:		return false;				
            
            				
        }
    }
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        
        // Get the layout objects
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        fertPriceLayout = (LinearLayout) findViewById(R.id.fert_price_layout);
        nPercentLayout = (LinearLayout) findViewById(R.id.n_percent_layout);
        fertPriceLabel = (TextView) findViewById(R.id.fert_price_label);
        nPercentLabel = (TextView) findViewById(R.id.n_percent_label);
        fertPriceInput = (EditText) findViewById(R.id.fert_price_input);
        nPercentInput = (EditText) findViewById(R.id.n_percent_input);
        fert1 = (TextView) findViewById(R.id.fert1);
        fert2 = (TextView) findViewById(R.id.fert2);
        fert3 = (TextView) findViewById(R.id.fert3);
        fert4 = (TextView) findViewById(R.id.fert4);
        fert5 = (TextView) findViewById(R.id.fert5);
        fert6 = (TextView) findViewById(R.id.fert6);
        result = (TextView) findViewById(R.id.result);
        resultExplanation = (TextView) findViewById(R.id.result_Explanation);
        addListButton = (TextView) findViewById(R.id.add_to_list);
        compareButton = (TextView) findViewById(R.id.compare_prices);
        
        // Gets the width and height of the display we are working with
        Display display = getWindowManager().getDefaultDisplay();
        final int width = display.getWidth();
        final int height = display.getHeight();
        
        // Layout for the main layout
        mainLayout.setPadding(width/32, width/32, width/32, width/32);
        
        // Layout for the first input's layout
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, width/16, 0);
        
        fertPriceLayout.setLayoutParams(params);
        
        // Layout for the second input's layout
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(width/16, 0, 0, 0);
        
        nPercentLayout.setLayoutParams(params);
        
        // Layout for the first input's label
        fertPriceLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        
        // Layout for the first input
        params = new LayoutParams(width/3, width/6);
        params.topMargin = width/64;
        
        fertPriceInput.setLayoutParams(params);
        fertPriceInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        
        fertPriceInput.addTextChangedListener( new CurrencyWholeFormatWatcher(fertPriceInput, this));
        fertPriceInput.setRawInputType(Configuration.KEYBOARD_12KEY);
        
        // Layout for the second input's label
        nPercentLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        
        // Layout for the second input
        params = new LayoutParams(width/3, width/6);
        params.topMargin = width/64;
        
        nPercentInput.setLayoutParams(params);
        nPercentInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        
        nPercentInput.addTextChangedListener( new PercentFormatWatcher(nPercentInput, this));
	    nPercentInput.setRawInputType(Configuration.KEYBOARD_12KEY);
	    
	    // Layout for the list of items
	    params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	    
	    params.leftMargin = width/32;
	    params.rightMargin = width/32;
	    params.topMargin = width/128;
	    
	    fert2.setLayoutParams(params);
	    fert3.setLayoutParams(params);
	    fert4.setLayoutParams(params);
	    fert5.setLayoutParams(params);
	    fert6.setLayoutParams(params);
	    
	    params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	    
	    params.leftMargin = width/32;
	    params.rightMargin = width/32;
	    params.topMargin = width/16;
	    
	    fert1.setLayoutParams(params);
	    
	    int textSize = 16;
	    
	    fert1.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    fert2.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    fert3.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    fert4.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    fert5.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    fert6.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    
	    int padding = width/32;
	    
	    fert1.setPadding(padding, padding, padding, padding);
	    fert2.setPadding(padding, padding, padding, padding);
	    fert3.setPadding(padding, padding, padding, padding);
	    fert4.setPadding(padding, padding, padding, padding);
	    fert5.setPadding(padding, padding, padding, padding);
	    fert6.setPadding(padding, padding, padding, padding);
	    
	    ArrayList<TextView> list = new ArrayList<TextView>();
	    list.add(fert1);
	    list.add(fert2);
	    list.add(fert3);
	    list.add(fert4);
	    list.add(fert5);
	    list.add(fert6);
	    
	    final RadioGroup radioGroup = new RadioGroup(list);
	    
	    // Layout for the result text view
	    result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
	    
	    result.setPadding(0, width/32, 0, 0);
	    
	    // Layout for the result explanation
        resultExplanation.setMinHeight(width/16);
        resultExplanation.setPadding(0, 0, 0, width/32);
        
        // Layout for the buttons at the bottom
        padding = width/32;
        
        addListButton.setPadding(padding, padding, padding, padding);
        compareButton.setPadding(padding, padding, padding, padding);
        
        textSize = 16;
        
        addListButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    compareButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	    
	    params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    params.leftMargin = width/24;
	    
	    compareButton.setLayoutParams(params);
	    
	    /////////////////////////////////////////////////////////////////////////////////
	    /////////////////////////////////////////////////////////////////////////////////
	    /////////////////////////////////////////////////////////////////////////////////
	    
	    fert1.setOnClickListener( new OnClickListener(){
	    	
	    	public void onClick(View v)
	    	{
	    		if(radioGroup.getState() == 0)
	    		{
	    			radioGroup.set(-1);
	    			nPercentInput.setText("0");
	    		}
	    		else
	    		{
	    			radioGroup.set(0);
	    			nPercentInput.setText("34");
	    		}
	    		/*Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              radioGroup.set(-1); 
	    	         } 
	    	    }, 100);*/
	    		
	    		
	    	}
	    	
	    });
	    
	    fert2.setOnClickListener( new OnClickListener(){
	    	
	    	public void onClick(View v)
	    	{
	    		if(radioGroup.getState() == 1)
	    		{
	    			radioGroup.set(-1);
	    			nPercentInput.setText("0");
	    		}
	    		else
	    		{
	    			radioGroup.set(1);
	    			nPercentInput.setText("21");
	    		}
	    		/*Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              radioGroup.set(-1); 
	    	         } 
	    	    }, 100);*/
	    	}
	    	
	    });

	    fert3.setOnClickListener( new OnClickListener(){
	
			public void onClick(View v)
			{
				if(radioGroup.getState() == 2)
	    		{
	    			radioGroup.set(-1);
	    			nPercentInput.setText("0");
	    		}
	    		else
	    		{
	    			radioGroup.set(2);
	    			nPercentInput.setText("82");
	    		}
	    		/*Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              radioGroup.set(-1); 
	    	         } 
	    	    }, 100);*/
			}
		
	    });

		fert4.setOnClickListener( new OnClickListener(){
			
			public void onClick(View v)
			{
				if(radioGroup.getState() == 3)
	    		{
	    			radioGroup.set(-1);
	    			nPercentInput.setText("0");
	    		}
	    		else
	    		{
	    			radioGroup.set(3);
	    			nPercentInput.setText("46");
	    		}
	    		/*Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              radioGroup.set(-1); 
	    	         } 
	    	    }, 100);*/
			}
			
		});

		fert5.setOnClickListener( new OnClickListener(){
			
			public void onClick(View v)
			{
				if(radioGroup.getState() == 4)
	    		{
	    			radioGroup.set(-1);
	    			nPercentInput.setText("0");
	    		}
	    		else
	    		{
	    			radioGroup.set(4);
	    			nPercentInput.setText("28");
	    		}
	    		/*Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              radioGroup.set(-1); 
	    	         } 
	    	    }, 100);*/
			}
			
		});

		fert6.setOnClickListener( new OnClickListener(){
			
			public void onClick(View v)
			{
				if(radioGroup.getState() == 5)
	    		{
	    			radioGroup.set(-1);
	    			nPercentInput.setText("0");
	    		}
	    		else
	    		{
	    			radioGroup.set(5);
	    			nPercentInput.setText("32");
	    		}
	    		/*Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              radioGroup.set(-1); 
	    	         } 
	    	    }, 100);*/
			}
			
		});
		
		addListButton.setOnClickListener( new OnClickListener(){
			
			public void onClick(View v)
			{
				addListButton.setBackgroundResource(R.drawable.list_item_selected);
	    		Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              addListButton.setBackgroundResource(R.drawable.list_item_unselected);
	    	         } 
	    	    }, 100);
	    	    
	    	    
	    	    dataHelper.openForWrite();
	    	    
	    	    String _price = fertPriceInput.getText().toString();
	    	    String _npercent = nPercentInput.getText().toString();
	    	    String _fertprice = result.getText().toString();
	    	    	   _fertprice = _fertprice.substring(0, _fertprice.indexOf("/"));
	    	    	   _fertprice = _fertprice.substring(1);
	    	    	   
	    	    int a = radioGroup.getState();
	    	    String _description;
	    	    
	    	    switch(a)
	    	    {
	    	    		case 0: _description = "Ammonium Nitrate";
	    	    				break;
	    	    				
	    	    		case 1: _description = "Ammonium Sulfate";
	    						break;
	    				
	    	    		case 2: _description = "Anhydrous Ammonia";
	    						break;
	    				
	    	    		case 3: _description = "Urea";
	    						break;
	    				
	    	    		case 4: _description = "Urea Ammonium Nitrate (28%)";
	    						break;
	    				
	    	    		case 5: _description = "Urea Ammonium Nitrate (32%)";
	    						break;
	    				
	    				default: _description = "Custom fertilizer";
	    						break;
	    	    }
	    	    
	    	    dataHelper.insertPrice(_fertprice, _npercent, _price, _description);
	    	    dataHelper.close();
	    	    
	    	    fertPriceInput.setText("");
	    	    nPercentInput.setText("");
	    	    radioGroup.set(-1);
	    	    
	    	    Intent intent = new Intent(v.getContext(), PriceList.class);
	    	    startActivity(intent);
			}
			
		});
		
		compareButton.setOnClickListener( new OnClickListener(){
			
			public void onClick(View v)
			{
				compareButton.setBackgroundResource(R.drawable.list_item_selected);
	    		Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              compareButton.setBackgroundResource(R.drawable.list_item_unselected);
	    	         } 
	    	    }, 100);
	    	    
	    	    Intent intent = new Intent(v.getContext(), PriceList.class);
	    	    startActivity(intent);
			}
			
		});
		
		fertPriceInput.setOnTouchListener( new View.OnTouchListener(){
			
			
			public boolean onTouch(View v, MotionEvent event) {
				
				fertPriceInput.setFocusable(true);
				fertPriceInput.setFocusableInTouchMode(true);
				return false;
			}
			
		});
		
		nPercentInput.setOnTouchListener( new View.OnTouchListener(){
			
			
			public boolean onTouch(View v, MotionEvent event) {
				
				nPercentInput.setFocusable(true);
				nPercentInput.setFocusableInTouchMode(true);
				return false;
			}
			
		});
    }
    
    public void calculate()
    {
    	double fertPrice = 0;
    	double nPercent = 0;
    	double r = -1;
    	
    	if(!fertPriceInput.getText().toString().equals(""))
    	{
    		fertPrice = Integer.parseInt(fertPriceInput.getText().toString());
    	}
    	if(!nPercentInput.getText().toString().equals(""))
    	{
    		nPercent = Integer.parseInt(nPercentInput.getText().toString());
    	}
    	
    	// If both of the values are not 0, do the calculation
		if(fertPrice != 0 && nPercent != 0)
		{
			double temp = fertPrice*(100/nPercent)/2000;
			DecimalFormat twoPlaces = new DecimalFormat("#.##");
			r = Double.valueOf(twoPlaces.format(temp));
			//r = fertPrice*(100/nPercent)/2000;
		}
		
		if(r != -1)
		{
			String s = ((Double)r).toString();
			
			if(s.length() == 3)
			{
				s += "0";
			}
			
			result.setText("$" + s + "/lb N");
			resultExplanation.setText("based on $" + ((Integer)((Double) fertPrice).intValue()).toString() + " per ton at " + ((Integer)((Double) nPercent).intValue()).toString() + "% N");
		}
		else
		{
			result.setText("$0.00/lb N");
			resultExplanation.setText("");
		}
		
		//resultExplanation.setText("Hello");
		//resultExplanation.setText("based on $" + ((Integer)((Double) fertPrice).intValue()).toString() + " per ton at " + ((Integer)((Double) nPercent).intValue()).toString() + "% N");
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];
            
            if (event.getAction() == MotionEvent.ACTION_UP 
            			&& (x < w.getLeft() || x >= w.getRight() 
            			|| y < w.getTop() || y > w.getBottom()) ) { 
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
     return ret;
    }
}