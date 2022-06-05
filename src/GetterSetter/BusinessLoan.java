package GetterSetter;

import java.util.Date;

public class BusinessLoan {
    private String loanName,loanType,monthlyPayment,profit,payment,loanStatus,loanNumber,comment;
    private Date loanIshuDate;
    private double loanInterestRate,loanAmount,dueAmount,TotalAmount,futurePayment;
    private int loanTerms;
    /**
     * @return the loanName
     */
    public String getLoanName() {
        return loanName;
    }

    /**
     * @param loanName the loanName to set
     */
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    /**
     * @return the loanType
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * @param loanType the loanType to set
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * @return the monthlyPayment
     */
    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    /**
     * @param monthlyPayment the monthlyPayment to set
     */
    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    /**
     * @return the profit
     */
    public String getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(String profit) {
        this.profit = profit;
    }

    /**
     * @return the payment
     */
    public String getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * @return the loanStatus
     */
    public String getLoanStatus() {
        return loanStatus;
    }

    /**
     * @param loanStatus the loanStatus to set
     */
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    /**
     * @return the loanNumber
     */
    public String getLoanNumber() {
        return loanNumber;
    }

    /**
     * @param loanNumber the loanNumber to set
     */
    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the loanIshuDate
     */
    public Date getLoanIshuDate() {
        return loanIshuDate;
    }

    /**
     * @param loanIshuDate the loanIshuDate to set
     */
    public void setLoanIshuDate(Date loanIshuDate) {
        this.loanIshuDate = loanIshuDate;
    }

    /**
     * @return the loanInterestRate
     */
    public double getLoanInterestRate() {
        return loanInterestRate;
    }

    /**
     * @param loanInterestRate the loanInterestRate to set
     */
    public void setLoanInterestRate(double loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    /**
     * @return the loanAmount
     */
    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the loanAmount to set
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the dueAmount
     */
    public double getDueAmount() {
        return dueAmount;
    }

    /**
     * @param dueAmount the dueAmount to set
     */
    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    /**
     * @return the TotalAmount
     */
    public double getTotalAmount() {
        return TotalAmount;
    }

    /**
     * @param TotalAmount the TotalAmount to set
     */
    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    /**
     * @return the futurePayment
     */
    public double getFuturePayment() {
        return futurePayment;
    }

    /**
     * @param futurePayment the futurePayment to set
     */
    public void setFuturePayment(double futurePayment) {
        this.futurePayment = futurePayment;
    }

    /**
     * @return the loanTerms
     */
    public int getLoanTerms() {
        return loanTerms;
    }

    /**
     * @param loanTerms the loanTerms to set
     */
    public void setLoanTerms(int loanTerms) {
        this.loanTerms = loanTerms;
    }

}
