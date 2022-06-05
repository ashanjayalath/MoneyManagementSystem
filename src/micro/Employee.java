package micro;

import classPack.ModelMenu;
import LoanForms.LoanGet;
import LoanForms.LoanCollection;
import LoanForms.LoanCollectionSummary;
import LoanForms.LoanHome;
import LoanForms.LoanPayment;
import LoanForms.LoanSettings;
import interfaces.EventMenuSlected;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import menus.Menu;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import swing.Header;

public class Employee extends javax.swing.JFrame {

    private Menu menu = new Menu();
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    private Header header;
    private String Name;
    public  String Id;   
    
    
    public Employee() {
        initComponents();
        init();
        this.setForeground(new Color(0, 0, 0, 0));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public Employee(String name,String id) {
        this.Name = name;
        this.Id = id;
        initComponents();
        init();
        this.setForeground(new Color(0, 0, 0, 0));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    Timer t;
    SimpleDateFormat st;

    private void init() {
        layout = new MigLayout("fill", "0[]0[100% , fill]0", "0[fill, top]0");
        body.setLayout(layout);
        main.setOpaque(false);
        header = new Header();
        //set action exit button
        menu.addEventLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);
                int a=JOptionPane.showInternalConfirmDialog(null,Name+" Do You Want Exit This Programme ?\n\nYes [Exit]\nNo [Logout]","Thank You.. "+Id,JOptionPane.YES_NO_CANCEL_OPTION);
                if(a==0){
                    //dispose();
                    System.exit(0);
                }else if(a==1){
                    Loging l=new Loging();
                    l.show();
                    dispose();
                }              
            }

        });    
        //set date
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(date);
        header.setDateDisplay(d);
        header.setEMP(Id);
        header.setEmpName(Name);
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");
                String time = st.format(dt);
                header.setTimeDisplay(time);
            }
        });
        t.start();

        menu.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        menu.setEvent(new EventMenuSlected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    showForm(new LoanHome());
                } else if (index == 1) {
                    showForm(new LoanGet());
                } else if (index == 2) {
                    showForm(new LoanPayment());
                } else if (index == 3) {
                    showForm(new LoanCollection());
                } else if (index == 4) {
                    showForm(new LoanCollectionSummary());
                } else if (index == 5) {
                    showForm(new LoanSettings());
                }

            }
        });
        main.setLayout(new BorderLayout());
        menu.addMenu(new ModelMenu("Home", new ImageIcon(getClass().getResource("/icon/home.png"))));
        menu.addMenu(new ModelMenu("Get Loan", new ImageIcon(getClass().getResource("/icon/debit.png"))));
        menu.addMenu(new ModelMenu("Loan Payment", new ImageIcon(getClass().getResource("/icon/cardPayment.png"))));
        menu.addMenu(new ModelMenu("Collection Details", new ImageIcon(getClass().getResource("/icon/moneyBag.png"))));
        menu.addMenu(new ModelMenu("Summery", new ImageIcon(getClass().getResource("/icon/dataBackup.png"))));
        menu.addMenu(new ModelMenu("Settings", new ImageIcon(getClass().getResource("/icon/settings.png"))));
        body.add(menu, "w 50!,spany 2");
        body.add(header, "h 60!,wrap");
        body.add(main, "w 100%,h 100%");

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    width = 50 + (150 * (1f - fraction));
                } else {
                    width = 50 + (150 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                body.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow;
            }

        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        showForm(new LoanHome());

    }

    private void showForm(Component com) {
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new swing.mainPanelAllRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 500));
        setUndecorated(true);

        body.setBackground(new java.awt.Color(244, 244, 244));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.mainPanelAllRound body;
    // End of variables declaration//GEN-END:variables
}
