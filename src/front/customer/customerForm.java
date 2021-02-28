/*
 * Created by JFormDesigner on Thu Dec 26 23:46:54 EET 2019
 */

package front.customer;

import java.awt.event.*;
import Entity.CustomerEntity;
import Entity.ExercisePlanEntity;
import Entity.SubscriptionEntity;
import Entity.TrainerEntity;
import Factory.DataAccessFactory;
import Service.Impl.CustomerServiceImpl;
import Service.Impl.ExercisePlanServiceImpl;
import Service.Impl.SubscriptionServiceImpl;
import Service.Impl.TrainerServiceImpl;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author unknown
 */
public class customerForm extends JPanel {
    public customerForm(int _id) {
        this.setCustomerId(_id);
        initComponents();
    }
    private int customerId;
    private int subscriptionId=0;
    private void fillTable(){
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        CustomerEntity customer = customerService.getCustomerById(this.getCustomerId());
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        if(customer != null){
            SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl(DataAccessFactory.getRepository("txt","subscriptions.txt"));
            TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt","trainers.txt"));
            ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
            Object columnList[] = {"Id","Exercise Name","Trainer","End date","Duration"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnList);
            for(SubscriptionEntity subscription : subscriptionService.getCustomerSubscription(customer)){
                Object rowData[] = new Object[5];
                ExercisePlanEntity exercise = exercisePlanService.getExercisePlanById(subscription.getExerciseId());
                TrainerEntity trainer = trainerService.getTrainerById(subscription.getTrainerId());
                rowData[0] = subscription.getId();
                rowData[1] = exercise.getExerciseName();
                rowData[2] = trainer.getName()+" "+trainer.getSurname();
                rowData[3] = format.format(subscription.getEndDate());
                rowData[4] = exercise.getDuration();
                model.addRow(rowData);
            }
            tblLessons.setModel(model);
        }
    }

    private void btnSaveActionPerformed(ActionEvent e) {
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        CustomerEntity customer = customerService.getCustomerById(this.getCustomerId());
        customer.setPassword(txPassword.getText());
        customer.setPhoneNumber(txPhone.getText());
        customerService.saveCustomer(customer);
        JOptionPane.showMessageDialog(null, "Your information is updated !");
    }

    private void tblLessonsMouseClicked(MouseEvent e) {
        this.setSubscriptionId(Integer.parseInt(tblLessons.getValueAt(tblLessons.getSelectedRow(),0).toString()));
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl(DataAccessFactory.getRepository("txt","subscriptions.txt"));
        subscriptionService.deleteSubscription(subscriptionService.getSubscriptionById(this.getSubscriptionId()));
        JOptionPane.showMessageDialog(null, "Your exercise is deleted !");
        fillTable();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblLessons = new JTable();
        btnDelete = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        txPhone = new JTextField();
        txPassword = new JTextField();
        btnSave = new JButton();
        lbFullName = new JLabel();

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
            .swing.border.EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing
            .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
            Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
            ),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
            public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName(
            )))throw new RuntimeException();}});

            //======== scrollPane1 ========
            {

                //---- tblLessons ----
                tblLessons.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblLessonsMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblLessons);
            }

            //---- btnDelete ----
            btnDelete.setText("Delete my exercise");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            //---- label1 ----
            label1.setText("My information");

            //---- label2 ----
            label2.setText("Full name");

            //---- label3 ----
            label3.setText("Password");

            //---- label4 ----
            label4.setText("Phone Num");

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> btnSaveActionPerformed(e));

            //---- lbFullName ----
            lbFullName.setText("text");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(0, 50, Short.MAX_VALUE)
                                .addComponent(label1)
                                .addGap(59, 59, 59))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnDelete, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label4)
                                            .addComponent(label3)
                                            .addComponent(label2))
                                        .addGap(18, 18, 18)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(txPhone, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                            .addComponent(txPassword, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                            .addComponent(lbFullName, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                    .addComponent(btnSave, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                .addGap(32, 32, 32))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(10, 10, 10)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(lbFullName))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(txPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnSave)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)))
                        .addContainerGap(43, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        CustomerEntity customer = customerService.getCustomerById(this.getCustomerId());
        lbFullName.setText(customer.getName()+" "+customer.getSurname());
        txPassword.setText(customer.getPassword());
        txPhone.setText(customer.getPhoneNumber());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblLessons;
    private JButton btnDelete;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField txPhone;
    private JTextField txPassword;
    private JButton btnSave;
    private JLabel lbFullName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
