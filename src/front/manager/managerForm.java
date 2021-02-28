/*
 * Created by JFormDesigner on Mon Dec 23 16:38:16 EET 2019
 */

package front.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class managerForm extends JPanel {
    public managerForm() {
        initComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("managerForm");
        frame.setContentPane(new managerForm().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnTrainerOpActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("trainerForm");
        frame.setContentPane(new trainerForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("customerForm");
        frame.setContentPane(new customerForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnEquipmentActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("equipmentForm");
        frame.setContentPane(new equipmentForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnHallActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("hallForm");
        frame.setContentPane(new hallForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        btnTrainerOp = new JButton();
        button1 = new JButton();
        btnEquipment = new JButton();
        btnHall = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 153, 153));

            //---- btnTrainerOp ----
            btnTrainerOp.setText("Trainer operations");
            btnTrainerOp.addActionListener(e -> btnTrainerOpActionPerformed(e));

            //---- button1 ----
            button1.setText("Customer operations");
            button1.addActionListener(e -> button1ActionPerformed(e));

            //---- btnEquipment ----
            btnEquipment.setText("Equipment Operations");
            btnEquipment.addActionListener(e -> btnEquipmentActionPerformed(e));

            //---- btnHall ----
            btnHall.setText("Hall operations");
            btnHall.addActionListener(e -> btnHallActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEquipment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTrainerOp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(btnHall, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                        .addGap(38, 38, 38))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrainerOp, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEquipment, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHall, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(154, Short.MAX_VALUE))
            );
        }
        add(panel1, BorderLayout.NORTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JButton btnTrainerOp;
    private JButton button1;
    private JButton btnEquipment;
    private JButton btnHall;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
