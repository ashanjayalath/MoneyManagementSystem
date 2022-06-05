package classPack;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewProfitCal extends LoanCalculation{

    public String getSetDay() {
        return setDay;
    }
    public String getSetWeekOne() {
        return setWeekOne;
    }
    public String getSetWeekTwo() {
        return setWeekTwo;
    }
    public String getSetMonth() {
        return setMonth;
    }
    public String getSetYear() {
        return setYear;
    }
    
    
//this class mainly focus getting a result in using current save rate table list values

    private String setDay;
    private String setWeekOne;
    private String setWeekTwo;
    private String setMonth;
    private String setYear;
    
    private final ArrayList<Double> day=new ArrayList<Double>();
    private final ArrayList<Double> dayRate=new ArrayList<Double>();
    private final ArrayList<Double> weekOne=new ArrayList<Double>();
    private final ArrayList<Double> weekOneRate=new ArrayList<Double>();
    private final ArrayList<Double> weekTwo=new ArrayList<Double>();
    private final ArrayList<Double> weekTwoRate=new ArrayList<Double>();
    private final ArrayList<Double> month=new ArrayList<Double>();
    private final ArrayList<Double> monthRate=new ArrayList<Double>();
    private final ArrayList<Double> year=new ArrayList<Double>();
    private final ArrayList<Double> yearRate=new ArrayList<Double>();

    
    private final ArrayList<Double>LoanAmount=new ArrayList<Double>();
    private final ArrayList<Double>LoanRate=new ArrayList<Double>();
    private final ArrayList<Double>Equated=new ArrayList<Double>();
    private final ArrayList<Double>EquatedNormal=new ArrayList<Double>();
    private final ArrayList<Double>Equal=new ArrayList<Double>();
    

    
    
    //load saved rating table data
    public void dataLoad(){//load selected rating value
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
            String sql = "SELECT * FROM `micro_rating`,micro_rating_keys WHERE micro_rating.Rating_Id_name=micro_rating_keys.Rating_Id_name ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                day.add(rs.getDouble("Day_By_Day"));
                dayRate.add(rs.getDouble("D_rate"));
                weekOne.add(rs.getDouble("Week_1"));
                weekOneRate.add(rs.getDouble("W_1_rate"));                    
                weekTwo.add(rs.getDouble("Week_2"));
                weekTwoRate.add(rs.getDouble("W_2_rate"));
                month.add(rs.getDouble("Month"));
                monthRate.add(rs.getDouble("Month_rate"));
                year.add(rs.getDouble("Year"));
                yearRate.add(rs.getDouble("Year_rate"));  
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    } 
    
    //normal cal profit day only
    public void autoDayOnly(int terms){//amount Day 
        equatedNormalCalProfit=0;
        equatedCalProfit=0;
        for(int z=0;z<day.size();z++){LoanAmount.add(day.get(z));System.out.println("day "+LoanAmount.get(z));}
        for(int z=0;z<dayRate.size();z++){LoanRate.add(dayRate.get(z));System.out.println("day B "+LoanRate.get(z));}  
        
        //day normal cal
        for(int k=0;k<LoanAmount.size();k++){
            equatedInstalmentNormal(LoanAmount.get(k),LoanRate.get(k), terms);
            EquatedNormal.add(equatedNormalCalProfit);
        }
        //day equated
        for(int k=0;k<LoanAmount.size();k++){
            equatedInstalment(LoanAmount.get(k),LoanRate.get(k), terms);
            Equated.add(equatedCalProfit);
        }    
        
        for(Double r:Equated){
            System.out.println("day Equated"+r);
        }
        
        for(Double r:EquatedNormal){
            System.out.println("day Normal"+r);
        }
        clearAllArrays();
    }
    
    //normal cal profit week,month,year
    public void autoSeparate(int terms,String type){   
        equatedNormalCalProfit=0;
        equatedCalProfit=0;
        equalCalProfit=0;
        
        if(type.equals("WeekOne")){
            for(int x=0;x<weekOne.size();x++){LoanAmount.add(weekOne.get(x));System.out.println("q "+x);}
            for(int x=0;x<weekOneRate.size();x++){LoanRate.add(weekOneRate.get(x));System.out.println("qq "+x);} 
        }else if(type.equals("WeekTwo")){
            for(int x=0;x<weekTwo.size();x++){LoanAmount.add(weekTwo.get(x));}
            for(int x=0;x<weekTwoRate.size();x++){LoanRate.add(weekTwoRate.get(x));}
        }else if(type.equals("Month")){
            for(int x=0;x<month.size();x++){LoanAmount.add(month.get(x));}
            for(int x=0;x<monthRate.size();x++){LoanRate.add(monthRate.get(x));} 
        }else{
            for(int x=0;x<year.size();x++){LoanAmount.add(year.get(x));}
            for(int x=0;x<yearRate.size();x++){LoanRate.add(yearRate.get(x));} 
        }
        
        
        for(Double z:LoanAmount){
            System.out.println("w "+z);
        }
        for(Double z:LoanRate){
            System.out.println("wR "+z);
        }
        
        //normal cal
        for(int k=0;k<LoanAmount.size();k++){
            equatedInstalmentNormal(LoanAmount.get(k),LoanRate.get(k), terms);//amount,interest,terms
            EquatedNormal.add(equatedNormalCalProfit);
        }
        //equated
        for(int k=0;k<LoanAmount.size();k++){
            equatedInstalment(LoanAmount.get(k),LoanRate.get(k), terms);//amount,interest,terms
            Equated.add(equatedCalProfit);
        }
        //equal
        for(int k=0;k<LoanAmount.size();k++){
            equalInstalment(LoanAmount.get(k),LoanRate.get(k), terms);//amount,interest,terms
            Equal.add(equalCalProfit);
        }

        //clearAllArrays();
    }  
    
    //clear all data
    public void clearAllArrays(){
        
        //remove all data loan amount an rate

        LoanAmount.clear();
        LoanRate.clear();
        Equated.clear();     
        EquatedNormal.clear();        
        Equal.clear();     
        day.clear();    
        dayRate.clear();               
        weekOne.clear();     
        weekOneRate.clear();   
        weekTwo.clear();
        weekTwoRate.clear();
        month.clear();
        monthRate.clear();
        yearRate.clear();
        year.clear();
    }
    
    
    
    
    //only creat string data set
    public void profitCal(int terms,double step,String type){
        ProfitCal bn=new ProfitCal();
        String rat="";
        int a=0;
        if(type.equals("day")){//day data set create
            for(int z=0;z<dayRate.size();z++){a=z;
                rat+=String.valueOf(dayRate.get(z))+",";
                if(z==dayRate.size()-1){a=dayRate.size()-2;}
                if(dayRate.get(z).equals(dayRate.get(a+1))){z++;continue;}
            }
        setDay=String.valueOf(terms)+","+day.get(0)+","+day.get(day.size()-1)+","+rat+String.valueOf(step);
        
        }else if(type.equals("weekOne")){//week one data set create
            for(int z=0;z<weekOneRate.size();z++){
                rat=String.valueOf(weekOneRate.get(z))+",";
            }
        setWeekOne=String.valueOf(terms)+","+weekOne.get(0)+","+weekOne.get(weekOne.size()-1)+","+rat+String.valueOf(step);
        
        }else if(type.equals("weekTwo")){//week two data set create
            for(int z=0;z<weekTwoRate.size();z++){a=z;
                rat+=String.valueOf(weekTwoRate.get(z))+",";
                if(z==weekTwoRate.size()-1){a=weekTwoRate.size()-2;}
                if(weekTwoRate.get(z).equals(weekTwoRate.get(a+1))){z++;continue;}
            }
        setWeekTwo=String.valueOf(terms)+","+weekTwo.get(0)+","+weekTwo.get(weekTwo.size()-1)+","+rat+String.valueOf(step);
        
        }else if(type.equals("month")){//month data set create
            for(int z=0;z<monthRate.size();z++){
                rat=String.valueOf(monthRate.get(z))+",";
            }
            
        setMonth=String.valueOf(terms)+","+month.get(0)+","+month.get(month.size()-1)+","+rat+String.valueOf(step);
        
        }else{        
            for(int z=0;z<yearRate.size();z++){// year set create
                rat=String.valueOf(yearRate.get(z))+",";
            }
        setYear=String.valueOf(terms)+","+year.get(0)+","+year.get(year.size()-1)+","+rat+String.valueOf(step);

        }       
        
    }
    
}
