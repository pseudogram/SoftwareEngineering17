package SimulationModel;

/**
 *  A Random Trader may have 1 of 3 states in any 15 minute cylce:<br>
 *<br>
 *  BALANCED<br>
 *      Buys stocks using between 0% - 1% of it's available cash assets<br>
 *      Sells stocks valued between 0% - 1% of it's total portfolio value<br>
 *<br>
 *  AGGSELLER (Aggressive Seller)<br>
 *      Buys stocks using between 0% - 0.5% of it's available cash assets<br>
 *      Sells stocks valued between 0% - 2% of it's total portfolio value<br>
 *<br>
 *  AGGBUYER (Aggressive Buyer)<br>
 *      Buys stocks using between 0% - 2% of it's available cash assets<br>
 *      Sells stocks valued between 0% - 0.5% of it's total portfolio value<br>
 *<br>
 *<br>
 *  Furthermore, at the end of each 15 minute period there is a chance that the state of a trader<br>
 *  will swap from one to another, this is determined by the current state based on these probabilities:<br>
 *<br>
 *  BALANCED<br>
 *      seller(40%), balanced(60%), buyer(0%)<br>
 *<br>
 *  AGGSELLER<br>
 *      seller(10%), balanced(80%), buyer(10%)<br>
 *<br>
 *  AGGBUYER<br>
 *      seller(0%), balanced(70%), buyer(30%)<br>
 *<br>
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public enum TraderState {
    AGGSELLER ( 0.005, 0.02, 0.4, 0.6, 0),
    BALANCED ( 0.01, 0.01, 0.1, 0.8, 0.1  ),
    AGGBUYER ( 0.02, 0.005, 0, 0.7, 0.3 );

    private final double buyPerc, sellPerc, switchSell, switchBalance, switchBuy;

    TraderState(double buyPerc, double sellPerc, double switchSell, double switchBalance, double switchBuy) {
        this.buyPerc = buyPerc;
        this.sellPerc = sellPerc;
        this.switchSell = switchSell;
        this.switchBalance = switchBalance;
        this.switchBuy = switchBuy;
    }

    /**
     * Returns a double between 0 and 1 corresponding tho the percentage of the Asset wealth the trader will use to buy stocks when in a state.
     *
     * @return - Percentage of asset wealth to buy
     */
    public double getBuyPerc() {
        return buyPerc;
    }

    /**
     * Returns a double between 0 and 1 corresponding tho the percentage of the Asset wealth the trader will use to buy stocks when in a state.
     *
     * @return - Percentage of asset wealth to Sell.
     */
    public double getSellPerc() {
        return sellPerc;
    }

    /**
     * Returns the chance of swapping to an Aggressive Seller state.
     *
     * @return a double representing chance of swapping to an Aggressive Seller.
     */
    public double getSwitchSell() {
        return switchSell;
    }

    /**
     * Returns the chance of swapping to an Balanced state.
     *
     * @return a double representing chance of swapping to an Balanced Trader.
     */
    public double getSwitchBalance() {
        return switchBalance;
    }

    /**
     * Returns the chance of swapping to an Aggressive Buyer state.
     *
     * @return a double representing the percentage chance of swapping to an Aggressive Buyer.
     */
    public double getSwitchBuy() {
        return switchBuy;
    }
}
