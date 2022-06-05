package classPack;

import javax.swing.JOptionPane;

public class ValidityCheck {

    boolean send = true;
    int count;
    int convert;

        
    public boolean checkMobilePhoneNumber(String getNumber) {
        String PhoneNumber = getNumber;
            String regexStr = "^(0\\-)?[0]{1}\\-?[7]{1}\\-?[0-8]{1}\\-?[0-9]{7}$";//hand phone
            send = PhoneNumber.matches(regexStr);

        return send;
    }
    public boolean checkLandPhoneNumber(String getNumber) {
        String PhoneNumber = getNumber;

        String regexStr = "^(0\\-)?[0]{1}\\-?[1-9]{2}\\-?[0-9]{7}$";//land phone
        //String regexStr ="^(0\\-)?[0]{1}\\-?[7]{1}\\-?[0-8]{1}\\-?[0-9]{7}$";//hand phone

        return PhoneNumber.matches(regexStr);
    }
    public boolean checkEmail(String getEmail) {
        String Email = getEmail;
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return Email.matches(regex);
    }
    public boolean checkNic(String getNic) {
        String Nic = getNic;
        if (Nic.length() == 9 || Nic.length() == 11) {
            for (int a = 0; a < Nic.length(); a++) {
                Boolean get = Character.isDigit(Nic.charAt(a));
                if (get) {
                    count++;
                }
            }
            if (count == Nic.length()) {
                send = true;
            } else {
                send = false;
            }

        } else {
            send = false;
        }

        return send;
    }      
    public boolean checkPresentage(String getPres){
        String pre=getPres;
        int b=0;
        if(pre.length()<=3 && pre.length()>=1){
            for(int v=0;v<pre.length();v++){
                Boolean get = Character.isDigit(pre.charAt(v));
                if(get){
                    b++;
                }
            }if (b == pre.length()) {
                send = true;
            } else {
                send = false;
            }

        } else {
            send = false;
        }
        return send;
    }
    public boolean onlyTypeDigits(char getNum){
        //char c=evt.getKeyChar();
        char c=getNum;
        if(Character.isLetter(c)){
            send=false;//number
        }else{
            send=true;//later
        }
        return send;
        
    }
    public boolean lengthLimited(char getCharacter,int maxCount,int getTextLength){
        //char c=evt.getKeyChar();
        int len=getTextLength;
        int co=maxCount;
        char c=getCharacter;
        if(Character.isDigit(c)){
            if(len<co){
                send=true;
            }else{
                send=false;
            }
            
        }else if(Character.isLetter(c)){
            if(len<co){
                send=true;
            }else{
                send=false;
            }
        }
        return send;     
    }

    public boolean NumberLimitCheck(String getNum){
        double val=0;
        val=Double.parseDouble(getNum);
        if(val==0){
            //0
            send=false;
            JOptionPane.showMessageDialog(null,"Please enter Number More than 0 ");
        }else if(val>0){
            //ok
            send=true;
        }else{
            //0
            JOptionPane.showMessageDialog(null,"Please enter Number More than 0 ");
            send=false;
        }
        return send;
    }
    
}
