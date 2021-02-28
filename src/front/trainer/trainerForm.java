/*
 * Created by JFormDesigner on Wed Dec 25 18:32:11 EET 2019
 */

package front.trainer;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class trainerForm extends JPanel {
    public trainerForm() {
        initComponents();
    }

    private void btnExerciseActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("exerciseForm");
        frame.setContentPane(new exerciseForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnSubsActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("subscriptionForm");
        frame.setContentPane(new subscriptionForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        btnSubs = new JButton();
        btnExercise = new JButton();

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );

            //---- btnSubs ----
            btnSubs.setText("Subscription operations");
            btnSubs.addActionListener(e -> btnSubsActionPerformed(e));

            //---- btnExercise ----
            btnExercise.setText("Exercise plan operations");
            btnExercise.addActionListener(e -> btnExerciseActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnSubs, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnExercise, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubs, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExercise, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(262, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JButton btnSubs;
    private JButton btnExercise;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
