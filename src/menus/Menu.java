package menus;

import classPack.ButtonCustom;
import classPack.ModelMenu;
import interfaces.EventMenuSlected;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import swing.MenuItem;

public class Menu extends javax.swing.JPanel {

    public void setEvent(EventMenuSlected event) {
        this.event = event;
    }
    
    private MigLayout layout;
    private JPanel panelMenu;
    private JButton cmdMenu;
    private JButton cmdLogOut;
    private EventMenuSlected event;

    public Menu() {
        initComponents();
        setOpaque(false);
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "50[]0"));
        panelMenu = new JPanel();
        createButtonMenu();
        createButtonLogout();
        panelMenu.setOpaque(false);
        layout = new MigLayout("fillx,wrap", "0[fill]0", "0[]0");
        panelMenu.setLayout(layout);
        add(cmdMenu,"pos 1al 0al 100% 50");
        add(cmdLogOut,"pos 1al 1al 100% 100,height 60!");
        add(panelMenu);
        
    }
    
    public void addMenu(ModelMenu menu) {
        MenuItem item = new MenuItem(menu.getIcon(), menu.getMenuName(), panelMenu.getComponentCount());
        item.addEvent(new EventMenuSlected(){
            @Override
            public void selected(int index){
                clearMenu(index);
            }
        });
        item.addEvent(event);
        panelMenu.add(item);
    }
    
    private void createButtonMenu(){
        cmdMenu=new JButton();
        cmdMenu.setContentAreaFilled(false);
        cmdMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMenu.setIcon(new ImageIcon(getClass().getResource("/icon/menu.png")));
    }
    
    private void createButtonLogout(){
        cmdLogOut=new ButtonCustom();
        cmdLogOut.setIcon(new ImageIcon(getClass().getResource("/icon/exit.png")));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint h = new GradientPaint(0, 0, Color.decode("#00F260"),0,getHeight(), Color.decode("#0575E6"));
        g2.setPaint(h);
        g2.fillRoundRect(0,0,getWidth(),getHeight(),0,0);//1515
        g2.fillRect(getWidth()-20,0,getWidth(),getHeight());
        super.paintComponent(g);
    }

    private void clearMenu(int exceptIndex){
        for(Component com:panelMenu.getComponents()){
            MenuItem item=(MenuItem) com;
            if(item.getIndex()!=exceptIndex){
                item.setSelected(false);
            }
        }
    }

    public void addEventMenu(ActionListener event){
        cmdMenu.addActionListener(event);
    }
    
    public void addEventLogout(ActionListener event){
        cmdLogOut.addActionListener(event);
    }
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
