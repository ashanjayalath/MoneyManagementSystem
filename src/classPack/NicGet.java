package classPack;

public class NicGet {
    public static String getNicNumber(String getNic){
        String newNic;
        if(getNic.length()==9){
            newNic="19"+getNic;
        }else{
            newNic=getNic;
        }
        return newNic;
    }
}
