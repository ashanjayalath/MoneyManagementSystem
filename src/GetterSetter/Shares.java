package GetterSetter;

import classPack.DBQuary;

public class Shares extends DBQuary {

    /**
     * @return the Total
     */
    public double getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(double Total) {
        this.Total = Total;
    }

    /**
     * @return the IshuMoney
     */
    public double getIshuMoney() {
        return IshuMoney;
    }

    /**
     * @param IshuMoney the IshuMoney to set
     */
    public void setIshuMoney(double IshuMoney) {
        this.IshuMoney = IshuMoney;
    }

    /**
     * @return the Holder
     */
    public double getHolder() {
        return Holder;
    }

    /**
     * @param Holder the Holder to set
     */
    public void setHolder(double Holder) {
        this.Holder = Holder;
    }

    /**
     * @return the Owner
     */
    public double getOwner() {
        return Owner;
    }

    /**
     * @param Owner the Owner to set
     */
    public void setOwner(double Owner) {
        this.Owner = Owner;
    }

    /**
     * @return the Other
     */
    public double getOther() {
        return Other;
    }

    /**
     * @param Other the Other to set
     */
    public void setOther(double Other) {
        this.Other = Other;
    }

    /**
     * @return the OtherLoan
     */
    public double getOtherLoan() {
        return OtherLoan;
    }

    /**
     * @param OtherLoan the OtherLoan to set
     */
    public void setOtherLoan(double OtherLoan) {
        this.OtherLoan = OtherLoan;
    }

    /**
     * @return the precent
     */
    public double getPrecent() {
        return precent;
    }

    /**
     * @param precent the precent to set
     */
    public void setPrecent(double precent) {
        this.precent = precent;
    }
    private double Total,IshuMoney,Holder,Owner,Other,OtherLoan,precent;
}
