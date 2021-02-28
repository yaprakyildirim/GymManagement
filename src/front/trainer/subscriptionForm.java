/*
 * Created by JFormDesigner on Thu Dec 26 19:03:54 EET 2019
 */

package front.trainer;

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
import front.ComboItem;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author unknown
 */
public class subscriptionForm extends JPanel {
    public subscriptionForm() {
        initComponents();
    }
    private int subscriptionId;
    public void fillTable(){
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt","trainers.txt"));
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl(DataAccessFactory.getRepository("txt","subscriptions.txt"));
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Object columnList[] = {"Id","Customer","Exercise","Trainer","End date"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        if(subscriptionService.getAllSubscriptions() != null) {
            for (SubscriptionEntity subscription : subscriptionService.getAllSubscriptions()) {
                Object rowData[] = new Object[5];
                rowData[0] = subscription.getId();
                rowData[1] = customerService.getCustomerById(subscription.getCustomerId()).getName();
                rowData[2] = exercisePlanService.getExercisePlanById(subscription.getExerciseId()).getExerciseName();
                rowData[3] = trainerService.getTrainerById(subscription.getTrainerId()).getName();
                rowData[4] = format.format(subscription.getEndDate());
                model.addRow(rowData);
            }
        }
        tblSubs.setModel(model);

    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnSaveActionPerformed(ActionEvent e) throws ParseException {
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt","trainers.txt"));
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl(DataAccessFactory.getRepository("txt","subscriptions.txt"));

        SubscriptionEntity subscriptionEntity = null;
        if(this.subscriptionId != 0){
            subscriptionEntity = subscriptionService.getSubscriptionById(this.subscriptionId);
        }else{
            subscriptionEntity = new SubscriptionEntity();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        subscriptionEntity.setEndDate(format.parse(txEndDate.getText()));
        subscriptionEntity.setCustomerId(cmbCustomer.getSelectedIndex()+1);
        subscriptionEntity.setTrainerId(cmbTrainer.getSelectedIndex()+1);
        subscriptionEntity.setExerciseId(cmbExercise.getSelectedIndex()+1);
        int trainerCountAtSameDay = 0;
        int customerMultipleLessonCount = 0;
        for(SubscriptionEntity subscription : subscriptionService.getAllSubscriptions()){
            if(subscription.getTrainerId() == subscriptionEntity.getTrainerId() && subscription.getEndDate().equals(subscriptionEntity.getEndDate())){
                trainerCountAtSameDay++;
            }
            if(subscription.getCustomerId() == subscriptionEntity.getCustomerId() && subscription.getEndDate().equals(subscriptionEntity.getEndDate())){
                customerMultipleLessonCount++;
            }
        }
        boolean errorStatus = false;
        if(trainerCountAtSameDay != 0) {
            JOptionPane.showMessageDialog(null, "Same trainer have another lesson that day !");
            errorStatus = true;
        }
        if(customerMultipleLessonCount == 2){
            JOptionPane.showMessageDialog(null, "Customer cant take more than 2 lessons at same day !");
            errorStatus = true;
        }

        if(!errorStatus) {
            subscriptionService.saveSubscription(subscriptionEntity);
        }
        clearFields();
        fillTable();
    }
    private void clearFields(){
        cmbExercise.setSelectedIndex(0);
        cmbTrainer.setSelectedIndex(0);
        cmbCustomer.setSelectedIndex(0);
        txEndDate.setText("");
    }
    private void btnDeleteActionPerformed(ActionEvent e) {
        SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl(DataAccessFactory.getRepository("txt","subscriptions.txt"));
        subscriptionService.deleteSubscription(subscriptionService.getSubscriptionById(this.subscriptionId));
        fillTable();
    }

    private void btnClearActionPerformed(ActionEvent e) {
        clearFields();
    }

    private void tblSubsMouseClicked(MouseEvent e) {
        SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl(DataAccessFactory.getRepository("txt","subscriptions.txt"));
        SubscriptionEntity subscriptionEntity = subscriptionService.getSubscriptionById(Integer.parseInt(tblSubs.getValueAt(tblSubs.getSelectedRow(),0).toString()));
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        cmbExercise.setSelectedIndex(subscriptionEntity.getExerciseId()-1);
        cmbCustomer.setSelectedIndex(subscriptionEntity.getCustomerId()-1);
        cmbTrainer.setSelectedIndex(subscriptionEntity.getTrainerId()-1);
        txEndDate.setText(format.format(subscriptionEntity.getEndDate()));
        this.subscriptionId = subscriptionEntity.getId();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblSubs = new JTable();
        cmbTrainer = new JComboBox();
        cmbExercise = new JComboBox();
        cmbCustomer = new JComboBox();
        txEndDate = new JTextField();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) )
            ; panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;

            //======== scrollPane1 ========
            {

                //---- tblSubs ----
                tblSubs.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblSubsMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblSubs);
            }

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> {
			button1ActionPerformed(e);
                try {
                    btnSaveActionPerformed(e);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            });

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            //---- btnClear ----
            btnClear.setText("Clear");
            btnClear.addActionListener(e -> btnClearActionPerformed(e));

            //---- label1 ----
            label1.setText("Trainer");

            //---- label2 ----
            label2.setText("Exercise");

            //---- label3 ----
            label3.setText("Customer");

            //---- label4 ----
            label4.setText("End date");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label1)
                                    .addComponent(label2)
                                    .addComponent(label3)
                                    .addComponent(label4))
                                .addGap(65, 65, 65)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(cmbExercise, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(cmbCustomer, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(cmbTrainer, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(txEndDate, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                .addGap(45, 45, 45))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbTrainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1))
                                .addGap(29, 29, 29)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbExercise, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2))
                                .addGap(33, 33, 33)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4))
                                .addGap(23, 23, 23)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSave)
                                    .addComponent(btnDelete)
                                    .addComponent(btnClear))))
                        .addGap(2, 2, 2))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt","trainers.txt"));
        CustomerServiceImpl customerService = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        for(TrainerEntity trainer:trainerService.getAllTrainers()){
            cmbTrainer.addItem(new ComboItem(trainer.getName()+" "+trainer.getSurname(),String.valueOf(trainer.getId())));
        }
        for(CustomerEntity customer:customerService.getAllCustomers()){
            cmbCustomer.addItem(new ComboItem(customer.getName()+" "+customer.getSurname(),String.valueOf(customer.getId())));
        }
        for(ExercisePlanEntity plan:exercisePlanService.getAllExercisePlans()){
            cmbExercise.addItem(new ComboItem(plan.getExerciseName(),String.valueOf(plan.getId())));
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblSubs;
    private JComboBox cmbTrainer;
    private JComboBox cmbExercise;
    private JComboBox cmbCustomer;
    private JTextField txEndDate;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
