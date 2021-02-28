/*
 * Created by JFormDesigner on Tue Dec 24 15:59:49 EET 2019
 */

package front.manager;

import java.awt.event.*;
import Entity.EquipmentEntity;
import Entity.GymHallEntity;
import Factory.DataAccessFactory;
import Service.Impl.EquipmentServiceImpl;
import Service.Impl.GymHallServiceImpl;
import front.ComboItem;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unknown
 */
public class hallForm extends JPanel {
    public hallForm() {
        initComponents();
    }
    private int hallId=0;
    private void fillTable(){
        GymHallServiceImpl hallService = new GymHallServiceImpl(DataAccessFactory.getRepository("txt","halls.txt"));
        ArrayList<GymHallEntity> hallEntityArrayList = hallService.getAllGymHalls();
        Object columnList[] = {"Id","hallName","openAt","closedAt","Equipments"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        if(hallEntityArrayList != null && !hallEntityArrayList.isEmpty()){
            Object rowData[] = new Object[5];
            for(GymHallEntity hall : hallEntityArrayList){
                rowData[0] = hall.getId();
                rowData[1] = hall.getHallName();
                rowData[2] = hall.getOpenAt();
                rowData[3] = hall.getCloseAt();
                String ids = "";
                for(EquipmentEntity equipmentEntity :  hall.getEquipmentList()){
                    ids = equipmentEntity.getEquipmentName()+","+ids;
                }
                rowData[4] = ids;
                model.addRow(rowData);
            }
            tblHall.setModel(model);
        }
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(DataAccessFactory.getRepository("txt","equipments.txt"));
        ArrayList<EquipmentEntity> equipmentEntityArrayList = equipmentService.getAllEquipments();
        DefaultListModel equipmentListModel = new DefaultListModel();
        for(EquipmentEntity equipmentEntity:equipmentEntityArrayList){
            ComboItem comboItem = new ComboItem(equipmentEntity.getEquipmentName(),Integer.toString(equipmentEntity.getId()));
            equipmentListModel.addElement(comboItem);
        }
        equipmentList.setModel(equipmentListModel);
    }
    private void clearFields(){
        this.hallId = 0;
        txClosedAt.setText("");
        txOpenAt.setText("");
        txHallName.setText("");
    }

    private void btnSaveActionPerformed(ActionEvent e) throws ParseException {
        GymHallServiceImpl hallService = new GymHallServiceImpl(DataAccessFactory.getRepository("txt","halls.txt"));
        GymHallEntity hallEntity;
        if(this.hallId != 0){
            hallEntity = hallService.getGymHallById(this.hallId);
        }else{
            hallEntity = new GymHallEntity();
        }
        hallEntity.setOpenAt(new SimpleDateFormat("dd/MM/yyyy").parse(txOpenAt.getText()));
        hallEntity.setCloseAt(new SimpleDateFormat("dd/MM/yyyy").parse(txClosedAt.getText()));
        List<ComboItem> equipments = equipmentList.getSelectedValuesList();

        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(DataAccessFactory.getRepository("txt","equipments.txt"));
        ArrayList<EquipmentEntity> equipmentListForHall = new ArrayList<>();
        for (ComboItem equipment:equipments){
            equipmentListForHall.add(equipmentService.getEquipmentById(Integer.parseInt(equipment.getValue())));
        }
        hallEntity.setEquipmentList(equipmentListForHall);
        hallEntity.setHallName(txHallName.getText());
        hallService.saveGymHall(hallEntity);
        fillTable();
        clearFields();
    }

    private void tblHallMouseClicked(MouseEvent e) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYY");
        GymHallServiceImpl hallService = new GymHallServiceImpl(DataAccessFactory.getRepository("txt","halls.txt"));
        GymHallEntity hall = hallService.getGymHallById(Integer.parseInt(tblHall.getValueAt(tblHall.getSelectedRow(),0).toString()));
        this.hallId = hall.getId();
        txHallName.setText(hall.getHallName());
        txOpenAt.setText(format.format(hall.getOpenAt()));
        txClosedAt.setText(format.format(hall.getCloseAt()));

        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(DataAccessFactory.getRepository("txt","equipments.txt"));


        ArrayList<EquipmentEntity> allEquipments = equipmentService.getAllEquipments();
        int[] selectedEquipmentIds = new int[20];
        int i=0;
        for(EquipmentEntity currentEquipment:hall.getEquipmentList()){
            selectedEquipmentIds[i] = (currentEquipment.getId()-1);
            i++;
        }
        equipmentList.setSelectedIndices(selectedEquipmentIds);
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        GymHallServiceImpl hallService = new GymHallServiceImpl(DataAccessFactory.getRepository("txt","halls.txt"));
        hallService.deleteGymHall(hallService.getGymHallById(this.hallId));
        fillTable();
        clearFields();
    }

    private void btnClearActionPerformed(ActionEvent e) {
        clearFields();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblHall = new JTable();
        scrollPane2 = new JScrollPane();
        equipmentList = new JList();
        txClosedAt = new JTextField();
        txOpenAt = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();
        txHallName = new JTextField();
        label4 = new JLabel();

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.
            awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder()))
            ;panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;

            //======== scrollPane1 ========
            {

                //---- tblHall ----
                tblHall.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblHallMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblHall);
            }

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(equipmentList);
            }

            //---- label1 ----
            label1.setText("Equipment List");

            //---- label2 ----
            label2.setText("Opened at :");

            //---- label3 ----
            label3.setText("Closed at:");

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> {
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

            //---- label4 ----
            label4.setText("Hall name");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(label1))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(79, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear)
                                .addContainerGap(19, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label2)
                                    .addComponent(label4)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txHallName, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(txOpenAt, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(txClosedAt, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                .addContainerGap(57, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txHallName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txOpenAt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txClosedAt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSave)
                                    .addComponent(btnDelete)
                                    .addComponent(btnClear))))
                        .addContainerGap(16, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblHall;
    private JScrollPane scrollPane2;
    private JList equipmentList;
    private JTextField txClosedAt;
    private JTextField txOpenAt;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JTextField txHallName;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
