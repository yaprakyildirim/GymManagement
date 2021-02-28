/*
 * Created by JFormDesigner on Mon Dec 23 15:21:17 EET 2019
 */

package front;

import Entity.AbsPersonEntity;
import Entity.CustomerEntity;
import Factory.DataAccessFactory;
import Factory.PersonServiceFactory;
import Payment.PayWithCash;
import Service.IPersonService;
import Service.Impl.CustomerServiceImpl;
import front.customer.customerForm;
import front.manager.managerForm;
import front.trainer.trainerForm;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;

/**
 * @author unknown
 */
public class loginForm extends JPanel {

    public loginForm() {
        initComponents();
    }

    private void btnLoginActionPerformed(ActionEvent e) {

        CustomerEntity customerEntity = new CustomerEntity(1,"tarik","filiz","545 747 3009","12345",true,"test adress","tarikfiliz@yahoo.com",0,"NOT_PAID");
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt", "customers.txt"));

        customerService.makePayment(customerEntity,100,new PayWithCash());

        customerService.saveCustomer(customerEntity);


        IPersonService loginService = PersonServiceFactory.getService(cmbUserType.getSelectedIndex());
        Object obj = loginService.login(txUsername.getText(),txPassword.getText());
        AbsPersonEntity absPersonEntity = (AbsPersonEntity) obj;
        if(absPersonEntity != null){
            if(cmbUserType.getSelectedIndex() == 1){
                // Manager screen opened.
                this.setVisible(false);
                JFrame frame = new JFrame("managerForm");
                frame.setContentPane(new managerForm().getPanel1());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }else if(cmbUserType.getSelectedIndex() ==0){
                this.setVisible(false);
                JFrame frame = new JFrame("trainerForm");
                frame.setContentPane(new trainerForm().getPanel1());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }else if(cmbUserType.getSelectedIndex() == 2){
                this.setVisible(false);
                JFrame frame = new JFrame("customerForm");
                JPanel custForm = new customerForm(absPersonEntity.getId());
                frame.setContentPane(((customerForm) custForm).getPanel1());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        txUsername = new JTextField();
        txPassword = new JTextField();
        cmbUserType = new JComboBox();
        label3 = new JLabel();
        btnLogin = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
        .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing
        .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
        Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.red
        ), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName(
        )))throw new RuntimeException();}});
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 153, 153));

            //---- label1 ----
            label1.setText("Name");

            //---- label2 ----
            label2.setText("Password");

            //---- label3 ----
            label3.setText("User type");

            //---- btnLogin ----
            btnLogin.setText("Login");
            btnLogin.addActionListener(e -> btnLoginActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbUserType, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(txUsername, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(txPassword, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                        .addContainerGap(43, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(txUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbUserType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addComponent(btnLogin)
                        .addContainerGap(72, Short.MAX_VALUE))
            );
        }
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        cmbUserType.addItem(new ComboItem("Trainer","1"));
        cmbUserType.addItem(new ComboItem("Manager","2"));
        cmbUserType.addItem(new ComboItem("Customer","3"));

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JTextField txUsername;
    private JTextField txPassword;
    private JComboBox cmbUserType;
    private JLabel label3;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame("loginForm");
        frame.setContentPane(new loginForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
