package swing;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;


public class LogInterface extends javax.swing.JPanel {

    public LogInterface() {
        initComponents();
        setOpaque(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movment = new javax.swing.JPanel();

        movment.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        movment.setOpaque(false);

        javax.swing.GroupLayout movmentLayout = new javax.swing.GroupLayout(movment);
        movment.setLayout(movmentLayout);
        movmentLayout.setHorizontalGroup(
            movmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        movmentLayout.setVerticalGroup(
            movmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(movment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 =(Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g= new GradientPaint(0,0,Color.decode("#00F260"),0,getHeight(),Color.decode("#0575E6"));
        g2.setPaint(g);
        g2.fillRoundRect(0,0,getWidth(),getHeight(),15,15);
        g2.fillRect(getWidth()-20,0,getWidth(),getHeight());
        super.paintComponent(grphcs);        
    }
    

    
    private int x;
    private int y;
    
    public void initMoving2(JFrame frame){
        movment.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent m) {
                x = m.getX();
                y = m.getY();
            }
        });
        movment.addMouseMotionListener(new MouseMotionAdapter (){
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x,e.getYOnScreen() - y);
            }        
        });
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel movment;
    // End of variables declaration//GEN-END:variables
}
