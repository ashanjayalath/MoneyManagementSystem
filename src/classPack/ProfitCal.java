package classPack;

import java.util.ArrayList;

public class ProfitCal extends LoanCalculation{
    private ArrayList<Double> keyDataSet=new ArrayList<>();
    private ArrayList<Double> keyDataSet2=new ArrayList<>();
    public ArrayList<Double> Equated=new ArrayList<>();
    public ArrayList<Double> EquatedNormal=new ArrayList<>();
    public ArrayList<Double> Equal=new ArrayList<>();
    public ArrayList<Double> loanGetType=new ArrayList<>();
    public ArrayList<Double> DueValue=new ArrayList<>();
    public ArrayList<Integer> termsCountThrow=new ArrayList<>();
    public ArrayList<String> rate=new ArrayList<>();

    
   /* public ProfitCal(){
        Equated.clear();
        EquatedNormal.clear();
        Equal.clear();        
        loanGetType.clear();        
        DueValue.clear();        
        termsCountThrow.clear();        
        rate.clear();        
    }*/
    
    public void autoCal(double getLoanAmount,ArrayList keyData){//amount  double []day={1,10000,50000,20,19,18,17,5000};
        equatedNormalCalProfit=0;
        equatedCalProfit=0;
        equalCalProfit=0;
        Equated.clear();
        EquatedNormal.clear();
        Equal.clear(); 
        keyDataSet.clear();
        rate.clear();
        DueValue.clear();        
        termsCountThrow.clear();        
        rate.clear();
        loanGetType.clear();
        
        
        double loanValue=getLoanAmount;
        for(Object c:keyData){
           keyDataSet.add((Double) c);
        }

        int count=0;
        double profitCount1=0;
        double profitCount2=0;
        double profitCount3=0;
        double openCount=keyDataSet.get(1);
        double closingCount=keyDataSet.get(2);
        double terms=keyDataSet.get(0);
        double stepCount= keyDataSet.get(keyDataSet.size()-1);
        int openInterest = keyDataSet.size()-5;//3
        int lastInterest = keyDataSet.size()-2;//6
 
        while(openCount<=closingCount){
            while(true){//public double []day={1,10000,50000,20,19,18,17};
                rate.add(keyDataSet.get(openInterest)+" %");
                equatedInstalment(openCount,keyDataSet.get(openInterest), (int) terms);
                equatedInstalmentNormal(openCount, keyDataSet.get(openInterest), (int) terms);
                equalInstalment(openCount,keyDataSet.get(openInterest), (int) terms);
                profitCount1=equatedCalProfit;
                profitCount2=equatedNormalCalProfit;
                profitCount3=equalCalProfit;
                break;
            }
            if(openInterest==lastInterest){
                openInterest=lastInterest;
            }else{
                openInterest++;
            }
            int termsCount=(int)(loanValue/openCount);
            termsCountThrow.add(termsCount);
            DueValue.add(loanValue-(openCount*termsCount));
            Equated.add(profitCount1*termsCount);
            EquatedNormal.add(profitCount2*termsCount);
            Equal.add(profitCount3*termsCount);
            loanGetType.add(openCount);
            count++;
            openCount+=stepCount;
            
        }
                
    }
    public void autoCalTwo(double getLoanAmount,ArrayList keyData){    
        equatedNormalCalProfit=0;
        equatedCalProfit=0;
        equalCalProfit=0;
        Equated.clear();
        EquatedNormal.clear();
        Equal.clear(); 
        keyDataSet2.clear();
        rate.clear(); 
        DueValue.clear();        
        termsCountThrow.clear();        
        rate.clear();
        loanGetType.clear();
        
        double loanValue=getLoanAmount;
        for(Object c:keyData){
           keyDataSet2.add((Double) c);
        }
        
        int count=0;
        double profitCount1=0;
        double profitCount2=0;
        double profitCount3=0;
        double openCount=keyDataSet2.get(1);
        double closingCount=keyDataSet2.get(2);
        double terms=keyDataSet2.get(0);
        double stepCount= keyDataSet2.get(keyDataSet2.size()-1);
        //final 
        int openInterest = keyDataSet2.size()-2;//3{1,10000,100000,50,10000};

        
        
        while(openCount<=closingCount){
            while(true){//public double []day={1,10000,50000,20,19,18,17};
                rate.add(keyDataSet2.get(openInterest)+" %");
                equatedInstalment(openCount,keyDataSet2.get(openInterest),(int)terms);
                equatedInstalmentNormal(openCount,keyDataSet2.get(openInterest),(int)terms);
                equalInstalment(openCount,keyDataSet2.get(openInterest),(int)terms);
                profitCount1=equatedCalProfit;
                profitCount2=equatedNormalCalProfit;
                profitCount3=equalCalProfit;
                break;
            }

            int termsCount=(int)(loanValue/openCount);
            termsCountThrow.add(termsCount);
            DueValue.add(loanValue-(openCount*termsCount));
            Equated.add(profitCount1*termsCount);
            EquatedNormal.add(profitCount2*termsCount);
            Equal.add(profitCount3*termsCount);
            loanGetType.add(openCount);
            equalCalProfit=0;
            count++;
            openCount+=stepCount;
        }
        

    }
}
