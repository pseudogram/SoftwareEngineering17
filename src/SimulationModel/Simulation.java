package SimulationModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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

	private TradingExchange exchange;
	protected CsvImport import1;
	protected Clock clock;
	/**
	 * This constructs the stock market simulation
	 */
	public Simulation(){
		try {
			this.import1 = new CsvImport("InitialDataV2.csv", "InitialDataV2portfolio.csv");
		} catch (FileNotFoundException e) {
			System.out.println("Files not found");
		}
		this.exchange = new TradingExchange(import1.getTradedCompanies(), import1.getPortfolios());

		clock = new Clock(0,0,this.exchange);
	}

	//Gets a traded company (necessary to get all traded companies)
	public ArrayList<TradedCompany> getAllTradedCompanies(){
		return exchange.getAllCompanies();
	}


	public TradedCompany getCompany(String name) throws Exception {
		for (TradedCompany company: getAllTradedCompanies()) {
			if (company.getName() == name) {
				return company;
			}
		}
		throw new Exception("No company called "+name);
	}


	//Gets a trader (necessary to get all traders)
	public ArrayList<Trader> getAllTraders(){
		return exchange.getAllTraders();
	}

	//Feel free to give these different names but let me know so I can tweak things

	/**
	 * Method to call the runClock method within clock object which handles all trading.
	 *
	 * @param days amount of days to run simulation for
	 */
	public void runXSteps(int days){
		clock.runClock(days, this.exchange);
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


}