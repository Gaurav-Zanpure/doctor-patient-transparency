import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AssignADoctor extends JFrame {

    JLabel loginIdLabel;
    JTextField loginIdField;

    JButton login;

    JPanel loginPanel;

    public AssignADoctor() throws SQLException {

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

                JFrame frameDoctors = new JFrame();
                JTextArea textArea1 ;
                JTextArea textArea2 ;
                JButton backtoHomepage;
                String doctorAssigned = null;
                int patientIdToAssignADoctor = Integer.parseInt(loginIdField.getText().trim());

                try {

                    st.executeQuery("select patientID, patientName, patientIllness from patients where patientID = " + patientIdToAssignADoctor + "");
                    ResultSet rs = st.getResultSet();


                    while (rs.next()) {

                        String illness = rs.getString("patientIllness");
                        String patientNametoAssignDoctor = rs.getString("patientName");

                        textArea1 = new JTextArea("\n Patient Name : " + patientNametoAssignDoctor + "\n"
                                +"\nPatient Illness : " + illness + "\n");
                        Font boldFont=new Font(textArea1.getFont().getName(), Font.BOLD, textArea1.getFont().getSize());
                        textArea1.setFont(boldFont);
                        textArea1.setBackground(Color.LIGHT_GRAY);
                        textArea1.setEditable(false);

                        frameDoctors.add(textArea1);

                        if (illness.toLowerCase().contains("heart")) {

                            textArea2 = new JTextArea("\n-------------------------------------Doctor assigned---------------------------------------\n"+
                                                      "    Dr. Jagdish HireMath\n"+
                                                      "    (Consultant) Qualifications :- MD, DM, DNB, FACC\n"+
                                                      "    Ruby Hall Clinic, Pune-411001, Maharashtra\n"+
                                                      "    Specialty : Cardiologist, Intervention Cardialogy\n"+
                                                      "    Sangamwadi - Mon & Saturday 9.00 am to 12.30 pm\n"+
                                                      "-----------------------------------------------------------------------------------------------------\n");


                            frameDoctors.add(textArea2);
                            Font boldFont1=new Font(textArea2.getFont().getName(), Font.BOLD, textArea2.getFont().getSize());
                            textArea2.setFont(boldFont1);
                            textArea2.setBackground(Color.LIGHT_GRAY);
                            textArea2.setEditable(false);
                            doctorAssigned = "Dr. Jagdish Hiremath [Cardiologist, Intervention Cardiology]";

                        }
                        else if (illness.toLowerCase().contains("eye")) {


                            textArea2 = new JTextArea("\n-------------------------------------Doctor assigned---------------------------------------\n"+
                                                      "    Dr. Jai Kelkar\n"+
                                                      "    (Consultant) Qualifications :- D.N.B.,DOMS,FCPS\n"+
                                                      "    National Institute of Ophthalmology, Pune-411005\n"+
                                                      "    Specialty : Ophthalmologist | Eye Surgeon\n"+
                                                      "    ShivajiNagar - Mon & Saturday 9.00 am to 12.30 pm \n"+
                                                      "---------------------------------------------------------------------------------------------------\n");

                            frameDoctors.add(textArea2);
                            Font boldFont2=new Font(textArea2.getFont().getName(), Font.BOLD, textArea2.getFont().getSize());
                            textArea2.setFont(boldFont2);
                            textArea2.setBackground(Color.LIGHT_GRAY);
                            textArea2.setEditable(false);
                            doctorAssigned = "Dr. Jai Kelkar [Ophthalmologist | Eye Surgeon]";

                        }
                        else if (illness.toLowerCase().contains("tooth")) {

                            textArea2 = new JTextArea("\n--------------------------------------Doctor assigned---------------------------------------\n"+
                                                      "   Dr. Sagar Jadhav\n"+
                                                      "   (Consultant) Qualifications :- BDS\n"+
                                                      "   Shree Yash Dental Clinic, Pune-411044\n"+
                                                      "   Specialty : Teeth Reshaping | Artificial Teeth | Dental Braces Fixing\n"+
                                                      "   Nigdi - Mon & Saturday 9.00 am to 12.30 pm \n"+
                                                      "---------------------------------------------------------------------------------------------------\n");

                            frameDoctors.add(textArea2);
                            Font boldFont3=new Font(textArea2.getFont().getName(), Font.BOLD, textArea2.getFont().getSize());
                            textArea2.setFont(boldFont3);
                            textArea2.setBackground(Color.LIGHT_GRAY);
                            textArea2.setEditable(false);
                            doctorAssigned = "Dr. Sagar Jadhav [ Teeth Reshaping | Artificial Teeth | Dental Braces Fixing]";

                        }



                        st1.executeUpdate("update patients set status = 'Doctor Assigned' where patientId = " + patientIdToAssignADoctor + "");
                        st2.executeUpdate("insert into doctors value ('" + patientIdToAssignADoctor + "','" + patientNametoAssignDoctor + "','" + doctorAssigned + "')");



                        backtoHomepage = new JButton("CLOSE");
                        frameDoctors.add(backtoHomepage);
                        JOptionPane.showMessageDialog(null,patientNametoAssignDoctor + " has been assigned a doctor.");


                        backtoHomepage.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                frameDoctors.setVisible(false);
                                setVisible(false);

                            }
                        });


                    }
                    rs.close();
                    st.close();
                    st1.close();
                    st2.close();
                    frameDoctors.setLayout(new FlowLayout());
                    frameDoctors.setTitle("Doctor Assigned");
                    frameDoctors.setVisible(true);
                    frameDoctors.setSize(400, 300);
                    frameDoctors.setResizable(false);
                    frameDoctors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
