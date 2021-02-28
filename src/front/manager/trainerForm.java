/*
 * Created by JFormDesigner on Mon Dec 23 16:40:53 EET 2019
 */

package front.manager;

import java.awt.event.*;
import Entity.TrainerEntity;
import Factory.DataAccessFactory;
import Service.Impl.GymHallServiceImpl;
import Service.Impl.TrainerServiceImpl;
import front.ComboItem;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author unknown
 */
public class trainerForm extends JPanel {
    private int trainerId = 0;
    public trainerForm() {
        initComponents();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("trainerForm");
        frame.setContentPane(new trainerForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void tblTrainerMouseClicked(MouseEvent e) {
        this.trainerId = Integer.parseInt(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),0).toString());
        txName.setText(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),1).toString());
        txSurname.setText(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),2).toString());
        txPhone.setText(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),3).toString());
        txPassword.setText(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),4).toString());
        txSalary.setText(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),6).toString());
        txHallId.setText(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),7).toString());
        if(tblTrainer.getValueAt(tblTrainer.getSelectedRow(),5).toString().equals("true")){
            cmbStatus.setSelectedIndex(0);
        }else{
            cmbStatus.setSelectedIndex(1);
        }
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt", "trainers.txt"));
        TrainerEntity trainer = trainerService.getTrainerById(this.trainerId);
        trainerService.deleteTrainer(trainer);
        clearFields();
        fillTable();
    }

    private void btnUpdateActionPerformed(ActionEvent e) {
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt", "trainers.txt"));
        TrainerEntity trainerEntity = null;
        if(this.trainerId != 0){
            trainerEntity = trainerService.getTrainerById(this.trainerId);
        }else{
            trainerEntity = new TrainerEntity();
            trainerEntity.setStartDate(new Date());
            trainerEntity.setEndDate(new Date());
        }
            trainerEntity.setName(txName.getText());
            trainerEntity.setSurname(txSurname.getText());
            trainerEntity.setPhoneNumber(txPhone.getText());
            trainerEntity.setPassword(txPassword.getText());
            trainerEntity.setSalary(Double.parseDouble(txSalary.getText()));
            trainerEntity.setHallId(Integer.parseInt(txHallId.getText()));
            if(cmbStatus.getSelectedIndex() == 0){
                trainerEntity.setStatus(true);
            }else{
                trainerEntity.setStatus(false);
            }
            trainerService.saveTrainer(trainerEntity);
            clearFields();
            fillTable();

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblTrainer = new JTable();
        txName = new JTextField();
        txSurname = new JTextField();
        txPhone = new JTextField();
        txPassword = new JTextField();
        txSalary = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        label6 = new JLabel();
        txHallId = new JTextField();
        cmbStatus = new JComboBox();

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) )
            ; panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;

            //======== scrollPane1 ========
            {

                //---- tblTrainer ----
                tblTrainer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblTrainerMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblTrainer);
            }

            //---- label1 ----
            label1.setText("Name");

            //---- label2 ----
            label2.setText("Surname");

            //---- label3 ----
            label3.setText("Phone");

            //---- label4 ----
            label4.setText("Password");

            //---- label5 ----
            label5.setText("Salary");

            //---- btnUpdate ----
            btnUpdate.setText("Update");
            btnUpdate.addActionListener(e -> btnUpdateActionPerformed(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            //---- label6 ----
            label6.setText("Hall id");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                        .addComponent(txSurname, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                        .addComponent(txName, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                        .addComponent(txPhone, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                        .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label5)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbStatus, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                            .addComponent(txHallId, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                            .addComponent(txSalary, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                                .addGap(28, 28, 28))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btnUpdate)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDelete))
                                    .addComponent(label6))
                                .addContainerGap(35, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(txHallId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete))
                        .addGap(39, 39, 39))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
    }
    private void clearFields(){
        this.trainerId = 0;
        txName.setText("");
        txSurname.setText("");
        txPhone.setText("");
        txPassword.setText("");
        txSalary.setText("");
        txHallId.setText("");
    }
    private void fillTable(){
        Object columnList[] = {"Id","Name","Surname","phoneNum","password","isActive","salary","hallId","startDate","endDate"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt", "trainers.txt"));
        ArrayList<TrainerEntity> trainerEntityArrayList = trainerService.getAllTrainers();
        Object rowData[] = new Object[10];

        GymHallServiceImpl hallService = new GymHallServiceImpl(DataAccessFactory.getRepository("txt","halls.txt"));

        for(TrainerEntity trainerEntity : trainerEntityArrayList){
            rowData[0] = trainerEntity.getId();
            rowData[1] = trainerEntity.getName();
            rowData[2] = trainerEntity.getSurname();
            rowData[3] = trainerEntity.getPhoneNumber();
            rowData[4] = trainerEntity.getPassword();
            rowData[5] = trainerEntity.isStatus();
            rowData[6] = trainerEntity.getSalary();
            rowData[7] = hallService.getGymHallById(trainerEntity.getHallId());
            rowData[8] = trainerEntity.getStartDate();
            rowData[9] = trainerEntity.getEndDate();
            model.addRow(rowData);
        }
        tblTrainer.setModel(model);
        cmbStatus.addItem(new ComboItem("Active","1"));
        cmbStatus.addItem(new ComboItem("Passive","0"));

    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblTrainer;
    private JTextField txName;
    private JTextField txSurname;
    private JTextField txPhone;
    private JTextField txPassword;
    private JTextField txSalary;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel label6;
    private JTextField txHallId;
    private JComboBox cmbStatus;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
