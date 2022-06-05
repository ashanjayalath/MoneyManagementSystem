package classPack;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class AgeChecking {

    Date date = new Date();
    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy");
    String getYear = getFormat.format(date);
    int minimuYear = Integer.parseInt(getYear) - 18;
    int maximumYear = Integer.parseInt(getYear) - 60;
    int count;
    boolean send = true;
    private int getCurrentYear=Integer.parseInt(getYear);
    private int year;
    int firstOne;
    private String getMsg;
    
    public boolean checkAge(String getNic) {
    String Nic = getNic;
    int x = 0;
    int count = 0;
    if (Nic.length() == 9 || Nic.length() == 11) {
        if (Nic.length() == 9 && Nic.charAt(0)!='2' ){
            firstOne = Integer.parseInt("19" + String.valueOf(Nic.charAt(0)) + String.valueOf(Nic.charAt(1)));
            if (firstOne >= maximumYear) {
                getMsg="";
                x = 1;
            }else{
                String g=String.valueOf(getCurrentYear-firstOne);
                getMsg=("Your age "+g+" it's more than loan issu limite age");
            }
        }
        else if(Nic.length() == 10){
            send=false;
            getMsg="Invalid Id Number";
        }       
        else if (Nic.length() == 11 && Nic.charAt(0)=='2') {
            firstOne = Integer.parseInt(String.valueOf(Nic.charAt(0)) + String.valueOf(Nic.charAt(1)) + String.valueOf(Nic.charAt(2)) + String.valueOf(Nic.charAt(3)));
            if (firstOne >= maximumYear) {
                if(firstOne <= minimuYear){
                    getMsg="";
                    x = 1;
                }else{
                    String g=String.valueOf(getCurrentYear-firstOne);
                    if(Integer.valueOf(g)<0){
                        getMsg=("You not born in this time.Are You Time Traveling?");
                    }else{
                        getMsg=("Your age "+g+" it's less than loan issu limite age");
                    }
                }
            }else{
                String g=String.valueOf(getCurrentYear-firstOne);
                getMsg=("Your age "+g+" it's more than loan issu limite age");
            }
        }else{
            send=false;
            getMsg="Invalid Id Number";
        }


        if (x == 1) {
            for (int a = 0; a < Nic.length(); a++) {
                Boolean get = Character.isDigit(Nic.charAt(a));
                if (get) {
                    count++;
                }
            }
            if (count == Nic.length()) {
                send = true;
                year=firstOne;
            } else {
                year=0;
                send = false;
            }
        }

    }
    return send;
    }
    
    public int getYear() {
        return year;
    }

    public String getGetMsg() {
        return getMsg;
    }

}
