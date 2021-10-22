import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateIllness extends JFrame {

    JLabel loginIdLabel;
    JTextField loginIdField;

    JButton login;

    JPanel loginPanel;

    public UpdateIllness() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","root");
        Statement st = connection.createStatement();
        Statement st1 = connection.createStatement();
        Statement st2 = connection.createStatement();

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

                JFrame frameUpdateIllness = new JFrame();
                int patientIdToUpdateIllness = Integer.parseInt(loginIdField.getText().trim());

                try {
                    st.executeQuery("select patientID, patientName, patientIllness, status from patients where patientID = " + patientIdToUpdateIllness + "");
                    ResultSet rs = st.getResultSet();

                    while (rs.next()) {

                        String illness = rs.getString("patientIllness");
                        String patientNametoUpdateIllness = rs.getString("patientName");
                        String currentStatus = rs.getString("status");

                        if(currentStatus.equals("Cured and Discharged")){

                            JLabel illnessLabel;
                            JTextField illnessField;
                            JButton update;

                            illnessLabel = new JLabel("ILLNESS    ");
                            illnessField = new JTextField(30);
                            update = new JButton("UPDATE");

                            frameUpdateIllness.add(illnessLabel);
                            frameUpdateIllness.add(illnessField);
                            frameUpdateIllness.add(update);

                            update.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    try {
                                        st1.executeUpdate("update patients set patientIllness =' " + illnessField.getText() + " 'where patientId = " + patientIdToUpdateIllness + "");
                                        st2.executeUpdate("update patients set status = 'No Doctor Assigned' where patientId = " + patientIdToUpdateIllness + "");

                                        JOptionPane.showMessageDialog(null, patientNametoUpdateIllness + " 's illness has been updated.");

                                        frameUpdateIllness.setVisible(false);
                                        setVisible(false);

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }


                                }
                            });



                            frameUpdateIllness.setLayout(new FlowLayout());
                            frameUpdateIllness.setTitle("Update Illness");
                            frameUpdateIllness.setVisible(true);
                            frameUpdateIllness.setSize(400, 300);
                            frameUpdateIllness.setResizable(false);
                            frameUpdateIllness.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }else{

                            JOptionPane.showMessageDialog(null, patientNametoUpdateIllness + " 's treatment of previous illness is still pending.");
                            frameUpdateIllness.setVisible(false);
                            setVisible(false);

                        }

                    }




                } catch (SQLException throwables) {
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
