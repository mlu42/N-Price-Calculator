package ipcm.calc.nprice;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PriceAdapter extends ArrayAdapter<Price>{	
	// Instance variables
	int resource;
	private ArrayList<Price> prices;
	
	/*
	 * Constructs a QuoteAdapter object.
	 * @param _context: the context in which the QuoteAdapter will be used.
	 * @param _resource: the "R.layout.*" reference to the parent layout
	 * 					 that will be used for each item in the ListView.
	 * @param _quotes: the data which will be binded to the ListView.
	 */
	public PriceAdapter(Context _context, int _resource, ArrayList<Price> _quotes)
	{
		super(_context, _resource, _quotes);
		resource = _resource;
		prices = _quotes;
	}
	
	/*
	 * Used by default to create a ListView item for each item in the list of data.
	 * @return: the view to insert into the list.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;		
		if(view == null)
		{
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    view = vi.inflate(resource, null);
		}
		Price price = prices.get(position);		
		if(price != null){
			TextView top = (TextView)view.findViewById(R.id.top);
			if(top!=null)
				top.setText("$" + price.getPrice() + "/ton - " + price.getNpercent() + "% N - $" + price.getFertprice() + "/lb N");			
			TextView bottom = (TextView)view.findViewById(R.id.bottom);
			if(bottom!=null)
				bottom.setText(price.getDescription());			
		}		
		return view;
	}
}