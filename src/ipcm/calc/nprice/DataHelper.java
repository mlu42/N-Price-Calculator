package ipcm.calc.nprice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper {
	//Declaring constants for the database name, table name,
	//and the version of the database
	private static final String DATABASE_NAME = "myDatabase.db";
	private static final String DATABASE_TABLE = "prices";
	private static final int DATABASE_VERSION = 1;	

	//The name and column index of each column in the database
	public static final String KEY_ID = "_id";
	public static final int ID_COLUMN = 0;
	public static final String KEY_FERTPRICE = "fertprice";
	public static final int FERTPRICE_COLUMN = 1;
	public static final String KEY_NPERCENT = "npercent";
	public static final int NPERCENT_COLUMN = 2;
	public static final String KEY_PRICE = "price";
	public static final int PRICE_COLUMN = 3;
	public static final String KEY_DESCRIPTION = "description";
	public static final int DESCRIPTION_COLUMN = 4;

	//SQL statement to create a new database
	private static final String DATABASE_CREATE = 
		"create table " + DATABASE_TABLE + " (" +
		KEY_ID + " integer primary key autoincrement, " +
		KEY_FERTPRICE + " text not null, " +
		KEY_NPERCENT + " text not null, " +
		KEY_PRICE + " text not null, " +
		KEY_DESCRIPTION + " text not null " +
		");";

	//Variable to hold the database instance
	private SQLiteDatabase db;
	//Context of the application using the database
	private final Context context;
	//Database open/upgrade helper;
	private DBHelper dbHelper;
    
	// Constructor for a DataHelper
	// @param _context: the context in which the DataHelper will operate
	public DataHelper(Context _context)
	{
		context = _context;
		dbHelper = new DBHelper(context);
	}
	
	//The following two methods are interchangeable.
	//I just use them separately because it helps keep
	//things in order a little better.
	
	//Opens a DataHelper that is writable
	public DataHelper openForWrite() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	//Opens a DataHelper that is readable
	public DataHelper openForRead() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}
    
	//Closes the database.
	public void close()
	{
		db.close();
	}

	/*
	 * Inserts a quote into the database.
	 * @param n: the name of the person who said the quote.
	 * @param q: the quote.
	 */
	public long insertPrice(String fp, String np, String p, String d)
	{
		ContentValues price = new ContentValues();		
		price.put(KEY_FERTPRICE, fp);
		price.put(KEY_NPERCENT, np);
		price.put(KEY_PRICE, p);
		price.put(KEY_DESCRIPTION, d);
		return db.insert(DATABASE_TABLE, null, price);
	}
    
	/*
	 * Removes the selected quote from the database
	 * @param _rowIndex: the rowIndex of the quote to be removed.
	 */
	public void removePrice(long _rowIndex)
	{
		db.delete(DATABASE_TABLE, KEY_ID + "=" + _rowIndex, null);
	}
	
	//Removes all of the quotes from the database.
	public void removeAllPrices()
	{
		db.delete(DATABASE_TABLE, KEY_ID, null);
	}
    
	//Returns a cursor over the entire database.
	public Cursor getAllPrices()
	{
		Cursor q = db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_FERTPRICE, KEY_NPERCENT, KEY_PRICE, KEY_DESCRIPTION},
				null, null, null, null, null);		
		q.moveToFirst();		
		return q;
	}
    
	//Returns a cursor over the selected quote.
	//@param _rowIndex: the rowIndex of the quote to be returned.
	public Cursor getCursorQuote(long _rowIndex)
	{
		Cursor q = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_FERTPRICE, KEY_NPERCENT, KEY_PRICE, KEY_DESCRIPTION},
				KEY_ID + "=" + _rowIndex, null, null, null, null);
		
		if(q != null)
		{
			q.moveToFirst();
		}
		
		return q;		
	}
	
	/*
	 * Returns a quote in the form of a Quote object from a Cursor.
	 * @param cursor: the cursor to use.
	 * @param pos: the position (rowIndex) of the quote to be returned.
	 */
	public Price getQuote(Cursor cursor, int pos)
	{
		cursor.moveToPosition(pos);
		String fertprice = cursor.getString(cursor.getColumnIndex("fertprice"));
		String npercent = cursor.getString(cursor.getColumnIndex("npercent"));
		String price = cursor.getString(cursor.getColumnIndex("price"));
		String description = cursor.getString(cursor.getColumnIndex("description"));
		long id = cursor.getLong(cursor.getColumnIndex("_id"));		
		return new Price(fertprice, npercent, price, description, id);
	}
	
	/*
	 * Returns a quote in the form of a Quote object from a cursor.
	 * @param c: the cursor to use.
	 * Precondition: the Cursor must consist of only one quote.
	 */
	public Price convertToQuote(Cursor c)
	{
		String fertprice = c.getString(FERTPRICE_COLUMN);
		String npercent = c.getString(NPERCENT_COLUMN);
		String price = c.getString(PRICE_COLUMN);
		String description = c.getString(DESCRIPTION_COLUMN);
		long id = c.getLong(ID_COLUMN);		
		return new Price(fertprice, npercent, price, description, id);
	}
	
	/*
	 * Returns a quote in the form of a String from a cursor.
	 * @param c: the cursor to use.
	 * Precondition: the Cursor must consist of only one quote.
	 */
	public String priceToString(Cursor c)
	{		
		return "Fert. price: " + c.getString(FERTPRICE_COLUMN) + "\n" +
		"N percent: " + c.getString(NPERCENT_COLUMN) + "\n" +
		"Price: " + c.getString(PRICE_COLUMN) + "\n" +
		"Description: " + c.getString(DESCRIPTION_COLUMN) + "\n" +
		"ID: " + ((Long)c.getLong(ID_COLUMN)).toString();
	}
	
	/*
	 * Updates the quote in the given row with the string values provided.
	 * @param _rowIndex: the quote to update/
	 * @param n: the new name of the quote.
	 * @param q: the new quote text of the quote.
	 */
	public boolean updatePrice(long _rowIndex, String fp, String np, String p, String d)
	{
		ContentValues price = new ContentValues();
		price.put(KEY_FERTPRICE, fp);
		price.put(KEY_NPERCENT, np);
		price.put(KEY_PRICE, p);
		price.put(KEY_DESCRIPTION, d);
		db.update(DATABASE_TABLE, price, KEY_ID + "=" + _rowIndex, null);
		return true;
	}

	private static class DBHelper extends SQLiteOpenHelper
	{
		// Constructor for a DBHelper
		// @param _context: the context in which the DataHelper will operate
		public DBHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		//Called when no database exists on disk and that helper class
		//needs to create a new one.
		@Override
		public void onCreate(SQLiteDatabase _db)
		{
			_db.execSQL(DATABASE_CREATE);
		}

		//Called when there is a database version mismatch meaning that the version
		//of the database on disk needs to be upgraded to the current version.
		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
		{
			//Log the version upgrade
			Log.w("TaskDataHelper", "Upgrading from version " + _oldVersion 
					+ " to " +
					+ _newVersion + ", which will destroy all old data");

			//Upgrade the existing database to conform to the new version.
			//Multiple previous versions can be handled by comparing _oldVersion
			//and _newVersion values.
			_db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			//Create a new one.
			onCreate(_db);
		}
	}
}