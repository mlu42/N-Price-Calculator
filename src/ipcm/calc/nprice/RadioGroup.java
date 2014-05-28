package ipcm.calc.nprice;

import java.util.ArrayList;

import android.widget.TextView;

public class RadioGroup {

	private ArrayList<TextView> buttons;
	
	private int set = -1;
	private int size;
	
	public RadioGroup()
	{
		buttons = null;
		set = -1;
		size = 0;
	}
	
	public RadioGroup(ArrayList<TextView> list)
	{
		buttons = list;
		set = -1;
		size = list.size();
	}
	
	public void set(int a)
	{
		switch(a){
			
			case 0:	buttons.get(0).setBackgroundResource(R.drawable.list_item_selected);
					buttons.get(1).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(2).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(3).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(4).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(5).setBackgroundResource(R.drawable.list_item_unselected);
					set = 0;
					break;
					
			case 1:	buttons.get(0).setBackgroundResource(R.drawable.list_item_unselected);
					buttons.get(1).setBackgroundResource(R.drawable.list_item_selected);	
					buttons.get(2).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(3).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(4).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(5).setBackgroundResource(R.drawable.list_item_unselected);
					set = 1;
					break;
			
			case 2:	buttons.get(0).setBackgroundResource(R.drawable.list_item_unselected);
					buttons.get(1).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(2).setBackgroundResource(R.drawable.list_item_selected);	
					buttons.get(3).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(4).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(5).setBackgroundResource(R.drawable.list_item_unselected);
					set = 2;
					break;	
			
			case 3:	buttons.get(0).setBackgroundResource(R.drawable.list_item_unselected);
					buttons.get(1).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(2).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(3).setBackgroundResource(R.drawable.list_item_selected);	
					buttons.get(4).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(5).setBackgroundResource(R.drawable.list_item_unselected);
					set = 3;
					break;	
			
			case 4:	buttons.get(0).setBackgroundResource(R.drawable.list_item_unselected);
					buttons.get(1).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(2).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(3).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(4).setBackgroundResource(R.drawable.list_item_selected);	
					buttons.get(5).setBackgroundResource(R.drawable.list_item_unselected);
					set = 4;
					break;	
			
			case 5:	buttons.get(0).setBackgroundResource(R.drawable.list_item_unselected);
					buttons.get(1).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(2).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(3).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(4).setBackgroundResource(R.drawable.list_item_unselected);	
					buttons.get(5).setBackgroundResource(R.drawable.list_item_selected);
					set = 5;
					break;
					
			default:	buttons.get(0).setBackgroundResource(R.drawable.list_item_unselected);
						buttons.get(1).setBackgroundResource(R.drawable.list_item_unselected);	
						buttons.get(2).setBackgroundResource(R.drawable.list_item_unselected);	
						buttons.get(3).setBackgroundResource(R.drawable.list_item_unselected);	
						buttons.get(4).setBackgroundResource(R.drawable.list_item_unselected);	
						buttons.get(5).setBackgroundResource(R.drawable.list_item_unselected);
						set = -1;
						break;
				
		}
	}
	
	public int getState()
	{
		return set;
	}
	
}
