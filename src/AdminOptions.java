import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminOptions extends JFrame {

    JLabel password;
    JPasswordField adminPassword;

    JButton login;
    JPanel loginPanel;

    public  AdminOptions(){

        password = new JLabel("  ADMIN PASSWORD  ");
        adminPassword = new JPasswordField(30);
        login = new JButton("LOG IN");
        loginPanel = new JPanel();

        loginPanel.setLayout(new GridLayout(0, 1));
        loginPanel.add(password);
        loginPanel.add(adminPassword);
        loginPanel.add(login);

        loginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ADMIN LOGIN"));
        setContentPane(loginPanel);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String validPassword = "admin123";
                String checkPassword = new String(adminPassword.getPassword());

                if(validPassword.equals(checkPassword)){

                    JFrame frameAdmin = new JFrame();

                    JRadioButton displayPatients;
                    JRadioButton displayPatientsAndTheirDoctors;
                    JRadioButton patientTreatment;
                    JButton proceed;
                    JButton logOut;

                    displayPatients = new JRadioButton("Display Patients");
                    displayPatientsAndTheirDoctors = new JRadioButton("Display Doctors and their Patients");
                    patientTreatment = new JRadioButton("Treatment of a Patient");

                    proceed = new JButton("Proceed");
                    logOut = new JButton("LOG OUT");

                    ButtonGroup buttonGroup = new ButtonGroup();
                    buttonGroup.add(displayPatients);
                    buttonGroup.add(displayPatientsAndTheirDoctors);
                    buttonGroup.add(patientTreatment);

                    JPanel radioPanel = new JPanel();
                    radioPanel.setLayout(new GridLayout(0,1));
                    radioPanel.add(displayPatients);
                    radioPanel.add(displayPatientsAndTheirDoctors);
                    radioPanel.add(patientTreatment);

                    radioPanel.add(proceed);
                    radioPanel.add(logOut);
                    radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Choose a Option"));

                    frameAdmin.setContentPane(radioPanel);

                    logOut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            frameAdmin.setVisible(false);
                            setVisible(false);

                        }
                    });

                    proceed.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if(displayPatients.isSelected()){

                                try {
                                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","root");

                                    Statement st = connection.createStatement();
                                    st.execute("select * from patients");

                                    ResultSet rs = st.getResultSet();

                                    if(rs.next()) {
                                        DisplayPatients displayAll = new DisplayPatients();
                                    }else{

                                        JOptionPane.showMessageDialog(null, "No patient has been registered, yet.");

                                    }

                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }else if(displayPatientsAndTheirDoctors.isSelected()){

                                try {
                                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","root");

                                    Statement st1 = connection.createStatement();
                                    Statement st2 = connection.createStatement();


                                    st1.execute("select * from doctors");
                                    ResultSet rs1 = st1.getResultSet();
                                    st2.execute("select * from patients");
                                    ResultSet rs2 = st2.getResultSet();

                                    if(rs1.next()) {

                                        DisplayDoctorsAndTheirPatients displayAll = new  DisplayDoctorsAndTheirPatients();

                                    }else if(!rs2.next()){

                                        JOptionPane.showMessageDialog(null, "No patient has been registered, yet.");

                                    }else{

                                        JOptionPane.showMessageDialog(null, "No doctor was assigned to any patient, yet.");

                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            }else if(patientTreatment.isSelected()){
                                try {
                                    PatientTreatment doctorAssignation = new PatientTreatment();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                        }
                    });

                    frameAdmin.setLayout(new GridLayout(0,1));
                    frameAdmin.setTitle("Welcome ADMIN!");
                    frameAdmin.setVisible(true);
                    frameAdmin.setSize(400, 300);
                    frameAdmin.setResizable(false);
                    frameAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                }else{

                    JOptionPane.showMessageDialog(null, "INCORRECT PASSWORD.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    setVisible(false);


                }


            }
        });

        setLayout(new FlowLayout());
        setTitle("LOGIN");
        setVisible(true);
        adminPassword.requestFocusInWindow();
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


}
