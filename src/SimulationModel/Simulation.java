package SimulationModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class that will run the entire simulation of the Stock Market.
 *
 * It runs for a total of 365 days.
 * Trading occurs every 15 minutes between 9am and 5pm. Throughout this package we will refer to every 15 minute
 * interval as a cycle
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public class Simulation {

	/* **************************************************
     *
     *                  Fields
     *
     ****************************************************/
	private StockMarket market;
	protected CsvImport import1;
	protected Clock clock;

	/* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/
	/**
	 * Constructor  that initiates new instance of CsvImport using file paths to data already existent within the
	 * project, data is then processed by the CsvImport object and passed to a new instance of trading exchange through
	 * its params.
	 */
	public Simulation(List<TradedCompany> companies){
		clock = new Clock(0,0);
		market = new StockMarket(companies);
	}

	/**
	 * Constructor  that initiates new instance of CsvImport and uses the file path strings provided through the params,
	 * data is then processed by the CsvImport object and passed to a new instance of trading exchange through the params.
	 *
	 * @param tradedCompanyCsvFilePath file path to traded company .csv file
	 * @param portfolioCsvFilePath file path to portfolio .csv file
	 */
	public Simulation(String tradedCompanyCsvFilePath, String portfolioCsvFilePath) throws FileNotFoundException{
		try {
			this.import1 = new CsvImport(tradedCompanyCsvFilePath,portfolioCsvFilePath);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Files not found");
		}

		this.market = new StockMarket(import1.getTradedCompanies(), import1.getPortfolios());

	}

	/* **************************************************
	 *
	 *                  Methods
	 *
	 ****************************************************/
	//Gets a traded company (necessary to get all traded companies)
	public ArrayList<TradedCompany> getAllTradedCompanies(){
		return market.getAllTradedCompanies();
	}


	public TradedCompany getCompany(String name) throws Exception {
		return market.getCompany(name);
	}


	//Gets a trader (necessary to get all traders)
	public ArrayList<Trader> getAllTraders(){
		return market.getAllTraders();
	}

	//Feel free to give these different names but let me know so I can tweak things

	/**
	 * Method to call the runClock method within clock object which handles all trading.
	 *
	 * @param days amount of days to run simulation for
	 */
	public void runXSteps(int days){
		clock.runClock(days, market);
	}

	/**
	 * Returns the date in the following format:
	 * day of the week day/month/year HH:MM
	 *
	 * @return The current date found in clock object
	 */
	public String getDate(){
		return clock.getDate();
	}


	public ArrayList<Portfolio> getAllPortfolios() {
		return market.getAllPortfolios();
	}
}