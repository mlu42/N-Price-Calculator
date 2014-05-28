package ipcm.calc.nprice;

import java.text.DecimalFormat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Defines the behavior that the first and third EditTexts should
 * take when input is changed in order to create currency format.
 * This class limits the currency to whole numbers
 */

class CurrencyWholeFormatWatcher implements TextWatcher {
	
	// Layout objects
	private EditText et;					// The EditText being manipulated
	
	// Other instance variables
	private String prevState = "";										// The previous state of the EditText (et)
	private String singleDigit = "\\d";									// A java regex that is defined as a single digit (0-9)
	private String generalCorrectInput = "\\d{1,4}";	// The general form of a correct input ($####)
	private Calculator calculator;
	
	
	/*
	 * A constructor that takes all of the necessary layout objects
	 */
	public CurrencyWholeFormatWatcher(EditText e, Calculator calc)
	{
		et = e;
		calculator = calc;
	}
	
	/*
	 * The action to take before the text is changed. I did nothing
	 */
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
		
	}
	
	/*
	 * The action to take after the text is changed
	 */
	public void afterTextChanged(Editable e)
	{		
		// This if prevents infinite loops and stack overflows
		if(!et.getText().toString().equals(prevState))
			et.setText(prevState);	
		
		calculator.calculate();
		
		cursorPos();	// Adjusts the cursor position
	}
	
	/*
	 * Sets the behavior to take when the text is being changed. This is where
	 * the main work for formatting the input happens.
	 */
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// Converts the new input to a String because it's easier to work with
		
		String text = s.toString();
		
		if(!text.equals(""))
		{			
			if(text.matches(generalCorrectInput))
			{
				int val = Integer.parseInt(text);
				
				if(val <= 2000)
					prevState = ((Integer) val).toString();
			}
			else
			{
				
			}
		}
		else
		{
			prevState = "";
		}					
	}
	
	// Resets the cursor position to the end of the text
	private void cursorPos()
	{
		et.setSelection(et.getText().length());
	}
	
}