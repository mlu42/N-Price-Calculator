package ipcm.calc.nprice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PriceList extends ListActivity {

    // Instance variables
	private ArrayList<Price> prices;
	int resID = R.layout.priceitem;
	long sId = 0;
	Context context = this;
	final DataHelper dataHelper = new DataHelper(this);
	ListView list;
	private Button sendEmail;
	
	public ArrayList<Price> updateList()
    {
	    dataHelper.openForRead();
		prices = new ArrayList<Price>();					
		Cursor allCursor = dataHelper.getAllPrices();			
		int count = allCursor.getCount();
		for(int i = 0; i < count; i++)
		{
			prices.add(dataHelper.getQuote(allCursor, i));			
		}		
		return prices;		
    }
	
	/*
	 * Creates a context menu when a price is long-clicked.
	 */
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
	{
		super.onCreateContextMenu(menu, v, menuInfo);		
		AdapterView.AdapterContextMenuInfo info = 
	        (AdapterView.AdapterContextMenuInfo)menuInfo;		
		sId = info.position;		
		menu.setHeaderTitle("Options");
		menu.add("Delete");
		menu.add("Delete all");
	}
	
	// Performs the necessary actions when items in the context menu are clicked upon.
	public boolean onContextItemSelected(MenuItem item)
	{
		if(item.getTitle().equals("Delete"))
		{			
			dataHelper.openForWrite();
			long id = prices.get(((Long)sId).intValue()).getID();
			dataHelper.removePrice(id);
			list.setAdapter( new PriceAdapter(this, resID, updateList()));
			dataHelper.close();
		}
		if(item.getTitle().equals("Delete all"))
		{
			dataHelper.openForWrite();
			dataHelper.removeAllPrices();
			list.setAdapter( new PriceAdapter(this, resID, updateList()));
			dataHelper.close();
		}
		return false;
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pricelist);       
        
        TextView header = (TextView) findViewById(R.id.header);
        sendEmail = (Button) findViewById(R.id.sendEmail);
        list = getListView();
        list.setCacheColorHint(Color.TRANSPARENT);
        
        // Gets the width and height of the display we are working with
        Display display = getWindowManager().getDefaultDisplay();
        final int width = display.getWidth();
        final int height = display.getHeight();
        
        header.setPadding(width/24, width/24, 0, width/24);
        
        list.setAdapter(new PriceAdapter(this, resID, updateList()));			
		dataHelper.close();
		
		registerForContextMenu(list);
		
		sendEmail.setOnClickListener( new OnClickListener(){
			
			public void onClick(View v)
			{
				dataHelper.openForWrite();
				
				String text = getPrices(dataHelper.getAllPrices());
				
				Calendar cal = Calendar.getInstance(Locale.getDefault());
				
				int month = cal.get(Calendar.MONTH) + 1;
				
				String subject = "N price report: " + month + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
				
				Intent intent = new Intent(Intent.ACTION_SEND);								// Sets the intent to be an email intent
	    		intent.setType("plain/text");												// I don't know what this does but it's necessary
	    		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "" });					// The email address to send to. We don't know who the user will want to send it to.
	    		intent.putExtra(Intent.EXTRA_SUBJECT, subject);	// The subject line
	    		intent.putExtra(Intent.EXTRA_TEXT, text);			// The body text
	    		startActivity(Intent.createChooser(intent, ""));							// Starts the email activity, passing the given data with it
			}
			
		});
    }
    
    private String getPrices(Cursor c)
    {
    	ArrayList<Price> prices = new ArrayList<Price>();
    	
    	c.moveToFirst();
    	
    	for(int i = 0; i < c.getCount(); i++)
    	{
    		Price p = new Price(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getLong(0));
    		
    		prices.add(p);
    		
    		c.moveToNext();    		
    	}
    	
    	String text = "";
    	
    	for(int i = 0; i < prices.size(); i++)
    	{
    		Price p = prices.get(i);
    		
    		text += i+1 + ")" + " " + p.getDescription() + "\n" +
    				"$" + p.getPrice() + "/ton" + " - " + p.getNpercent() + "% N - $" + p.getFertprice() + "/lb N \n\n";
    	}
    	
    	text += "" + "This email generated by the N Price Calculator for Android\n" + "ipcm.wisc.edu/apps";
    	
    	return text;
    }
}