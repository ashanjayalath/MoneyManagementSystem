package classPack;
import java.util.ArrayList;


public class LoanCalculation {
    double intrest;
    double loanValue;
    int month;
    int week;
    public double totalInterest;
    public double totalMonthlyPayment;
    public ArrayList<Double> monthlyPayment=new ArrayList<Double>();
    public ArrayList<Double> interestValue=new ArrayList<Double>();
    public double equatedNormalCalProfit=0;
    public double equatedCalProfit=0;
    public double equalCalProfit=0;

  

    
    public double power(double value,int terms){
        double getValue=value;
        int count=1;
        while (count<=(terms-1)){
            getValue=getValue*value;
            count++;
        }
        return getValue;
    }
    public int primeNumber(double value){
        //get full prim number
        int sendValue=0;
        if ((int)value < value){
            double getDouble=value-(int)value;
            if(getDouble>=0.1){
                sendValue=(int)value+1;
            }
            else {
                sendValue=(int)value;
            }
        }
        return sendValue;
    }
    public double roundNumber(double value){
        //get two decimals in double value
        double getValue=value;
        getValue=Math.round(getValue * 100.0)/100.0;
        return getValue;
    }
    
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    public double equatedInstalment(double amount,double interest,int month){
        
        LoanCalculation Loan=new LoanCalculation();        
        this.loanValue=amount;
        this.intrest=interest;
        this.month=month;

        double R=(intrest/12d/100d);
        double aniwarya=loanValue*R;
        double getR=Loan.power((1+R),month);
        double result=Loan.roundNumber((aniwarya * getR) / (getR-1));
        equatedCalProfit=(result*month)-loanValue;

        return result;      
    }
 //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    public double equatedInstalmentNormal(double amount,double interest,int weeks){
               
        this.loanValue=amount;
        this.intrest=interest;
        this.week=weeks;

        double R=(loanValue/100d)*intrest;
        double aniwarya=loanValue/week;
        equatedNormalCalProfit=R;

        return (R/week)+aniwarya;
    }
 
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void equalInstalment(double amount,double interest,int month){
        for(int v=0;v<monthlyPayment.size();v++){
            monthlyPayment.remove(v);
        }
        for(int r=0;r<interestValue.size();r++){
            interestValue.remove(r);
        }
        LoanCalculation Loan=new LoanCalculation();
        this.loanValue=amount;
        this.intrest=interest;
        this.month=month;
        //loan eke aniwaryen geviya uthu warikaya
        //mema kramayedi warika mudala samanupathikai..
        // poliya witharakhina wena shesha krmayata gewai
        double aniwarya=Loan.roundNumber(loanValue/month);
        double gen=Loan.roundNumber(loanValue);
        int count=0;
        while (count<month){
            double poliya=Loan.roundNumber((((gen*intrest)/100)/365)*30);
            totalInterest=totalInterest + Loan.primeNumber(poliya);
            double tot=Loan.primeNumber(poliya) + Loan.primeNumber(aniwarya);
            totalMonthlyPayment=totalMonthlyPayment+tot;
            monthlyPayment.add(count,tot);
            interestValue.add(count,Double.valueOf(Loan.primeNumber(poliya)));
            if(month==1){
                equalCalProfit=interestValue.get(count);
            }else{
                equalCalProfit+=interestValue.get(count);
            }
            gen=gen-aniwarya;
            count++;
        }

    }
    
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void equalInstalmentOriginalValues(double amount,double interest,int month){
        for(int f=0;f<monthlyPayment.size();f++){
            monthlyPayment.remove(f);
        }
        for(int r=0;r<interestValue.size();r++){
            interestValue.remove(r);
        }
        this.loanValue=amount;
        this.intrest=interest;
        this.month=month;
        //loan eke aniwaryen geviya uthu warikaya
        //mema kramayedi warika mudala samanupathikai..
        // poliya witharakhina wena shesha krmayata gewai
        double aniwarya=loanValue/month;
        double gen=loanValue;
        int count=0;
        while (count<month){
            double poliya=(((gen*intrest)/100)/365)*30;
            totalInterest=totalInterest + poliya;
            double tot=poliya + aniwarya;
            totalMonthlyPayment=totalMonthlyPayment+tot;
            monthlyPayment.add(count,tot);
            interestValue.add(count,poliya);
            gen=gen-aniwarya;
            count++;
        }

    }



}
