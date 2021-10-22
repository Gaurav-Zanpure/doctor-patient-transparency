import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserOptions extends JFrame{

    JRadioButton registerAPatient;
    JRadioButton assignADoctor;
    JRadioButton updateIllness;
    JButton proceed;
    JButton logOut;


    public UserOptions(){


        registerAPatient = new JRadioButton("Register a Patient");
        assignADoctor = new JRadioButton("Assign a Doctor to a Patient");
        updateIllness = new JRadioButton("Update the Illness of a Patient");

        proceed = new JButton("Proceed");
        logOut = new JButton("LOG OUT");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(registerAPatient);
        buttonGroup.add(assignADoctor);
        buttonGroup.add(updateIllness);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0,1));
        radioPanel.add(registerAPatient);
        radioPanel.add(assignADoctor);
        radioPanel.add(updateIllness);
        radioPanel.add(proceed);
        radioPanel.add(logOut);
        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Choose a Option"));

        setContentPane(radioPanel);

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

            }
        });

        proceed.addActionListener(e -> {

          if(registerAPatient.isSelected()){
              try {
                  RegisterAPatient patientRegistration = new RegisterAPatient();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }else if(assignADoctor.isSelected()){
              try {
                  AssignADoctor doctorAssignation = new AssignADoctor();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }else if(updateIllness.isSelected()){
              try {
                  UpdateIllness illnessUpdate = new UpdateIllness();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }

        });

        setTitle("Welcome USER!");
        setVisible(true);
        setSize(400,300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}