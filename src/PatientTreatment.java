import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PatientTreatment extends JFrame {

    JLabel loginIdLabel;
    JTextField loginIdField;

    JButton login;

    JPanel loginPanel;

    JLabel patientCheck;
    JCheckBox ongoingTreatment;
    JCheckBox dischargedSoon;
    JCheckBox cured;

    public PatientTreatment() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","root");
        Statement st = connection.createStatement();
        Statement st1 = connection.createStatement();
        Statement st2 = connection.createStatement();
        String treatmentOfPatient = null;

        loginIdLabel = new JLabel("  Patient ID   ");
        loginIdField = new JTextField("                                               ",30);


        login = new JButton("LOG IN");
        loginPanel = new JPanel();

        loginPanel.setLayout(new GridLayout(0, 1));
        loginPanel.add(loginIdLabel);
        loginPanel.add(loginIdField);
        loginPanel.add(login);

        loginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "PATIENT LOGIN"));
        setContentPane(loginPanel);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frameTreatment = new JFrame();
                JTextArea textArea1 ;
                JTextArea textArea2 ;
                JButton save;
                JButton close;
                int patientIdToTreatHim = Integer.parseInt(loginIdField.getText().trim());

                try {

                    st.executeQuery("select patientID,patientName,status from patients where patientID = " + patientIdToTreatHim + "");
                    ResultSet rs = st.getResultSet();

                    while (rs.next()) {

                        String patientNameToCheckTreatment = rs.getString("patientName");
                        String currentStatus = rs.getString("status");

                        if (currentStatus.toLowerCase().contains("new")) {

                            textArea1 = new JTextArea("\nStatus: " + currentStatus + "\n");
                            frameTreatment.add(textArea1);
                            Font boldFont = new Font(textArea1.getFont().getName(), Font.BOLD, textArea1.getFont().getSize());
                            textArea1.setFont(boldFont);
                            textArea1.setBackground(Color.LIGHT_GRAY);
                            textArea1.setEditable(false);


                        } else {

                            textArea1 = new JTextArea("\nLast Checked: " + currentStatus + "\n");
                            frameTreatment.add(textArea1);
                            Font boldFont = new Font(textArea1.getFont().getName(), Font.BOLD, textArea1.getFont().getSize());
                            textArea1.setFont(boldFont);
                            textArea1.setBackground(Color.LIGHT_GRAY);
                            textArea1.setEditable(false);

                        }

                        if (currentStatus.toLowerCase().contains("cured")) {

                            close = new JButton("CLOSE");
                            frameTreatment.add(close);
                            JOptionPane.showMessageDialog(null, patientNameToCheckTreatment + "  has been already given a discharge.");


                            close.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    frameTreatment.setVisible(false);
                                    setVisible(false);

                                }
                            });

                        } else {

                            textArea2 = new JTextArea("\n-----------------------------------------------------------------------------------------------------\n");
                            frameTreatment.add(textArea2);
                            Font boldFont1 = new Font(textArea2.getFont().getName(), Font.BOLD, textArea2.getFont().getSize());
                            textArea2.setFont(boldFont1);
                            textArea2.setBackground(Color.LIGHT_GRAY);
                            textArea2.setEditable(false);
                            patientCheck = new JLabel("How is patient holding up? (FURTHER INSTRUCTIONS)\n");
                            ongoingTreatment = new JCheckBox("Under Observation/ Needs an ICU");
                            dischargedSoon = new JCheckBox("Must Follow Strict Dietary Plans");
                            cured = new JCheckBox("is Absolutely Fine");

                            ButtonGroup buttonGroup = new ButtonGroup();
                            buttonGroup.add(ongoingTreatment);
                            buttonGroup.add(dischargedSoon);
                            buttonGroup.add(cured);

                            frameTreatment.add(patientCheck);

                            frameTreatment.add(ongoingTreatment);
                            frameTreatment.add(dischargedSoon);
                            frameTreatment.add(cured);



                            save = new JButton("SAVE");
                            frameTreatment.add(save);

                            save.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                        if (ongoingTreatment.isSelected()) {

                                            try {
                                                st1.executeUpdate("update patients set status = 'Ongoing Treatment' where patientId = " + patientIdToTreatHim + "");
                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            }

                                        } else if (dischargedSoon.isSelected()) {

                                            try {
                                                st1.executeUpdate("update patients set status = 'Discharged Soon' where patientId = " + patientIdToTreatHim + "");
                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            }

                                        } else if (cured.isSelected()) {

                                            try {
                                                st1.executeUpdate("update patients set status = 'Cured and Discharged' where patientId = " + patientIdToTreatHim + "");
                                                st2.executeUpdate("delete from doctors where patientId = " + patientIdToTreatHim);
                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            }

                                        }
                                        JOptionPane.showMessageDialog(null, patientNameToCheckTreatment + " 's record has been updated.");

                                        frameTreatment.setVisible(false);
                                        setVisible(false);
                                }
                            });

                        }

                    }

                        frameTreatment.setLayout(new FlowLayout());
                        frameTreatment.setTitle("Treatment");
                        frameTreatment.setVisible(true);
                        frameTreatment.setSize(400, 300);
                        frameTreatment.setResizable(false);
                        frameTreatment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } catch(SQLException throwables){
                        throwables.printStackTrace();
                    }

            }
        });

        setLayout(new FlowLayout());
        setTitle("LOGIN");
        setVisible(true);
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
