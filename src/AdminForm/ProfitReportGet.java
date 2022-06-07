package AdminForm;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ProfitReportGet extends javax.swing.JFrame {


    public double []val=new double[3];
    public String []typesAndPrecent=new String[3];
    public String [] selectIndex=new String[200];
    public int pass=0;
    public int count=0;
    public int inner=0;
    public int checked=1;
    public ProfitReportGet() {
        initComponents();
        
       
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        lblLoanAmountSet = new javax.swing.JLabel();
        btnPie = new javax.swing.JButton();
        btnLine = new javax.swing.JButton();
        btnBar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportTableTwo = new javax.swing.JTable();
        addData = new javax.swing.JButton();
        clearAll = new javax.swing.JButton();
        clearSelect = new javax.swing.JButton();
        btnLineBreak = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profit");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1000, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        reportTable.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Row Id", "Loan Type", "Loan Value", "Rates (%)", "Loan Divided Terms", "Due Amount", "Equated Profit", "Equated Normal Profit", "Equal Profit", "Maximum Profit", "Maximum Rate Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportTable.setToolTipText("");
        reportTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        reportTable.setRowHeight(25);
        reportTable.setSelectionBackground(new java.awt.Color(153, 153, 153));
        reportTable.setShowGrid(true);
        reportTable.getTableHeader().setReorderingAllowed(false);
        reportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportTableMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportTableMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(reportTable);

        lblLoanAmountSet.setBackground(new java.awt.Color(255, 255, 255));
        lblLoanAmountSet.setForeground(new java.awt.Color(0, 204, 0));
        lblLoanAmountSet.setText("Loan Amount");

        btnPie.setText("Pie Chart");
        btnPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPieActionPerformed(evt);
            }
        });

        btnLine.setText("Line Chart");
        btnLine.setEnabled(false);
        btnLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLineActionPerformed(evt);
            }
        });

        btnBar.setText("Bar Chart");
        btnBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarActionPerformed(evt);
            }
        });

        jScrollPane2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        reportTableTwo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        reportTableTwo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Row Id", "Loan Type", "Loan Value", "Rates (%)", "Loan Divided Terms", "Due Amount", "Equated Profit", "Equated Normal Profit", "Equal Profit", "Maximum Profit", "Maximum Rate Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportTableTwo.setToolTipText("");
        reportTableTwo.setIntercellSpacing(new java.awt.Dimension(0, 0));
        reportTableTwo.setRowHeight(25);
        reportTableTwo.setSelectionBackground(new java.awt.Color(153, 153, 153));
        reportTableTwo.setShowGrid(true);
        reportTableTwo.getTableHeader().setReorderingAllowed(false);
        reportTableTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportTableTwoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(reportTableTwo);

        addData.setText("Add Selected Data");
        addData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDataActionPerformed(evt);
            }
        });

        clearAll.setText("Clear all Table Data");
        clearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllActionPerformed(evt);
            }
        });

        clearSelect.setText("Clear Selected Data");
        clearSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSelectActionPerformed(evt);
            }
        });

        btnLineBreak.setText("Line Break");
        btnLineBreak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLineBreakActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Loan Amount :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoanAmountSet, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addGap(732, 732, 732))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(btnLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(btnBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(90, 90, 90)
                            .addComponent(addData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(btnLineBreak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(clearAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(10, 10, 10)
                            .addComponent(clearSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(24, 24, 24)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblLoanAmountSet))
                .addContainerGap(666, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGap(11, 11, 11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnPie)
                        .addComponent(btnLine)
                        .addComponent(btnBar)
                        .addComponent(addData)
                        .addComponent(btnLineBreak)
                        .addComponent(btnRefresh)
                        .addComponent(clearAll)
                        .addComponent(clearSelect))
                    .addGap(9, 9, 9)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLineBreakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLineBreakActionPerformed
        DefaultTableModel model = (DefaultTableModel)reportTableTwo.getModel();
        model.addRow(new Object[]{});
    }//GEN-LAST:event_btnLineBreakActionPerformed

    private void clearSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSelectActionPerformed
        int [] index = reportTableTwo.getSelectedRows();
        DefaultTableModel model=(DefaultTableModel) reportTableTwo.getModel();
        for(Integer b:index){
            model.removeRow(b);
        }
    }//GEN-LAST:event_clearSelectActionPerformed

    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllActionPerformed
       DefaultTableModel model = (DefaultTableModel) reportTableTwo.getModel();
        while(true){
            if(reportTableTwo.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }

    }//GEN-LAST:event_clearAllActionPerformed

    private void addDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDataActionPerformed
        TableModel model1=reportTable.getModel();
        int [] index = reportTable.getSelectedRows();
        Object[] row = new Object[11];
        DefaultTableModel model2=(DefaultTableModel) reportTableTwo.getModel();
        try{
            if(selectIndex[0]==null){
                pass=1;
                inner=0;
                for(int x=0;x<index.length;x++){
                    selectIndex[x]=Integer.toString(index[x]);
                }
                count=index.length;
            }else{
                pass=0;
            }
            int xc=0;
            if(pass==0){
                for(int num=0;num<index.length;num++){
                    for(int numTwo=0;numTwo<count;numTwo++){
                        if(index[num]==Integer.parseInt(selectIndex[numTwo])){
                            if(checked==1){
                                checked=JOptionPane.showConfirmDialog(null,"Row "+index[num]+" Value is Duplicated.\nDo you want to Automatically Skipe Duplicated rows ?","Duplicate Skiping",JOptionPane.YES_NO_OPTION);
                            }
                            xc=1;
                        }
                    }
                    if(xc==0){
                        selectIndex[count++]=Integer.toString(index[num]);
                    }xc=0;
                }
            }
            while(inner<count){
                row[0]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 0);
                row[1]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 1);
                row[2]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 2);
                row[3]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 3);
                row[4]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 4);
                row[5]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 5);
                row[6]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 6);
                row[7]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 7);
                row[8]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 8);
                row[9]=model1.getValueAt(Integer.parseInt(selectIndex[inner]), 9);
                row[10]=model1.getValueAt(Integer.parseInt(selectIndex[inner]),10);
                model2.addRow(row);
                inner++;
            }
            inner=count;

        }catch(ArrayIndexOutOfBoundsException a){
            System.err.println(a);
        }
    }//GEN-LAST:event_addDataActionPerformed

    private void reportTableTwoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTableTwoMouseClicked
        int selectedRowIndex=reportTableTwo.getSelectedRow();
        TableModel model = reportTableTwo.getModel();

        val[0]=Double.parseDouble(model.getValueAt(selectedRowIndex,6).toString());
        val[1]=Double.parseDouble(model.getValueAt(selectedRowIndex,7).toString());
        val[2]=Double.parseDouble(model.getValueAt(selectedRowIndex,8).toString());
        typesAndPrecent[0]=(model.getValueAt(selectedRowIndex,0).toString());
        typesAndPrecent[1]=(model.getValueAt(selectedRowIndex,1).toString());
        typesAndPrecent[2]=(model.getValueAt(selectedRowIndex,2).toString());
    }//GEN-LAST:event_reportTableTwoMouseClicked

    private void btnBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarActionPerformed
        DefaultCategoryDataset barChart=new DefaultCategoryDataset();

        barChart.setValue(val[0],"Equrate","Equrate");
        barChart.setValue(val[1],"Equrate Normal","Equrate Normal");
        barChart.setValue(val[2],"Equal","Equal");

        JFreeChart Chart = ChartFactory.createBarChart(typesAndPrecent[1]+" - "+typesAndPrecent[2],"Rate Types","Total Profit",barChart, PlotOrientation.VERTICAL,false,true,true);
        CategoryPlot chart = Chart.getCategoryPlot();
        chart.setRangeGridlinePaint(Color.BLUE);

        ChartFrame bar= new ChartFrame(typesAndPrecent[0],Chart);
        bar.setVisible(true);
        bar.setSize(550,600);
    }//GEN-LAST:event_btnBarActionPerformed

    private void btnLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLineActionPerformed

        DefaultCategoryDataset lineChart=new DefaultCategoryDataset();

        lineChart.setValue(val[0],"Equrate","Equrate");
        lineChart.setValue(val[1],"Equrate Normal","Equrate Normal");
        lineChart.setValue(val[2],"Equal","Equal");

        JFreeChart Chart = ChartFactory.createLineChart(typesAndPrecent[1]+" - "+typesAndPrecent[2],"Rate Types","Total Profit",lineChart, PlotOrientation.VERTICAL,false,true,true);
        CategoryPlot plot = Chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLUE);

        ChartFrame line= new ChartFrame(typesAndPrecent[0],Chart);
        line.setVisible(true);
        line.setSize(550,600);
    }//GEN-LAST:event_btnLineActionPerformed

    private void btnPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPieActionPerformed

        DefaultPieDataset piedataset = new DefaultPieDataset();
        piedataset.setValue("Equrate "+(int)val[0], val[0]);
        piedataset.setValue("Equrate Normal "+(int)val[1], val[1]);
        piedataset.setValue("Equal "+(int)val[2], val[2]);

        JFreeChart chart = ChartFactory.createPieChart(typesAndPrecent[1]+" - "+typesAndPrecent[2], piedataset,true,true,true);
        PiePlot p =(PiePlot) chart.getPlot();
        ChartFrame frame= new ChartFrame(typesAndPrecent[0],chart);
        frame.setVisible(true);
        frame.setSize(550,600);

    }//GEN-LAST:event_btnPieActionPerformed

    private void reportTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTableMouseExited

    }//GEN-LAST:event_reportTableMouseExited

    private void reportTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTableMouseClicked
        int selectedRowIndex=reportTable.getSelectedRow();
        TableModel model = reportTable.getModel();

        val[0]=Double.parseDouble(model.getValueAt(selectedRowIndex,6).toString());
        val[1]=Double.parseDouble(model.getValueAt(selectedRowIndex,7).toString());
        val[2]=Double.parseDouble(model.getValueAt(selectedRowIndex,8).toString());
        typesAndPrecent[0]=(model.getValueAt(selectedRowIndex,0).toString());
        typesAndPrecent[1]=(model.getValueAt(selectedRowIndex,1).toString());
        typesAndPrecent[2]=(model.getValueAt(selectedRowIndex,2).toString());
    }//GEN-LAST:event_reportTableMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ProfitReportGet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfitReportGet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfitReportGet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfitReportGet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfitReportGet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addData;
    private javax.swing.JButton btnBar;
    private javax.swing.JButton btnLine;
    private javax.swing.JButton btnLineBreak;
    private javax.swing.JButton btnPie;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton clearAll;
    private javax.swing.JButton clearSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblLoanAmountSet;
    public javax.swing.JTable reportTable;
    public javax.swing.JTable reportTableTwo;
    // End of variables declaration//GEN-END:variables
}
