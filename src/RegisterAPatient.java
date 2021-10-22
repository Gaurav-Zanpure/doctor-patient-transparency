import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterAPatient extends JFrame{


    int patientID;
    String patientSex ;
    String joindate;

    JLabel nameLabel;
    JTextField nameField;

    JLabel addressLabel;
    JTextField addressField;

    JLabel illnessLabel;
    JTextField illnessField;

    JLabel statusLabel;
    JTextField statusField;

    JLabel  ageLabel;
    JTextField ageField;

    JLabel sexLabel;
    JRadioButton maleButton;
    JRadioButton femaleButton;
    JRadioButton othersButton;

    JLabel amountLabel;
    JTextField amountField;

    JButton register;

    JPanel registerPanel;

    public static int generatePatientId() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem", "root", "root");
        Statement st = connection.createStatement();


        st.execute("select patientID from patients " +
                "order by patientID desc" +
                " limit 1");
        ResultSet rs = st.getResultSet();
        int latestID;

        if(!rs.next()){


            return latestID = 1;


        }else {
            latestID = rs.getInt("patientID");
            return ++latestID;
        }
    }
    public RegisterAPatient() throws SQLException {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem", "root", "root");
            Statement st1 = connection.createStatement();



            nameLabel = new JLabel("Name    ");
            nameField = new JTextField(30);

            addressLabel = new JLabel("Address");
            addressField = new JTextField(30);

            illnessLabel = new JLabel("Illness  ");
            illnessField = new JTextField(30);

            statusLabel = new JLabel("Status   ");
            statusField = new JTextField("-----------------------------------NEW----------------------------------", 30);

            ageLabel = new JLabel("  Age(in years)");
            ageField = new JTextField(26);

            sexLabel = new JLabel("Sex                        ");
            maleButton = new JRadioButton("Male  ");
            femaleButton = new JRadioButton("Female");
            othersButton = new JRadioButton("Other");
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(maleButton);
            buttonGroup.add(femaleButton);
            buttonGroup.add(othersButton);

            amountLabel = new JLabel("Amount For Appointment");
            amountField = new JTextField(20);

            register = new JButton("REGISTER");

            registerPanel = new JPanel();


            registerPanel.setLayout(new GridLayout(0, 1));
            registerPanel.add(nameLabel);
            registerPanel.add(nameField);
            registerPanel.add(addressLabel);
            registerPanel.add(addressField);
            registerPanel.add(illnessLabel);
            registerPanel.add(illnessField);
            registerPanel.add(statusLabel);
            registerPanel.add(statusField);
            registerPanel.add(ageLabel);
            registerPanel.add(ageField);
            registerPanel.add(sexLabel);
            registerPanel.add(maleButton);
            registerPanel.add(femaleButton);
            registerPanel.add(othersButton);



            registerPanel.add(amountLabel);
            registerPanel.add(amountField);
            registerPanel.add(register);

            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
            joindate = formatter.format(currentDate.getTime());
            registerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Patient Information"));
            setContentPane(registerPanel);

            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (maleButton.isSelected()) {
                        patientSex = "M";
                    } else if (femaleButton.isSelected()) {
                        patientSex = "F";
                    } else if (othersButton.isSelected()) {
                        patientSex = "O";
                    }

                    try {

                        patientID = generatePatientId();
                        int age = Integer.parseInt(ageField.getText().trim());
                        String statusDuringRegisteration = "New";

                        st1.executeUpdate("insert into patients value ('" + patientID + "','" + nameField.getText() + "','" + addressField.getText() + "','" + age + "','" + patientSex + "','" + illnessField.getText() + "','" + amountField.getText() + "','" + joindate + "','" + statusDuringRegisteration+ "')");

                        JOptionPane.showMessageDialog(null,"Hi, "+ nameField.getText() + ". Registration Successful.\n" + "Patient ID = "+ patientID);

                        setVisible(false);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });



            setLayout(new FlowLayout());
            setTitle("REGISTRATION");
            setVisible(true);
            setSize(400, 300);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
