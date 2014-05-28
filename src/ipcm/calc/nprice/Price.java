package ipcm.calc.nprice;

public class Price {
	
	// Instance variables
	private long id;
	private String fertprice;
	private String npercent;
	private String price;
	private String description;
	
	// Constructors
	public Price()
	{
		id = -1;
		fertprice = null;
		npercent = null;
		price = null;
		description = null;
	}
	
	public Price(String _fertprice, String _npercent, String _price, String _description, long _id)
	{
		id = _id;
		fertprice = _fertprice;
		npercent = _npercent;
		price = _price;
		description = _description;
	}
	
	// Getters & setters
	public long getID()
	{
		return id;
	}
	
	public void setID(long _id)
	{
		id = _id;
	}
	
	public String getFertprice()
	{
		return fertprice;
	}
	
	public void setFertprice(String _fertprice)
	{
		fertprice = _fertprice;
	}
	
	public String getNpercent()
	{
		return npercent;
	}
	
	public void setNpercent(String _npercent)
	{
		npercent = _npercent;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public void setPrice(String _price)
	{
		price = _price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String _description)
	{
		description = _description;
	}

}
