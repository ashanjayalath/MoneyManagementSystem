package swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class colorSquared extends javax.swing.JPanel {


    public colorSquared() {
        initComponents();
        setOpaque(false);
    }

    
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 =(Graphics2D) grphcs;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //GradientPaint g= new GradientPaint(0,0,Color.decode("#00F260"),0,getHeight(),Color.decode("#0575E6"));
        GradientPaint h= new GradientPaint(0,0,Color.decode("#00F260"),getWidth(),0,Color.decode("#0575E6"));
        g2.setPaint(h);
        g2.fillRoundRect(0,0,getWidth(),getHeight(),0,0);
        //g2.fillRect(getWidth()-20,0,getWidth(),getHeight());//left side only
        //g2.fillRect(0,0,getWidth()-20,getHeight());right side only round
        //g2.fillRect(0,getHeight()-20,getWidth(),getHeight());//top only
        //g2.fillRect(0,getHeight()-20,getWidth(),getHeight());
        super.paintComponent(grphcs);        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
