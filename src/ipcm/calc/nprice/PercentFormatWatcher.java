package ipcm.calc.nprice;

import java.text.DecimalFormat;



import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Defines the way in which the second input field should behave
 * in order to format input for integer percentages less than 100
 */

class PercentFormatWatcher implements TextWatcher {
	
	// Layout objects that need to be read from or written to
	private EditText et;					// The EditText that is being input into
	
	// Other instance variables
	private String prevState = "";						// The previous state of the EditText
	private String generalCorrectInput = "\\d{1,3}";	// A java regex pattern for a percent. This one if 1-3 digits followed by a percent sign (e.g. 4%, 67%, 100%)
	private Calculator calculator;
	
	/*
	 * A constructor that takes all of the necessary layout objects as arguments.
	 */
	public PercentFormatWatcher(EditText e, Calculator calc)
	{
		et = e;
		calculator = calc;
	}
	
	/*
	 * An overridden method that defines the behavior before text is changed
	 */
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
		cursorPos();
	}
	
	/*
	 * An overridden method that defines the behavior of the EditText after the text has
	 * been changed. Essentially, it resets the cursor position to the correct position and
	 * re-calculates all the calculated values.
	 */
	public void afterTextChanged(Editable e)
	{		
		cursorPos();
		
		// Set the EditText to the new text if it doesn't already equal it.
		if(!et.getText().toString().equals(prevState))
			et.setText(prevState);
		
		calculator.calculate();
	}
	
	/*
	 * Sets the behavior that the EditText takes when text has been changed
	 */
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{		
		// Converts the new input to a string because it's easier to work with
		String text = s.toString();
		
		if(!text.equals(""))
		{			
			if(text.matches(generalCorrectInput))
			{
				int val = Integer.parseInt(text);
				
				if(val <= 100)
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
	
	/*
	 * A method that resets the cursor position to the 
	 */
	private void cursorPos()
	{
		// If there is text
			et.setSelection(et.getText().length());
	}
	
}
