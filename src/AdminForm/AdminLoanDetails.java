package AdminForm;


import GetterSetter.Rating;
import GetterSetter.RatingKey;
import GetterSetter.Shares;
import classPack.DBQuary;
import classPack.DatabaseConnection;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import classPack.LoanCalculation;
import classPack.NewProfitCal;
import classPack.ProfitCal;
import classPack.ValidityCheck;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import scrollbar.ScrollBarCustom;

public class AdminLoanDetails extends javax.swing.JPanel {

    public ArrayList<String> getDay() {
        return day;
    }
    public ArrayList<String> getDayr() {
        return dayr;
    }
    public ArrayList<String> getWeekOne() {
        return weekOne;
    }
    public ArrayList<String> getWeekOner() {
        return weekOner;
    }
    public ArrayList<String> getWeekTwo() {
        return weekTwo;
    }
    public ArrayList<String> getWeekTwor() {
        return weekTwor;
    }
    public ArrayList<String> getMonth() {
        return month;
    }
    public ArrayList<String> getMonthr() {
        return monthr;
    }
    public ArrayList<String> getYear() {
        return year;
    }
    public ArrayList<String> getYearr() {
        return yearr;
    }

 
    private int d,w1,w2,m,y;
    private double dd,ww1,ww2,mm,yy;
    private int checked;
    private int comboGetChecked;
    private double getValue;
    private double getLoanValue;
    private double calculateShares;
    private double presentage=40;
    private String TypeRate;
    private boolean limit;
    public double []val=new double[100];
    public String [] selectIndex=new String[200];
    public String []typesAndPrecent=new String[3];
    
    private ArrayList<String> day=new ArrayList<String>();
    private ArrayList<String> dayr=new ArrayList<String>();
    private ArrayList<String> weekOne=new ArrayList<String>();
    private ArrayList<String> weekOner=new ArrayList<String>();
    private ArrayList<String> weekTwo=new ArrayList<String>();
    private ArrayList<String> weekTwor=new ArrayList<String>();
    private ArrayList<String> month=new ArrayList<String>();
    private ArrayList<String> monthr=new ArrayList<String>();
    private ArrayList<String> year=new ArrayList<String>();
    private ArrayList<String> yearr=new ArrayList<String>();
  
    
    public AdminLoanDetails() {
        initComponents();        
        secondLoadDataComboBox();
        LoanCalLoad();
        getSharesData();

        ratingName.setSelectedItem("Default");
        ratingName.addItem("");
        lblSetPresent.setText(Double.toString(presentage));
        colors(presentage,0d);
        rdPrimeValue.setSelected(true);  
        btnUpdateAndSave.setEnabled(false);
        btnSave.setEnabled(false);
        
        
        ScrollBarCustom sc1=new ScrollBarCustom();
        jScrollPane4.setVerticalScrollBar(new ScrollBarCustom());
        sc1.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane4.setHorizontalScrollBar(sc1);
        
        ScrollBarCustom sc2=new ScrollBarCustom();
        jScrollPane5.setVerticalScrollBar(new ScrollBarCustom());
        sc2.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane5.setHorizontalScrollBar(sc2);  
        
        dayTerms.setText("1");daySteps.setText("5000");
        week1Terms.setText("12");week1Steps.setText("10000");
        week2Terms.setText("24");week2Steps.setText("10000");
        monthTerms.setText("12");monthSteps.setText("50000");
        yearTerms.setText("60");yearSteps.setText("100000");
        
    }

