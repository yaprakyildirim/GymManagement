/*
 * Created by JFormDesigner on Thu Dec 26 10:58:25 EET 2019
 */

package front.trainer;

import java.awt.event.*;
import Entity.EquipmentEntity;
import Entity.ExercisePlanEntity;
import Entity.TrainerEntity;
import Factory.DataAccessFactory;
import Service.Impl.EquipmentServiceImpl;
import Service.Impl.ExercisePlanServiceImpl;
import Service.Impl.TrainerServiceImpl;
import front.ComboItem;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unknown
 */
public class exerciseForm extends JPanel {
    public exerciseForm() {
        initComponents();
    }
    private int exerciseId = 0;

    private void clearFields(){
        txDuration.setText("");
        equipmentList.setSelectedIndex(0);
        txName.setText("");
        this.exerciseId = 0;
    }
    private void btnSaveActionPerformed(ActionEvent e) {
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        ExercisePlanEntity plan = null;
        if(this.exerciseId != 0){
            plan = exercisePlanService.getExercisePlanById(this.exerciseId);
        }else{
            plan = new ExercisePlanEntity();
        }
        plan.setDuration(txDuration.getText());
        List<ComboItem> equipments = equipmentList.getSelectedValuesList();
        String equipmentIds = "";
        for(ComboItem equipment:equipments){
            if(equipmentIds.equals("")){
                equipmentIds = equipment.getValue();
            }else{
                equipmentIds = equipment.getValue()+","+equipmentIds;
            }
        }
        plan.setEquipmentId(equipmentIds.toString());
        plan.setTrainerId(cmbTrainer.getSelectedIndex()+1);
        plan.setExerciseName(txName.getText());
        exercisePlanService.saveExercisePlan(plan);
        fillTable();

    }

    private void scrollPane1MouseClicked(MouseEvent e) {

    }

    private void tblExerciseMouseClicked(MouseEvent e) {
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        ExercisePlanEntity exercisePlanEntity = exercisePlanService.getExercisePlanById(Integer.parseInt(tblExercise.getValueAt(tblExercise.getSelectedRow(),0).toString()));
        this.exerciseId = exercisePlanEntity.getId();
        txDuration.setText(exercisePlanEntity.getDuration());
        cmbTrainer.setSelectedIndex(exercisePlanEntity.getTrainerId()-1);
        int[] equipmentIndex = new int[10];
        int i=0;
        for(String id : exercisePlanEntity.getEquipmentId().split(",")){
            equipmentIndex[i] = Integer.parseInt(id);
        }
        equipmentList.setSelectedIndices(equipmentIndex);
        txName.setText(exercisePlanEntity.getExerciseName());
    }

    private void btnDeleteMouseClicked(MouseEvent e) {
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        exercisePlanService.deleteExercisePlan(exercisePlanService.getExercisePlanById(this.exerciseId));
        clearFields();
        fillTable();
    }

    private void btnClearMouseClicked(MouseEvent e) {
        clearFields();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblExercise = new JTable();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        cmbTrainer = new JComboBox();
        scrollPane2 = new JScrollPane();
        equipmentList = new JList();
        txDuration = new JTextField();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();
        label4 = new JLabel();
        txName = new JTextField();

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font
            .BOLD,12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r"
            .equals(e.getPropertyName()))throw new RuntimeException();}});

            //======== scrollPane1 ========
            {
                scrollPane1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        scrollPane1MouseClicked(e);
                    }
                });

                //---- tblExercise ----
                tblExercise.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblExerciseMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblExercise);
            }

            //---- label1 ----
            label1.setText("Trainer");

            //---- label2 ----
            label2.setText("Equipments");

            //---- label3 ----
            label3.setText("Duration");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(equipmentList);
            }

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> btnSaveActionPerformed(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteMouseClicked(e);
                }
            });

            //---- btnClear ----
            btnClear.setText("Clear");
            btnClear.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnClearMouseClicked(e);
                }
            });

            //---- label4 ----
            label4.setText("Ex. Name");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label2)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbTrainer, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                            .addComponent(txDuration, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txName, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btnSave)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDelete))
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(546, Short.MAX_VALUE)
                        .addComponent(btnClear)
                        .addGap(110, 110, 110))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(cmbTrainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(txDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(label2)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDelete)
                                    .addComponent(btnSave)))
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(btnClear)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(DataAccessFactory.getRepository("txt","equipments.txt"));
        ArrayList<EquipmentEntity> equipmentEntityArrayList = equipmentService.getAllEquipments();
        DefaultListModel equipmentListModel = new DefaultListModel();
        for(EquipmentEntity equipmentEntity:equipmentEntityArrayList){
            ComboItem comboItem = new ComboItem(equipmentEntity.getEquipmentName(),Integer.toString(equipmentEntity.getId()));
            equipmentListModel.addElement(comboItem);
        }
        equipmentList.setModel(equipmentListModel);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblExercise;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox cmbTrainer;
    private JScrollPane scrollPane2;
    private JList equipmentList;
    private JTextField txDuration;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel label4;
    private JTextField txName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public void fillTable(){
        TrainerServiceImpl trainerService = new TrainerServiceImpl(DataAccessFactory.getRepository("txt","trainers.txt"));
        ArrayList<TrainerEntity> trainerEntities = trainerService.getAllTrainers();
        for(TrainerEntity trainerEntity : trainerEntities){
            cmbTrainer.addItem(new ComboItem(trainerEntity.getName()+" "+trainerEntity.getSurname(),String.valueOf(trainerEntity.getId())));
        }
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(DataAccessFactory.getRepository("txt","equipments.txt"));
        ExercisePlanServiceImpl exercisePlanService = new ExercisePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        ArrayList<ExercisePlanEntity> exercisePlanEntities = exercisePlanService.getAllExercisePlans();
        Object columnList[] = {"Id","Trainer","Equipments","Duration"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        if(exercisePlanEntities != null && !exercisePlanEntities.isEmpty()){
            Object rowData[] = new Object[4];
            for(ExercisePlanEntity exercisePlanEntity:exercisePlanEntities){
                rowData[0] = exercisePlanEntity.getId();
                rowData[1] = trainerService.getTrainerById(exercisePlanEntity.getTrainerId()).getName();
                String equipmentNames = "";
                for (String equipmentId:exercisePlanEntity.getEquipmentId().split(",")){
                    if(equipmentNames.equals("")){
                        equipmentNames = equipmentService.getEquipmentById(Integer.parseInt(equipmentId)).getEquipmentName();
                    }else{
                        equipmentNames = equipmentService.getEquipmentById(Integer.parseInt(equipmentId)).getEquipmentName() + equipmentNames;
                    }
                }
                rowData[2] = equipmentNames;
                rowData[3] = exercisePlanEntity.getDuration();
                model.addRow(rowData);
            }
        }
        tblExercise.setModel(model);

    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
