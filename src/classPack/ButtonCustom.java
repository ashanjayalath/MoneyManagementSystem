package classPack;


import java.awt.*;
import javax.swing.JButton;


public class ButtonCustom extends JButton{
    public ButtonCustom(){
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(65,152,216));
    }
    
    
    protected void paintComponent(Graphics grphcs) {
       Graphics2D g2= (Graphics2D) grphcs;
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2.setColor(getBackground());
       g2.fillRoundRect(10, 10, getWidth()-20, getHeight()-20,20,20);
       super.paintComponent(grphcs);
    }
}