    RatingKey rate =new RatingKey();
    Shares share=new Shares();
    Rating rating=new Rating();
    public void autoCal(){
        d=Integer.parseInt(dayTerms.getText());
        w1=Integer.parseInt(week1Terms.getText());
        w2=Integer.parseInt(week2Terms.getText());
        m=Integer.parseInt(monthTerms.getText());
        y=Integer.parseInt(yearTerms.getText());
        
        dd=Double.valueOf(daySteps.getText());
        ww1=Double.valueOf(week1Steps.getText());
        ww2=Double.valueOf(week2Steps.getText());
        mm=Double.valueOf(monthSteps.getText());
        yy=Double.valueOf(yearSteps.getText());
        
        ArrayList<Double> day=new ArrayList<>();
        ArrayList<Double> weekOne=new ArrayList<>();
        ArrayList<Double> weekTwo=new ArrayList<>();
        ArrayList<Double> month=new ArrayList<>();
        ArrayList<Double> year=new ArrayList<>();
        NewProfitCal bz=new NewProfitCal();
        bz.clearAllArrays();
        bz.dataLoad();
        
        //create string data set
        bz.profitCal(d,dd,"day");
        bz.profitCal(w1,ww1,"weekOne");
        bz.profitCal(w2,ww2,"weekTwo");
        bz.profitCal(m,mm,"month");
        bz.profitCal(y,yy,"year");

        
        
        //string data set convert double and store array list
        String v[]=bz.getSetDay().split(",");
        for(int z=0;z<v.length;z++){
            day.add(Double.valueOf(v[z]));
        }
        String w[]=bz.getSetWeekOne().split(",");
        for(int zz=0;zz<w.length;zz++){
            weekOne.add(Double.valueOf(w[zz]));
        }
        String cq[]=bz.getSetWeekTwo().split(",");
        for(int zzz=0;zzz<cq.length;zzz++){
            weekTwo.add(Double.valueOf(cq[zzz]));
        }
        String r[]=bz.getSetMonth().split(",");
        for(int zzzz=0;zzzz<r.length;zzzz++){
            month.add(Double.valueOf(r[zzzz]));
        }
        String kk[]=bz.getSetYear().split(",");
        for(int zzzzz=0;zzzzz<kk.length;zzzzz++){
            year.add(Double.valueOf(kk[zzzzz]));
        }        

        ProfitReportGet report=new ProfitReportGet();
        ProfitCal auto=new ProfitCal();
        double loan=Double.parseDouble(txtLoanAmount.getText());

        int co=1;
        if(loan>=day.get(1)){
            double last=day.get(2);
            auto.autoCal(loan,day);
            int a=0;
            while(true){
                    double max=maxValue(auto.Equated.get(a),auto.EquatedNormal.get(a),auto.Equal.get(a));
                    DefaultTableModel model = (DefaultTableModel)report.reportTable.getModel();
                    model.addRow(new Object[]{co,"Day by Day",auto.loanGetType.get(a),auto.rate.get(a),auto.termsCountThrow.get(a),auto.DueValue.get(a),auto.Equated.get(a),auto.EquatedNormal.get(a),auto.Equal.get(a),max,TypeRate});
                    double dc=auto.loanGetType.get(a);
                    if(last==dc){
                        model.addRow(new Object[]{});
                        break;
                    }
                a++;
                co++;
            }
        }co--;
        if(loan>=weekOne.get(1)){
            int c=0;
            double last=weekOne.get(2);
            auto.autoCalTwo(loan,weekOne);
            while(true){
                    double max=maxValue(auto.Equated.get(c),auto.EquatedNormal.get(c),0);
                    DefaultTableModel model = (DefaultTableModel)report.reportTable.getModel();
                    model.addRow(new Object[]{co,"Week One",auto.loanGetType.get(c),auto.rate.get(c),auto.termsCountThrow.get(c),auto.DueValue.get(c),auto.Equated.get(c),auto.EquatedNormal.get(c),0,max,TypeRate});
                    double dc=auto.loanGetType.get(c);
                    if(last==dc){
                        model.addRow(new Object[]{});
                        break;
                    }
                c++;
                co++;
            }
        }co--;
        if(loan>=weekTwo.get(1)){
            int b=0;
            double last=weekTwo.get(2);
            auto.autoCal(loan,weekTwo);
            while(true){
                    double max=maxValue(auto.Equated.get(b),auto.EquatedNormal.get(b),0);
                    DefaultTableModel model = (DefaultTableModel)report.reportTable.getModel();
                    model.addRow(new Object[]{co,"Week Two",auto.loanGetType.get(b),auto.rate.get(b),auto.termsCountThrow.get(b),auto.DueValue.get(b),auto.Equated.get(b),auto.EquatedNormal.get(b),0,max,TypeRate});
                     double dc=auto.loanGetType.get(b);
                    if(last==dc){
                        model.addRow(new Object[]{});
                        break;
                    }
                b++;
                co++;
            }
        }co--;
        if(loan>=month.get(1)){
            int x=0;
            double last=month.get(2);
            auto.autoCalTwo(loan,month);
            while(true){
                    double max=maxValue(auto.Equated.get(x),auto.EquatedNormal.get(x),auto.Equal.get(x));
                    DefaultTableModel model = (DefaultTableModel)report.reportTable.getModel();
                    model.addRow(new Object[]{co,"Month",auto.loanGetType.get(x),auto.rate.get(x),auto.termsCountThrow.get(x),auto.DueValue.get(x),auto.Equated.get(x),auto.EquatedNormal.get(x),auto.Equal.get(x),max,TypeRate});
                    double dc=auto.loanGetType.get(x);
                    if(last==dc){
                        model.addRow(new Object[]{});
                        break;
                    }
                x++;
                co++;
            }
        }co--;
        if(loan>=year.get(1)){
            int t=0;
            double last=year.get(2);
            auto.autoCalTwo(loan,year);
            while(true){
                    double max=maxValue(auto.Equated.get(t),auto.EquatedNormal.get(t),auto.Equal.get(t));
                    DefaultTableModel model = (DefaultTableModel)report.reportTable.getModel();
                    model.addRow(new Object[]{co,"Year",auto.loanGetType.get(t),auto.rate.get(t),auto.termsCountThrow.get(t),auto.DueValue.get(t),auto.Equated.get(t),auto.EquatedNormal.get(t),auto.Equal.get(t),max,TypeRate});
                    double dc=auto.loanGetType.get(t);
                    if(last==dc){
                        model.addRow(new Object[]{});
                        break;
                    }
                t++;
                co++;
            }

        }
        
        //this.setExtendedState(LoanCalculator.ICONIFIED);
        report.lblLoanAmountSet.setText(txtLoanAmount.getText());
        report.show();
        report.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public boolean chekStepseValid(){
        if(Double.parseDouble(daySteps.getText())>0 && Double.parseDouble(week1Steps.getText())>0 && Double.parseDouble(week2Steps.getText())>0 && Double.parseDouble(monthSteps.getText())>0 && Double.parseDouble(yearSteps.getText())>0){
            limit=true;
        }else{
            limit=false;
        }
        
        return limit;
    }
    public void update(String getId,String getUpName,int v){
        DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
        String rate="";
        String id=getId;
        Double day,d_rate,week_1,week_1_rate,week_2,week_2_rate,month,month_rate,year,year_rate;
        int a=v;
        String getName=getUpName; 
        if(ratingTable.getRowCount()>=1){
            
            //int id;
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                    day = (Double) model.getValueAt(a, 1);
                    d_rate = (Double) model.getValueAt(a, 2);
                    week_1 = (Double) model.getValueAt(a, 3);
                    week_1_rate = (Double) model.getValueAt(a, 4);
                    week_2 = (Double) model.getValueAt(a, 5);
                    week_2_rate = (Double) model.getValueAt(a, 6);
                    month = (Double) model.getValueAt(a, 7);
                    month_rate = (Double) model.getValueAt(a, 8);
                    year = (Double) model.getValueAt(a, 9);
                    year_rate = (Double) model.getValueAt(a, 10);

                    String sql = "UPDATE micro_rating SET Day_By_Day='"+day+"' , D_rate='"+d_rate+"' ,Week_1='"+week_1+"' ,W_1_rate='"+week_1_rate+"' ,Week_2 ='"+week_2+"' ,W_2_rate ='"+week_2_rate+"'  ,Month='"+month+"'  ,Month_rate='"+month_rate+"'  ,Year='"+year+"' ,Year_rate ='"+year_rate+"' , Rating_Id_name='"+getName+"'    WHERE Id='"+id+"' ";// ,Rating_Id='"+rate+"'  AND id='"+id+"' 

                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();

                JOptionPane.showMessageDialog(null,"Sucesss...");
                model.setRowCount(0);
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }    
    public void tableRefresh(String getRateName,int getType){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM micro_rating WHERE Rating_Id_name='"+getRateName+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();

            if(getType==0){
                while (rs.next()) {

                    DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
                    Object w[]={rs.getDouble("id"),rs.getDouble("Day_By_Day"),rs.getDouble("D_rate"),rs.getDouble("Week_1"),rs.getDouble("W_1_rate"),rs.getDouble("Week_2"),rs.getDouble("W_2_rate"),rs.getDouble("Month"),rs.getDouble("Month_rate"),rs.getDouble("Year"),rs.getDouble("Year_rate"),rs.getString("Rating_Id_name")};
                    model.addRow(w);

                }
            }else if(getType==1){
                while (rs.next()) {

                    DefaultTableModel model = (DefaultTableModel) ratingTableLoan.getModel();
                    Object w[]={rs.getDouble("id"),rs.getDouble("Day_By_Day"),rs.getDouble("D_rate"),rs.getDouble("Week_1"),rs.getDouble("W_1_rate"),rs.getDouble("Week_2"),rs.getDouble("W_2_rate"),rs.getDouble("Month"),rs.getDouble("Month_rate"),rs.getDouble("Year"),rs.getDouble("Year_rate"),rs.getString("Rating_Id_name")};
                    model.addRow(w);

                }                
            }else{
                
            }
            
            

            //ratingName.addItem(getRateName);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void secondLoadDataComboBox(){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT Rating_Id_name FROM micro_rating GROUP BY Rating_Id_name HAVING COUNT(*) > 1 & COUNT(*) <= 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Rating_Id_name = rs.getString("Rating_Id_name");                               
                ratingName.addItem(Rating_Id_name);
                ratingName1.addItem(Rating_Id_name);
            }
            
            String sql2 = "SELECT * FROM micro_rating_keys";
            PreparedStatement pst1 = conn.prepareStatement(sql2);
            
            ResultSet rs1 = pst1.executeQuery();   
            while (rs1.next()) {
                String vb=rs1.getString("Rating_Id_name");
                lblDisplay.setText(vb);
                lblDisplayDefaultRating.setText(vb);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void LoanCalLoad(){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM micro_rating,micro_rating_keys WHERE micro_rating_keys.Rating_Id_name = micro_rating.Rating_Id_name ";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                DefaultTableModel model = (DefaultTableModel) ratingTableLoan.getModel();
                Object w[]={rs.getDouble("Day_By_Day"),rs.getDouble("D_rate"),rs.getDouble("Week_1"),rs.getDouble("W_1_rate"),rs.getDouble("Week_2"),rs.getDouble("W_2_rate"),rs.getDouble("Month"),rs.getDouble("Month_rate"),rs.getDouble("Year"),rs.getDouble("Year_rate"),rs.getString("Rating_Id_name")};
                model.addRow(w);
                
                day.add(String.valueOf(rs.getDouble("Day_By_Day")));
                dayr.add(String.valueOf(rs.getDouble("D_rate")));
                weekOne.add(String.valueOf(rs.getDouble("Week_1")));
                weekOner.add(String.valueOf(rs.getDouble("W_1_rate")));
                weekTwo.add(String.valueOf(rs.getDouble("Week_2")));
                weekTwor.add(String.valueOf(rs.getDouble("W_2_rate")));
                month.add(String.valueOf(rs.getDouble("Month")));
                monthr.add(String.valueOf(rs.getDouble("Month_rate")));
                year.add(String.valueOf(rs.getDouble("Year")));
                yearr.add(String.valueOf(rs.getDouble("Year_rate")));    
                 
                
            }
            
            ratingName.addItem(rate.getRating_Id_name());
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void tableDataUpdate(String getUpdate){       
        String update=getUpdate;
        try{
            String day,d_rate,week_1,week_1_rate,week_2,week_2_rate,month,month_rate,year,year_rate;                
            String tday,td_rate,tweek_1,tweek_1_rate,tweek_2,tweek_2_rate,tmonth,tmonth_rate,tyear,tyear_rate;
            String id,tid;
            
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM micro_rating WHERE Rating_Id_name='"+update+"' ";
            PreparedStatement pst = conn.prepareStatement(sql); 
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
                int a=0;
                while (rs.next()) {
                    
                    id=rs.getString("id");
                    day = rs.getString("Day_By_Day");d_rate = rs.getString("D_rate");
                    week_1 = rs.getString("Week_1");week_1_rate = rs.getString("W_1_rate");
                    week_2 = rs.getString("Week_2");week_2_rate = rs.getString("W_2_rate");
                    month = rs.getString("Month");month_rate = rs.getString("Month_rate");
                    year = rs.getString("Year");year_rate = rs.getString("Year_rate");
                    
                    tid=model.getValueAt(a, 0).toString();
                    tday =model.getValueAt(a, 1).toString();td_rate = model.getValueAt(a, 2).toString();
                    tweek_1 =model.getValueAt(a, 3).toString();tweek_1_rate =model.getValueAt(a, 4).toString();
                    tweek_2 =model.getValueAt(a, 5).toString();tweek_2_rate = model.getValueAt(a, 6).toString();
                    tmonth =model.getValueAt(a, 7).toString();tmonth_rate =model.getValueAt(a, 8).toString();
                    tyear =model.getValueAt(a, 9).toString();tyear_rate =model.getValueAt(a, 10).toString();   

                    if(day == null ? tday != null : !day.equals(tday)){ update(id,update,a);}
                    if(d_rate == null ? td_rate != null : !d_rate.equals(td_rate)){ update(id,update,a);}
                    if(week_1 == null ? tweek_1 != null : !week_1.equals(tweek_1)){ update(id,update,a);}
                    if(week_1_rate == null ? tweek_1_rate != null : !week_1_rate.equals(tweek_1_rate)){  update(id,update,a);}
                    if(week_2 == null ? tweek_2 != null : !week_2.equals(tweek_2)){ update(id,update,a);}
                    if(week_2_rate == null ? tweek_2_rate != null : !week_2_rate.equals(tweek_2_rate)){ update(id,update,a);}
                    if(month == null ? tmonth != null : !month.equals(tmonth)){ update(id,update,a);}
                    if(month_rate == null ? tmonth_rate != null : !month_rate.equals(tmonth_rate)){ update(id,update,a);}
                    if(year == null ? tyear != null : !year.equals(tyear)){  update(id,update,a);}
                    if(year_rate == null ? tyear_rate != null : !year_rate.equals(tyear_rate)){ update(id,update,a);}

                    a++;
                }
            
            conn.close();
        }catch(Exception z){
            System.err.println(z);
        }

    }
    public void tableDataInsert(String name){
        DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
        String getName=name;
        String rate="";
        if(ratingTable.getRowCount()>=1){
            
            Double day,d_rate,week_1,week_1_rate,week_2,week_2_rate,month,month_rate,year,year_rate;
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                for(int a=0;a<model.getRowCount();a++) {

                    day = (Double) model.getValueAt(a, 1);
                    d_rate = (Double) model.getValueAt(a, 2);
                    week_1 = (Double) model.getValueAt(a, 3);
                    week_1_rate = (Double) model.getValueAt(a, 4);
                    week_2 = (Double) model.getValueAt(a, 5);
                    week_2_rate = (Double) model.getValueAt(a, 6);
                    month = (Double) model.getValueAt(a, 7);
                    month_rate = (Double) model.getValueAt(a, 8);
                    year = (Double) model.getValueAt(a, 9);
                    year_rate = (Double) model.getValueAt(a, 10);

                    String sql = "INSERT INTO micro_rating(Day_By_Day,D_rate,Week_1,W_1_rate,Week_2,W_2_rate,Month,Month_rate,Year,Year_rate,Rating_Id_name) VALUES('"+day+"','"+d_rate+"','"+week_1+"','"+week_1_rate+"','"+week_2+"','"+week_2_rate+"','"+month+"','"+month_rate+"','"+year+"','"+year_rate+"','"+getName+"')";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();

                }
                ratingName.addItem(getName);
                JOptionPane.showMessageDialog(null,"Sucesss...");
                model.setRowCount(0);
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
    public void getSharesData(){
        DBQuary.getSharesData(share);
        txtCompanyShares.setText(String.valueOf(share.getTotal()));
            
        double a=Double.parseDouble(txtCompanyShares.getText());
        txtLoanAmount.setText(Double.toString((a/100)*presentage));
        txtRateMoney.setText(Double.toString((a/100)*presentage));

    }   
    public void clearAllTableData(){
                
        ratingName.setSelectedItem("");
        btnRefrsh.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
        while(true){
            if(ratingTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }
    public void tableClear(){
        DefaultTableModel model = (DefaultTableModel)tblData.getModel();
        while(model.getRowCount()>0){
            for(int p=0;p<model.getRowCount();p++){
                model.removeRow(p);
            }
        }
    }
    
    public void enableSelector(){
        comboSelected.setEnabled(true);
        comboLoanType.setEnabled(true);
        txtMonths.setEnabled(true);
        txtTerms.setEnabled(true);
        sliderInterestOne.setEnabled(true);
        sliderInterestTwo.setEnabled(true);
    }
    public void disableSelector(){
        comboSelected.setEnabled(false);
        comboLoanType.setEnabled(false);
        txtMonths.setEnabled(false);
        txtTerms.setEnabled(false);
        sliderInterestOne.setEnabled(false);
        sliderInterestTwo.setEnabled(false);
    }
    public void colors(double p,double set){
        if(p<=40){
            lblSetPresent.setForeground(Color.GREEN);
        }else if(p<=50){
            lblSetPresent.setForeground(Color.PINK);
        }
        else if(p<=60){
            lblSetPresent.setForeground(Color.ORANGE);           
        }
        else if(p>60){
            lblSetPresent.setForeground(Color.RED);          
        }
        else{
            lblSetPresent.setForeground(Color.GREEN);          
        }
        
        
        if(set <=25){
            lblSet.setForeground(Color.RED);
        }else if(set <=40){
            lblSet.setForeground(Color.ORANGE);            
        }
        else if(set <=55){
            lblSet.setForeground(Color.PINK);            
        }
        else if(set <=100){
            lblSet.setForeground(Color.GREEN);            
        }
        else{
            lblSet.setForeground(Color.RED);            
        }
    }
    public void tableLoaderOne(){
        LoanCalculation lon=new LoanCalculation();    
        if(checked == 2){
                lon.equalInstalment(Double.parseDouble(txtLoanAmount.getText()),Double.parseDouble(lblSet.getText()),Integer.parseInt(txtTerms.getText()));
                tableClear();
                //double getMP[]=lon.monthlyPayment;
                //double getIV[]=lon.interestValue;
                int a=1;
                while(a<=lon.monthlyPayment.size()){
                    DefaultTableModel model = (DefaultTableModel)tblData.getModel();
                    model.addRow(new Object[]{a,lon.interestValue.get(a-1),lon.monthlyPayment.get(a-1)});
                    a++;
                }
                txtTotalInterest.setText(Double.toString(lon.totalInterest));
                txtTotalMonthlyPayment.setText(Double.toString(lon.totalMonthlyPayment));
                txtTotalProfit.setText(Double.toString(lon.totalMonthlyPayment-Double.parseDouble(txtLoanAmount.getText())));
            }
    }
    public void tableLoaderTwo(){
        LoanCalculation lon=new LoanCalculation();  
            if(checked == 2){
                lon.equalInstalmentOriginalValues(Double.parseDouble(txtLoanAmount.getText()),Double.parseDouble(lblSet.getText()),Integer.parseInt(txtTerms.getText()));
                tableClear();
               // double getMP[]=lon.monthlyPayment;
               // double getIV[]=lon.interestValue;
                int a=1;
                while(a<=lon.monthlyPayment.size()){
                    DefaultTableModel model = (DefaultTableModel)tblData.getModel();
                    model.addRow(new Object[]{a,lon.interestValue.get(a-1),lon.monthlyPayment.get(a-1)});
                    a++;
                }
                txtTotalInterest.setText(Double.toString(lon.totalInterest));
                txtTotalMonthlyPayment.setText(Double.toString(lon.totalMonthlyPayment));
                txtTotalProfit.setText(Double.toString(lon.totalMonthlyPayment-Double.parseDouble(txtLoanAmount.getText())));
            }
    }
    public void rateCalculation(){
        disableSelector();
        getValue=Double.parseDouble(txtCompanyShares.getText());
        getLoanValue=Double.parseDouble(txtLoanAmount.getText());
        calculateShares=(getValue/100)*presentage;
        txtRateMoney.setText(Double.toString(calculateShares));
        String calculation = Double.toString(calculateShares);
        if (calculateShares<getLoanValue){
            presentage=Double.parseDouble(JOptionPane.showInputDialog(null,"Please get a Loan amount less than or eual "+presentage+"%( "+calculation+" )in Shares.\nDo you want change Interest Rate ?\nEnter the new Rate."));
            lblSetPresent.setText(Double.toString(presentage));
            calculateShares=(getValue/100)*presentage;
            txtRateMoney.setText(Double.toString(calculateShares));
            if(calculateShares>getLoanValue){
                colors(calculateShares,0d);
                enableSelector();
            }else{
                disableSelector();
            }
            if(calculateShares<getLoanValue && getLoanValue>getValue){
                enableSelector();
                colors(getLoanValue,0d);
                txtLoanAmount.setText(Double.toString(calculateShares));
            }         
        }else{
            if(calculateShares>=getLoanValue){
                //txtLoanAmount.setText(Double.toString(getLoanValue));
                colors(getLoanValue,0d);
                enableSelector();
            }else{
                disableSelector();
            }

        }
    }
    public double maxValue(double one,double two,double thee){
        double max=one;
        double twoVal=two;
        double threeVal=thee;
        
        if(max>twoVal && max>threeVal ){
            max=max;
            TypeRate="Equated Profit";
        }else if(twoVal>max && twoVal>threeVal){
            max=two;
            TypeRate="Equated Normal Profit";
        }else if(threeVal>max && threeVal>twoVal){
            max=threeVal;
            TypeRate="Equal Profit";
        }else{
            max=0;
            TypeRate="";
        }
        return max;
    }


    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ratingFrame = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ratingTable = new javax.swing.JTable();
        jLabel74 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        btnClearSelect = new javax.swing.JButton();
        clearAll = new javax.swing.JButton();
        btnDefaultValues = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        btnUpdateAndSave = new javax.swing.JButton();
        btnRefrsh = new javax.swing.JButton();
        btnRatingAddNewLine = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ratingName = new javax.swing.JComboBox<>();
        btnAllDataRemoveTable = new javax.swing.JButton();
        btnOneColomRemove = new javax.swing.JButton();
        btnRatingAddNewTable = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        lblDisplay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnChart = new javax.swing.JButton();
        btnDisplay = new javax.swing.JButton();
        btnChart1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotalProfit = new javax.swing.JTextField();
        txtTotalInterest = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotalMonthlyPayment = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        ratingTableLoan = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblSet = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSetPresent = new javax.swing.JLabel();
        rdOriginalValue = new javax.swing.JRadioButton();
        rdPrimeValue = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        txtgetProfit = new javax.swing.JTextField();
        txtTotalAmount = new javax.swing.JTextField();
        txtMonthlyPayment = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sliderInterestTwo = new javax.swing.JSlider();
        sliderInterestOne = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTerms = new javax.swing.JTextField();
        txtMonths = new javax.swing.JTextField();
        lbltermsType = new javax.swing.JLabel();
        comboSelected = new javax.swing.JComboBox<>();
        comboLoanType = new javax.swing.JComboBox<>();
        txtLoanAmount = new javax.swing.JTextField();
        txtRateMoney = new javax.swing.JTextField();
        txtCompanyShares = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        ratingName1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        lblDisplayDefaultRating = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        week2Steps = new javax.swing.JTextField();
        week2Terms = new javax.swing.JTextField();
        week1Terms = new javax.swing.JTextField();
        week1Steps = new javax.swing.JTextField();
        dayTerms = new javax.swing.JTextField();
        daySteps = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        monthTerms = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        yearTerms = new javax.swing.JTextField();
        yearSteps = new javax.swing.JTextField();
        monthSteps = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(925, 400));
        setPreferredSize(new java.awt.Dimension(925, 500));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(800, 450));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 450));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(null);

        ratingFrame.setBackground(new java.awt.Color(255, 255, 255));
        ratingFrame.setMinimumSize(new java.awt.Dimension(900, 850));
        ratingFrame.setPreferredSize(new java.awt.Dimension(900, 850));

        ratingTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ratingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Day By Day (Rs.)", "D Rate (%)", "Week 1 (Rs.)", "W1 Rate (%)", "Week 2 (Rs.)", "W2 Rate (%)", "Month (Rs.)", "M Rate (%)", "Year (Rs.)", "Y Rate (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ratingTable.setRowHeight(20);
        ratingTable.setShowGrid(false);
        ratingTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                ratingTableAncestorRemoved(evt);
            }
        });
        ratingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratingTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(ratingTable);

        jLabel74.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel74.setText("Rating Table");
        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });

        btnClearSelect.setText(" Clear Selected Data ");
        btnClearSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSelectActionPerformed(evt);
            }
        });

        clearAll.setText("Clear all Table Data");
        clearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllActionPerformed(evt);
            }
        });

        btnDefaultValues.setText("Default Valuse");
        btnDefaultValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefaultValuesActionPerformed(evt);
            }
        });

        btnUpdateAndSave.setText("Data Edit & Update");
        btnUpdateAndSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateAndSaveActionPerformed(evt);
            }
        });

        btnRefrsh.setText("Refresh");
        btnRefrsh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrshActionPerformed(evt);
            }
        });

        btnRatingAddNewLine.setText("Add New Line ");
        btnRatingAddNewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRatingAddNewLineActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Rating Type :");

        ratingName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ratingNameActionPerformed(evt);
            }
        });

        btnAllDataRemoveTable.setText("Delete Save Table");
        btnAllDataRemoveTable.setMaximumSize(new java.awt.Dimension(125, 23));
        btnAllDataRemoveTable.setMinimumSize(new java.awt.Dimension(125, 23));
        btnAllDataRemoveTable.setPreferredSize(new java.awt.Dimension(125, 23));
        btnAllDataRemoveTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllDataRemoveTableActionPerformed(evt);
            }
        });

        btnOneColomRemove.setText("Delete Selected Row");
        btnOneColomRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneColomRemoveActionPerformed(evt);
            }
        });

        btnRatingAddNewTable.setText("Add New Table");
        btnRatingAddNewTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRatingAddNewTableActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton4.setText("Company Rate");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Set Company Rate :");

        lblDisplay.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDisplay.setForeground(new java.awt.Color(51, 255, 51));
        lblDisplay.setText("Default");

        javax.swing.GroupLayout ratingFrameLayout = new javax.swing.GroupLayout(ratingFrame);
        ratingFrame.setLayout(ratingFrameLayout);
        ratingFrameLayout.setHorizontalGroup(
            ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingFrameLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ratingFrameLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratingName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ratingFrameLayout.createSequentialGroup()
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRatingAddNewLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClearSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRatingAddNewTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRefrsh, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDefaultValues, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOneColomRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdateAndSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAllDataRemoveTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ratingFrameLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(700, 700, 700))
                    .addComponent(jLabel74)
                    .addComponent(jScrollPane6))
                .addGap(44, 44, 44))
        );
        ratingFrameLayout.setVerticalGroup(
            ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingFrameLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel74)
                .addGap(6, 6, 6)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblDisplay))
                .addGap(10, 10, 10)
                .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ratingName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ratingFrameLayout.createSequentialGroup()
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdateAndSave)
                            .addComponent(btnSave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAllDataRemoveTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOneColomRemove)))
                    .addGroup(ratingFrameLayout.createSequentialGroup()
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRatingAddNewTable)
                            .addComponent(btnRatingAddNewLine)
                            .addComponent(btnRefrsh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ratingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClearSelect)
                            .addComponent(clearAll)
                            .addComponent(btnDefaultValues))))
                .addGap(1101, 1101, 1101)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(ratingFrame);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("     Company Rate     ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(925, 450));
        jPanel3.setPreferredSize(new java.awt.Dimension(925, 450));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(900, 900));

        jButton1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton1.setText("C Table");
        jButton1.setMaximumSize(new java.awt.Dimension(93, 24));
        jButton1.setMinimumSize(new java.awt.Dimension(93, 24));
        jButton1.setPreferredSize(new java.awt.Dimension(93, 24));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnChart.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnChart.setText("Auto Cal");
        btnChart.setMaximumSize(new java.awt.Dimension(93, 24));
        btnChart.setMinimumSize(new java.awt.Dimension(93, 24));
        btnChart.setPreferredSize(new java.awt.Dimension(93, 24));
        btnChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChartActionPerformed(evt);
            }
        });

        btnDisplay.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnDisplay.setText("Display");
        btnDisplay.setAlignmentX(0.5F);
        btnDisplay.setMaximumSize(new java.awt.Dimension(93, 24));
        btnDisplay.setMinimumSize(new java.awt.Dimension(93, 24));
        btnDisplay.setPreferredSize(new java.awt.Dimension(93, 24));
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });

        btnChart1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnChart1.setText("Chart");
        btnChart1.setMaximumSize(new java.awt.Dimension(93, 24));
        btnChart1.setMinimumSize(new java.awt.Dimension(93, 24));
        btnChart1.setPreferredSize(new java.awt.Dimension(93, 24));
        btnChart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChart1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Total Interest :");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Total Profit :");

        txtTotalProfit.setEditable(false);
        txtTotalProfit.setMinimumSize(new java.awt.Dimension(7, 24));
        txtTotalProfit.setPreferredSize(new java.awt.Dimension(7, 24));

        txtTotalInterest.setEditable(false);
        txtTotalInterest.setMinimumSize(new java.awt.Dimension(7, 24));
        txtTotalInterest.setPreferredSize(new java.awt.Dimension(7, 24));
        txtTotalInterest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalInterestActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Total Payment :");

        txtTotalMonthlyPayment.setEditable(false);
        txtTotalMonthlyPayment.setMinimumSize(new java.awt.Dimension(7, 24));
        txtTotalMonthlyPayment.setPreferredSize(new java.awt.Dimension(7, 24));

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Terms", "Interest", "Payment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblData.setFocusable(false);
        tblData.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblData.setRowHeight(25);
        tblData.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblData.setShowGrid(false);
        tblData.setShowHorizontalLines(true);
        tblData.getTableHeader().setReorderingAllowed(false);
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

        jScrollPane3.setBorder(null);

        ratingTableLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day By Day (Rs.)", "D Rate (%)", "Week 1 (Rs.)", "W1 Rate (%)", "Week 2 (Rs.)", "W2 Rate (%)", "Month (Rs.)", "M Rate (%)", "Year (Rs.)", "Y Rate (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ratingTableLoan.setRowHeight(24);
        ratingTableLoan.setShowGrid(false);
        jScrollPane3.setViewportView(ratingTableLoan);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Interest Rate :");

        lblSet.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lblSetKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("MMI Rate :");

        lblSetPresent.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSetPresent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSetPresent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetPresentMouseClicked(evt);
            }
        });

        rdOriginalValue.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdOriginalValue);
        rdOriginalValue.setText("Original values");
        rdOriginalValue.setBorder(null);
        rdOriginalValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdOriginalValueActionPerformed(evt);
            }
        });

        rdPrimeValue.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdPrimeValue);
        rdPrimeValue.setText("Prime values");
        rdPrimeValue.setBorder(null);
        rdPrimeValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrimeValueActionPerformed(evt);
            }
        });

        jButton2.setText("Clear All");
        jButton2.setMaximumSize(new java.awt.Dimension(93, 24));
        jButton2.setMinimumSize(new java.awt.Dimension(93, 24));
        jButton2.setPreferredSize(new java.awt.Dimension(93, 24));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtgetProfit.setEditable(false);
        txtgetProfit.setToolTipText("Monthly Payment");
        txtgetProfit.setMinimumSize(new java.awt.Dimension(7, 24));
        txtgetProfit.setName(""); // NOI18N
        txtgetProfit.setPreferredSize(new java.awt.Dimension(7, 24));
        txtgetProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgetProfitActionPerformed(evt);
            }
        });

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setToolTipText("Monthly Payment");
        txtTotalAmount.setMinimumSize(new java.awt.Dimension(7, 24));
        txtTotalAmount.setPreferredSize(new java.awt.Dimension(7, 24));
        txtTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAmountActionPerformed(evt);
            }
        });

        txtMonthlyPayment.setEditable(false);
        txtMonthlyPayment.setToolTipText("Monthly Payment");
        txtMonthlyPayment.setMinimumSize(new java.awt.Dimension(7, 24));
        txtMonthlyPayment.setPreferredSize(new java.awt.Dimension(7, 24));
        txtMonthlyPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthlyPaymentActionPerformed(evt);
            }
        });
        txtMonthlyPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMonthlyPaymentKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Total Profit :");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Total Payment :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Monthly Payment :");

        sliderInterestTwo.setBackground(new java.awt.Color(255, 255, 255));
        sliderInterestTwo.setForeground(new java.awt.Color(0, 0, 0));
        sliderInterestTwo.setMajorTickSpacing(11);
        sliderInterestTwo.setMaximum(99);
        sliderInterestTwo.setMinorTickSpacing(1);
        sliderInterestTwo.setPaintLabels(true);
        sliderInterestTwo.setPaintTicks(true);
        sliderInterestTwo.setValue(0);
        sliderInterestTwo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sliderInterestTwo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderInterestTwoAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        sliderInterestTwo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderInterestTwoStateChanged(evt);
            }
        });

        sliderInterestOne.setBackground(new java.awt.Color(255, 255, 255));
        sliderInterestOne.setForeground(new java.awt.Color(0, 0, 0));
        sliderInterestOne.setMajorTickSpacing(11);
        sliderInterestOne.setMaximum(99);
        sliderInterestOne.setMinorTickSpacing(1);
        sliderInterestOne.setPaintLabels(true);
        sliderInterestOne.setPaintTicks(true);
        sliderInterestOne.setValue(0);
        sliderInterestOne.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sliderInterestOne.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderInterestOneAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        sliderInterestOne.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderInterestOneStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Interest Rate :");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Terms :");

        txtTerms.setText("1");
        txtTerms.setMinimumSize(new java.awt.Dimension(7, 24));
        txtTerms.setPreferredSize(new java.awt.Dimension(7, 24));
        txtTerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTermsActionPerformed(evt);
            }
        });

        txtMonths.setText("1");
        txtMonths.setToolTipText("Months");
        txtMonths.setMinimumSize(new java.awt.Dimension(7, 24));
        txtMonths.setPreferredSize(new java.awt.Dimension(7, 24));
        txtMonths.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthsActionPerformed(evt);
            }
        });
        txtMonths.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMonthsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMonthsKeyReleased(evt);
            }
        });

        lbltermsType.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbltermsType.setText("Days :");

        comboSelected.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Equated Rate", "Equated Rate Norma", "Equal Rate" }));
        comboSelected.setMinimumSize(new java.awt.Dimension(126, 24));
        comboSelected.setPreferredSize(new java.awt.Dimension(126, 24));
        comboSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSelectedActionPerformed(evt);
            }
        });

        comboLoanType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day by Day", "Week 1", "Week 2", "Month", "Years" }));
        comboLoanType.setMinimumSize(new java.awt.Dimension(126, 24));
        comboLoanType.setPreferredSize(new java.awt.Dimension(126, 24));
        comboLoanType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoanTypeActionPerformed(evt);
            }
        });

        txtLoanAmount.setToolTipText("Loan Amount");
        txtLoanAmount.setMinimumSize(new java.awt.Dimension(7, 24));
        txtLoanAmount.setPreferredSize(new java.awt.Dimension(7, 24));
        txtLoanAmount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLoanAmountMouseClicked(evt);
            }
        });
        txtLoanAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoanAmountActionPerformed(evt);
            }
        });
        txtLoanAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoanAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoanAmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLoanAmountKeyTyped(evt);
            }
        });

        txtRateMoney.setEditable(false);
        txtRateMoney.setToolTipText("Loan Amount");
        txtRateMoney.setMinimumSize(new java.awt.Dimension(7, 24));
        txtRateMoney.setPreferredSize(new java.awt.Dimension(7, 24));
        txtRateMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRateMoneyActionPerformed(evt);
            }
        });

        txtCompanyShares.setToolTipText("Loan Amount");
        txtCompanyShares.setMinimumSize(new java.awt.Dimension(7, 24));
        txtCompanyShares.setPreferredSize(new java.awt.Dimension(7, 24));
        txtCompanyShares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCompanySharesActionPerformed(evt);
            }
        });
        txtCompanyShares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanySharesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompanySharesKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Company Total Shares :");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Maximum Ishu Money :");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Loan Amount :");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Loan Type :");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Interest Type :");

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setText("Customer’s Details");

        ratingName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ratingName1ActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Default Rating type :");

        lblDisplayDefaultRating.setBackground(new java.awt.Color(255, 255, 255));
        lblDisplayDefaultRating.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDisplayDefaultRating.setForeground(new java.awt.Color(255, 0, 0));
        lblDisplayDefaultRating.setText("Loan");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Amount steps & Term Setings"));

        jButton3.setText("Default Vaues");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        week2Steps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                week2StepsActionPerformed(evt);
            }
        });
        week2Steps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                week2StepsKeyPressed(evt);
            }
        });

        week2Terms.setEnabled(false);

        week1Terms.setEnabled(false);

        week1Steps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                week1StepsKeyPressed(evt);
            }
        });

        dayTerms.setEnabled(false);
        dayTerms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dayTermsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dayTermsKeyTyped(evt);
            }
        });

        daySteps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dayStepsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dayStepsKeyTyped(evt);
            }
        });

        jLabel24.setText("Amount Steps");

        monthTerms.setEnabled(false);

        jLabel20.setText("Month Terms");

        jLabel25.setText("Year Terms");

        yearTerms.setEnabled(false);

        yearSteps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearStepsKeyPressed(evt);
            }
        });

        monthSteps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthStepsKeyPressed(evt);
            }
        });

        jLabel22.setText("Amount Steps");

        jLabel19.setText("Day Terms");

        jLabel23.setText("Week One Terms");

        jLabel21.setText("Week Two Terms");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel23)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dayTerms)
                        .addComponent(week1Terms, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(week2Terms, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(week2Steps, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(241, 249, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(week1Steps)
                                .addComponent(daySteps, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yearTerms)
                            .addComponent(monthTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monthSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel23)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(monthTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(monthSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(yearTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yearSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))
                                .addGap(6, 6, 6)))
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dayTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(daySteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(week1Terms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(week1Steps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(week2Terms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(week2Steps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRateMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanyShares, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(128, 128, 128)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdPrimeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(rdOriginalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSetPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel16)
                            .addComponent(jLabel4)
                            .addComponent(lbltermsType, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboLoanType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonths, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderInterestOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderInterestTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonthlyPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgetProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnChart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ratingName1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalMonthlyPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDisplayDefaultRating, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotalInterest, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotalProfit, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))))))
                    .addComponent(jScrollPane3))
                .addGap(20, 20, 20))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                        .addGap(25, 25, 25))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(lblSet, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSetPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdPrimeValue)
                                    .addComponent(rdOriginalValue)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel8))
                                    .addComponent(txtCompanyShares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtRateMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel11)))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel2)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel16)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel4)
                                        .addGap(16, 16, 16)
                                        .addComponent(lbltermsType)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel6)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel3)
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel5)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel14)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(comboLoanType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(comboSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(sliderInterestOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(sliderInterestTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtMonthlyPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtgetProfit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnChart, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel9)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalMonthlyPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalInterest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalProfit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lblDisplayDefaultRating)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(ratingName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jScrollPane4.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("     Loan Calculator     ", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdOriginalValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdOriginalValueActionPerformed
        LoanCalculation lon=new LoanCalculation();
        if(tblData.getRowCount()>=1){
            tableLoaderTwo();
        }
    }//GEN-LAST:event_rdOriginalValueActionPerformed

    private void rdPrimeValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrimeValueActionPerformed
        LoanCalculation lon=new LoanCalculation();
        if(tblData.getRowCount()>=1){
            tableLoaderOne();
        }
    }//GEN-LAST:event_rdPrimeValueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tableClear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChartActionPerformed
        if(chekStepseValid()){
            autoCal();
        }else{
            JOptionPane.showMessageDialog(null,"Steps and Terms Setting has some problem..");
        }
        
    }//GEN-LAST:event_btnChartActionPerformed

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        LoanCalculation lon=new LoanCalculation();
        if(rdPrimeValue.isSelected()==true){
            tableLoaderOne();
        }else if(rdOriginalValue.isSelected()==true){
            tableLoaderTwo();
        }
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnChart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChart1ActionPerformed
        TableModel model = tblData.getModel();
        int rowCount=tblData.getRowCount();
        DefaultCategoryDataset barChart=new DefaultCategoryDataset();
        int b=0;
        while(b<rowCount){
            barChart.setValue(Double.parseDouble(model.getValueAt(b, 2).toString()),b+"","Terms");
            b++;
        }
        JFreeChart Chart = ChartFactory.createBarChart("Equal Report","Terms","Payment",barChart, PlotOrientation.VERTICAL,false,true,true);
        CategoryPlot chart = Chart.getCategoryPlot();
        chart.setRangeGridlinePaint(Color.BLUE);
        chart.setBackgroundPaint(new Color(153,153,153));

        ChartFrame bar= new ChartFrame("Payment Chart",Chart);
        bar.setVisible(true);
        bar.setSize(550,600);
    }//GEN-LAST:event_btnChart1ActionPerformed

    private void txtTotalInterestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalInterestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalInterestActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked

    }//GEN-LAST:event_tblDataMouseClicked

    private void lblSetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblSetKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSetKeyTyped

    private void lblSetPresentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetPresentMouseClicked
        ValidityCheck val=new ValidityCheck();
        String getPres="";
        getPres=JOptionPane.showInputDialog(null,"Do you want changeing a Rate ?\nCurrent Rate"+presentage+"%( "+txtRateMoney.getText()+" )current Ishu Money.\nEnter the new Rate.");
        if(getPres!=null){
            while(true){
                boolean vx=val.checkPresentage(getPres);
                if(vx){
                    presentage=Double.parseDouble(getPres);
                    if(presentage<=100 && presentage>=1){
                        txtLoanAmount.setText("");
                        lblSetPresent.setText(Double.toString(presentage));
                        double a=Double.parseDouble(txtCompanyShares.getText());
                        txtLoanAmount.setText(Double.toString((a/100)*presentage));
                        txtRateMoney.setText(Double.toString((a/100)*presentage));
                        rateCalculation();
                        break;
                    }else{
                        txtLoanAmount.setText("");
                        getPres=JOptionPane.showInputDialog(null,"\tInvalid rate\n\nDo you want changeing a Rate ?\nEnter the new Rate.");
                    }
                }else{
                    getPres=JOptionPane.showInputDialog(null,"\tInvalid rate\n\nDo you want changeing a Rate ?\nEnter the new Rate.");
                }
            }
        }else{
        }
    }//GEN-LAST:event_lblSetPresentMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtgetProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgetProfitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgetProfitActionPerformed

    private void txtTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAmountActionPerformed

    private void txtMonthlyPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthlyPaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMonthlyPaymentActionPerformed

    private void txtMonthlyPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonthlyPaymentKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMonthlyPaymentKeyReleased

    private void sliderInterestTwoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderInterestTwoAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_sliderInterestTwoAncestorMoved

    private void sliderInterestTwoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderInterestTwoStateChanged
        int a = sliderInterestOne.getValue();
        int b = sliderInterestTwo.getValue();
        String z="";
        if(a == 0 ){
            if(b<=9){
                z=Integer.toString(a) + "." + "0" + Integer.toString(b);
                lblSet.setText(z);
            }else{
                z=Integer.toString(a) + "." + Integer.toString(b);
                lblSet.setText(z);
            }
        }else{
            if(b<=9){
                z=Integer.toString(a) + "." + "0" + Integer.toString(b);
                lblSet.setText(z);
            }else{
                z=Integer.toString(a) + "." + Integer.toString(b);
                lblSet.setText(z);
            }
        }
        LoanCalculation lon=new LoanCalculation();
        if(checked == 0){
            txtMonthlyPayment.setText(Double.toString(lon.equatedInstalment(Double.parseDouble(txtLoanAmount.getText()),Double.parseDouble(lblSet.getText()),Integer.parseInt(txtTerms.getText()))));
            double sum=Integer.parseInt(txtTerms.getText())*Double.parseDouble(txtMonthlyPayment.getText());
            txtTotalAmount.setText(Double.toString(sum));
        }
        else if(checked == 1){
            txtMonthlyPayment.setText(Double.toString(lon.equatedInstalmentNormal(Double.parseDouble(txtLoanAmount.getText()),Double.parseDouble(lblSet.getText()),Integer.parseInt(txtTerms.getText()))));
            double sum=Integer.parseInt(txtTerms.getText())*Double.parseDouble(txtMonthlyPayment.getText());
            txtTotalAmount.setText(Double.toString(sum));
        }
        else{
            txtMonthlyPayment.setText("");
            txtTotalAmount.setText("");
            txtgetProfit.setText("");
        }
    }//GEN-LAST:event_sliderInterestTwoStateChanged

    private void sliderInterestOneAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderInterestOneAncestorMoved

    }//GEN-LAST:event_sliderInterestOneAncestorMoved

    private void sliderInterestOneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderInterestOneStateChanged
        int a = sliderInterestTwo.getValue();
        int b = sliderInterestOne.getValue();
        String z="";
        if(a == 0 ){
            z=Integer.toString(b) + "." + Integer.toString(a);
            lblSet.setText(z);
            colors(0d,b);
        }else{
            z=Integer.toString(b) + "." + Integer.toString(a);
            lblSet.setText(z);
            colors(0d,b);
        }
        LoanCalculation lon=new LoanCalculation();
        if(checked == 0){
            txtMonthlyPayment.setText(Double.toString(lon.equatedInstalment(Double.parseDouble(txtLoanAmount.getText()),Double.parseDouble(lblSet.getText()),Integer.parseInt(txtTerms.getText()))));
            double sum=Integer.parseInt(txtTerms.getText())*Double.parseDouble(txtMonthlyPayment.getText());
            txtTotalAmount.setText(Double.toString(sum));
            txtgetProfit.setText(Double.toString(sum-Double.parseDouble(txtLoanAmount.getText())));
        }
        else if(checked == 1){
            txtMonthlyPayment.setText(Double.toString(lon.equatedInstalmentNormal(Double.parseDouble(txtLoanAmount.getText()),Double.parseDouble(lblSet.getText()),Integer.parseInt(txtTerms.getText()))));
            double sum=Integer.parseInt(txtTerms.getText())*Double.parseDouble(txtMonthlyPayment.getText());
            txtTotalAmount.setText(Double.toString(sum));
            txtgetProfit.setText(Double.toString(sum-Double.parseDouble(txtLoanAmount.getText())));
        }
        else{
            txtMonthlyPayment.setText("");
            txtTotalAmount.setText("");
            txtgetProfit.setText("");
        }
    }//GEN-LAST:event_sliderInterestOneStateChanged

    private void txtTermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTermsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTermsActionPerformed

    private void txtMonthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthsActionPerformed

    }//GEN-LAST:event_txtMonthsActionPerformed

    private void txtMonthsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonthsKeyReleased

        int a=comboLoanType.getSelectedIndex();
        if (a==0){
            int b=Integer.parseInt(txtMonths.getText());
            txtTerms.setText(Integer.toString(b*1));
        }else if(a==1){
            int b=Integer.parseInt(txtMonths.getText());
            txtTerms.setText(Integer.toString(b*1));
        }
        else if(a==2){
            int b=Integer.parseInt(txtMonths.getText());
            txtTerms.setText(Integer.toString(b*1));
        }
        else if(a==3){
            int b=Integer.parseInt(txtMonths.getText());
            txtTerms.setText(Integer.toString(b*12));
        }else{
            int b=Integer.parseInt(txtMonths.getText());
            txtTerms.setText(Integer.toString(b));
        }
    }//GEN-LAST:event_txtMonthsKeyReleased

    private void comboSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSelectedActionPerformed
        checked=comboSelected.getSelectedIndex();
        if(checked==2 | checked != 0){
            txtMonthlyPayment.setText("");
            txtTotalAmount.setText("");
            txtgetProfit.setText("");
        }
    }//GEN-LAST:event_comboSelectedActionPerformed

    private void comboLoanTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoanTypeActionPerformed
        comboGetChecked=comboLoanType.getSelectedIndex();
        //Week 1, Week 2, Month, Years
        //W, M, Y
        if(comboGetChecked == 0){
            txtMonths.setText("1");
            txtTerms.setText("1");
            lbltermsType.setText("Days :");
            comboSelected.setSelectedIndex(3);
        }else if(comboGetChecked == 1){
            txtMonths.setText("3");
            txtTerms.setText("12");
            lbltermsType.setText("Months :");
            comboSelected.setSelectedIndex(1);
        }else if(comboGetChecked == 2){
            txtMonths.setText("6");
            txtTerms.setText("24");
            lbltermsType.setText("Months :");
            comboSelected.setSelectedIndex(0);
        }else if(comboGetChecked == 3){
            txtMonths.setText("1");
            txtTerms.setText("12");
            lbltermsType.setText("Years :");
            comboSelected.setSelectedIndex(2);
        }else if(comboGetChecked == 4){
            txtMonths.setText("5");
            txtTerms.setText("60");
            lbltermsType.setText("Years :");
            comboSelected.setSelectedIndex(2);
        }else{
            txtMonths.setText("");
            txtTerms.setText("");
            lbltermsType.setText("Day/Week/Month or Year :");
            comboSelected.setSelectedIndex(0);
        }
    }//GEN-LAST:event_comboLoanTypeActionPerformed

    private void txtLoanAmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLoanAmountMouseClicked
        if(txtLoanAmount.getText().equals(txtRateMoney.getText())){
            txtLoanAmount.setText("");
        }
    }//GEN-LAST:event_txtLoanAmountMouseClicked

    private void txtLoanAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoanAmountActionPerformed

    }//GEN-LAST:event_txtLoanAmountActionPerformed

    private void txtLoanAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoanAmountKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtLoanAmount.setEditable(true);            
        }else{
            txtLoanAmount.setEditable(false);
        }
    }//GEN-LAST:event_txtLoanAmountKeyPressed

    private void txtLoanAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoanAmountKeyReleased
        rateCalculation();
    }//GEN-LAST:event_txtLoanAmountKeyReleased

    private void txtLoanAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoanAmountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoanAmountKeyTyped

    private void txtRateMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRateMoneyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRateMoneyActionPerformed

    private void txtCompanySharesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCompanySharesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanySharesActionPerformed

    private void txtCompanySharesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanySharesKeyReleased
        double a=Double.parseDouble(txtCompanyShares.getText());
        txtLoanAmount.setText(Double.toString((a/100)*presentage));
        txtRateMoney.setText(Double.toString((a/100)*presentage));
    }//GEN-LAST:event_txtCompanySharesKeyReleased

    private void ratingName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ratingName1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) ratingTableLoan.getModel();
        while(true){
            if(ratingTableLoan.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
        String getData=ratingName1.getItemAt(ratingName1.getSelectedIndex());
        tableRefresh(getData,1);
    }//GEN-LAST:event_ratingName1ActionPerformed

    private void week2StepsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_week2StepsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_week2StepsActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dayTerms.setText("1");daySteps.setText("5000");
        week1Terms.setText("12");week1Steps.setText("10000");
        week2Terms.setText("24");week2Steps.setText("10000");
        monthTerms.setText("12");monthSteps.setText("50000");
        yearTerms.setText("60");yearSteps.setText("100000");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dayTermsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayTermsKeyPressed
        /*if(Double.valueOf(dayTerms.getText())>=1){
            dayTerms.setEditable(true);
        }else{
            dayTerms.setText("");
            dayTerms.
        }*/
        


    }//GEN-LAST:event_dayTermsKeyPressed

    private void txtMonthsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonthsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtMonths.setEditable(true);            
        }else{
            txtMonths.setEditable(false);
        }
    }//GEN-LAST:event_txtMonthsKeyPressed

    private void txtCompanySharesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanySharesKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtCompanyShares.setEditable(true);            
        }else{
            txtCompanyShares.setEditable(false);
        }
    }//GEN-LAST:event_txtCompanySharesKeyPressed

    private void dayTermsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayTermsKeyTyped

    }//GEN-LAST:event_dayTermsKeyTyped

    private void dayStepsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayStepsKeyTyped

    }//GEN-LAST:event_dayStepsKeyTyped

    private void dayStepsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayStepsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            daySteps.setEditable(true);            
        }else{
            daySteps.setEditable(false);
        }
    }//GEN-LAST:event_dayStepsKeyPressed

    private void week1StepsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_week1StepsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            week1Steps.setEditable(true);            
        }else{
            week1Steps.setEditable(false);
        }
    }//GEN-LAST:event_week1StepsKeyPressed

    private void week2StepsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_week2StepsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            week2Steps.setEditable(true);            
        }else{
            week2Steps.setEditable(false);
        }
    }//GEN-LAST:event_week2StepsKeyPressed

    private void monthStepsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthStepsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            monthSteps.setEditable(true);            
        }else{
            monthSteps.setEditable(false);
        }
    }//GEN-LAST:event_monthStepsKeyPressed

    private void yearStepsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearStepsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            yearSteps.setEditable(true);            
        }else{
            yearSteps.setEditable(false);
        }
    }//GEN-LAST:event_yearStepsKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //set rating_key to defualt rating
        String getName=(String) ratingName.getSelectedItem();

        DBQuary.deleteRatingKey();//delete key data
        DBQuary.insertRatingKey(rate, getName);//insert key name
        DBQuary.getRatingKey(rate);//get key Data
        lblDisplay.setText(rate.getRating_Id_name());
        lblDisplayDefaultRating.setText(rate.getRating_Id_name());

        int index = ratingTableLoan.getRowCount();
        DefaultTableModel model=(DefaultTableModel) ratingTableLoan.getModel();
        for(int v=0;v<index;v++){
            model.removeRow(0);
        }
        LoanCalLoad();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
        String getName="";
        if(ratingName.getSelectedItem().equals("Default")){
            btnUpdateAndSave.setEnabled(false);
        }else{
            btnUpdateAndSave.setEnabled(true);
            if(ratingTable.getRowCount()>=1){
                while(true){
                    getName=JOptionPane.showInputDialog(null,"Please enter name to save Rating\nIf you want exit ?\n\tplease empty Text Box and press [ OK ]");
                    
                    if(getName.isEmpty()){
                        break;
                    }else{
                        int v=0;
                        while(true){
                            String getData=ratingName.getItemAt(v).toString();

                            if(getName==getData){
                                getName=JOptionPane.showInputDialog(null,"Please enter valid name to save Rating\nDon't enter duplicate value\nIf you want exit ?\n\tplease empty Text Box and press [ OK ]");
                                   if(getName.isEmpty()){
                                        break;
                                   }
                            }
                            if(v==ratingName.getItemCount()-1){
                                break;
                            }
                            v++;
                        }
                        if(!getName.isEmpty()){
                            tableDataInsert(getName);
                        }
                    }
                }

            }
            String getSelect=(String) ratingName.getSelectedItem();
            clearAllTableData();
            ratingName.setSelectedItem(getSelect);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRatingAddNewTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRatingAddNewTableActionPerformed
        btnSave.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel)ratingTable.getModel();
        clearAllTableData();
        int getRowsCount=0;
        while(true){
            String getRows=JOptionPane.showInputDialog(null,"How many rows Do you want ?\ndefault :5");
            if(getRows == null){
                ratingName.setSelectedItem("Default");
                break;
            }else{
                if(getRows.isEmpty()){
                    getRowsCount=5;
                    break;
                }else{
                    getRowsCount=Integer.valueOf(getRows);
                    break;
                }
            }
        }

        int x=0;
        while(x<getRowsCount){

            model.addRow(new Object[]{});
            x++;
        }

    }//GEN-LAST:event_btnRatingAddNewTableActionPerformed

    private void btnOneColomRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOneColomRemoveActionPerformed
        DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
        String getData=ratingName.getItemAt(ratingName.getSelectedIndex());
        int getSelectedRowIndex[]=ratingTable.getSelectedRows();
        if(getData.equals("Default")){
        }
        else{
            String id;
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                for(Integer vc:getSelectedRowIndex){
                    id=model.getValueAt(vc,0).toString();
                    String sql = "DELETE FROM micro_rating WHERE id='"+id+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                }
                // JOptionPane.showMessageDialog(null,"Data Delete Success..");
                conn.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }

            String getSelect=(String) ratingName.getSelectedItem();
            clearAllTableData();
            ratingName.setSelectedItem(getSelect);
        }

    }//GEN-LAST:event_btnOneColomRemoveActionPerformed

    private void btnAllDataRemoveTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllDataRemoveTableActionPerformed
        String getData=ratingName.getItemAt(ratingName.getSelectedIndex());
        if(getData.equals("Default")){
        }else{
            DBQuary.deleteRating(getData);//call to delete ratings
            ratingName.removeAllItems();
            ratingName.addItem("");
            clearAllTableData();
            secondLoadDataComboBox();

        }

    }//GEN-LAST:event_btnAllDataRemoveTableActionPerformed

    private void ratingNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ratingNameActionPerformed
        DefaultTableModel model = (DefaultTableModel) ratingTable.getModel();
        while(true){
            if(ratingTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
        String getData=ratingName.getItemAt(ratingName.getSelectedIndex());
        tableRefresh(getData,0);
    }//GEN-LAST:event_ratingNameActionPerformed

    private void btnRatingAddNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRatingAddNewLineActionPerformed
        btnSave.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel)ratingTable.getModel();
        model.addRow(new Object[]{});
        if(ratingName.getSelectedItem().equals("Default")){
            btnUpdateAndSave.setEnabled(false);
        }else{
            btnUpdateAndSave.setEnabled(true);
        }

    }//GEN-LAST:event_btnRatingAddNewLineActionPerformed

    private void btnRefrshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrshActionPerformed
        String getSelect=(String) ratingName.getSelectedItem();
        clearAllTableData();
        ratingName.setSelectedItem(getSelect);
    }//GEN-LAST:event_btnRefrshActionPerformed

    private void btnUpdateAndSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAndSaveActionPerformed
        if(ratingName.getSelectedItem().equals("Default")){
            btnUpdateAndSave.setEnabled(false);
        }else{

            String getSelect=(String) ratingName.getSelectedItem();
            tableDataUpdate(getSelect);
            clearAllTableData();
            ratingName.setSelectedItem(getSelect);
        }
    }//GEN-LAST:event_btnUpdateAndSaveActionPerformed

    private void btnDefaultValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefaultValuesActionPerformed
        DefaultTableModel model=(DefaultTableModel) ratingTable.getModel();
        String def="Default";
        ratingName.setSelectedItem("Default");
        if(ratingTable.getRowCount()==0){

            tableRefresh(def,0);
        }else{
            while(true){
                if(ratingTable.getRowCount()==0){
                    break;
                }
                model.removeRow(0);
            }
            tableRefresh(def,0);
        }

    }//GEN-LAST:event_btnDefaultValuesActionPerformed

    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllActionPerformed

        clearAllTableData();

    }//GEN-LAST:event_clearAllActionPerformed

    private void btnClearSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSelectActionPerformed
        int [] index = ratingTable.getSelectedRows();
        DefaultTableModel model=(DefaultTableModel) ratingTable.getModel();
        for(Integer b:index){
            model.removeRow(b);
        }
    }//GEN-LAST:event_btnClearSelectActionPerformed

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
        JFrame f=new JFrame();
        f.add(ratingFrame);
        f.setSize(900, 900);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setTitle("Rating Window");
        f.show();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jLabel74MouseClicked

    private void ratingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingTableMouseClicked
        btnUpdateAndSave.setEnabled(true);
    }//GEN-LAST:event_ratingTableMouseClicked

    private void ratingTableAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ratingTableAncestorRemoved

    }//GEN-LAST:event_ratingTableAncestorRemoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllDataRemoveTable;
    private javax.swing.JButton btnChart;
    private javax.swing.JButton btnChart1;
    private javax.swing.JButton btnClearSelect;
    private javax.swing.JButton btnDefaultValues;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnOneColomRemove;
    private javax.swing.JButton btnRatingAddNewLine;
    private javax.swing.JButton btnRatingAddNewTable;
    private javax.swing.JButton btnRefrsh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdateAndSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearAll;
    private javax.swing.JComboBox<String> comboLoanType;
    private javax.swing.JComboBox<String> comboSelected;
    private javax.swing.JTextField daySteps;
    private javax.swing.JTextField dayTerms;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JLabel lblDisplayDefaultRating;
    private javax.swing.JLabel lblSet;
    private javax.swing.JLabel lblSetPresent;
    private javax.swing.JLabel lbltermsType;
    private javax.swing.JTextField monthSteps;
    private javax.swing.JTextField monthTerms;
    private javax.swing.JPanel ratingFrame;
    private javax.swing.JComboBox<String> ratingName;
    private javax.swing.JComboBox<String> ratingName1;
    private javax.swing.JTable ratingTable;
    public javax.swing.JTable ratingTableLoan;
    private javax.swing.JRadioButton rdOriginalValue;
    private javax.swing.JRadioButton rdPrimeValue;
    private javax.swing.JSlider sliderInterestOne;
    private javax.swing.JSlider sliderInterestTwo;
    public javax.swing.JTable tblData;
    private javax.swing.JTextField txtCompanyShares;
    private javax.swing.JTextField txtLoanAmount;
    public javax.swing.JTextField txtMonthlyPayment;
    private javax.swing.JTextField txtMonths;
    private javax.swing.JTextField txtRateMoney;
    private javax.swing.JTextField txtTerms;
    public javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtTotalInterest;
    private javax.swing.JTextField txtTotalMonthlyPayment;
    private javax.swing.JTextField txtTotalProfit;
    public javax.swing.JTextField txtgetProfit;
    private javax.swing.JTextField week1Steps;
    private javax.swing.JTextField week1Terms;
    private javax.swing.JTextField week2Steps;
    private javax.swing.JTextField week2Terms;
    private javax.swing.JTextField yearSteps;
    private javax.swing.JTextField yearTerms;
    // End of variables declaration//GEN-END:variables

}
