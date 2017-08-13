package finalProject;

public class Currency {
	private String name;
	private int unit;				//units per rate
	private String currencycode;
	private String country;			
	private double rate;			//the rate in relaion of SHEKEL
	private double change;			//change about previous day

	/**
	 * @param name
	 * @param unit
	 * @param currencycode
	 * @param country
	 * @param rate
	 * @param change
	 */
	public Currency(String name, int unit, String currencycode, String country, double rate, double change) {
		super();
		setName(name);
		setUnit(unit);
		setCurrencycode(currencycode);
		setCountry(country);
		setRate(rate);
		setChange(change);
		System.out.println("currency "+this.getName()+" was created");
	}
	public Currency(String name, String unit, String currencycode, String country, String rate, String change) {
		super();
		setName(name);
		setUnit(unit);
		setCurrencycode(currencycode);
		setCountry(country);
		setRate(rate);
		setChange(change);
		System.out.println("currency "+this.getName()+" was created");
	}
	/**
	 * @copy constructor
	 */
	public Currency(Currency src) {
		// TODO Auto-generated constructor stub
		this(src.getName(), src.getUnit(), src.getCurrencycode(), src.getCountry(), src.getRate(), src.getChange());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Currency [name=" + name + ", currencycode=" + currencycode + ", country=" + country + ", rate=" + rate
				+ "]";
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public void setUnit(String unit) {
		this.unit = Integer.parseInt(unit);
	}
	/**
	 * @return the currencycode
	 */
	public String getCurrencycode() {
		return currencycode;
	}
	/**
	 * @param currencycode the currencycode to set
	 */
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	public void setRate(String rate) {
		this.rate = Double.parseDouble(rate);
	}
	/**
	 * @return the change
	 */
	public double getChange() {
		return change;
	}
	/**
	 * @param change the change to set
	 */
	public void setChange(double change) {
		this.change = change;
	}
	public void setChange(String change) {
		this.change = Double.parseDouble(change);
	}
}
