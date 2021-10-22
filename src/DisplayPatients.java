import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DisplayPatients {

    JFrame frameAll;

    public DisplayPatients() throws SQLException {

        frameAll = new JFrame();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem", "root", "root");
        Statement st = connection.createStatement();


        st.execute("select * from patients");
        ResultSet rs = st.getResultSet();

        DefaultTableModel model = new DefaultTableModel(new String[]{"PATIENT ID", "PATIENT NAME", "ADDRESS", "AGE", "SEX", "ILLNESS", "AMOUNT FOR APPOINTMENT", "DATE", "STATUS"}, 0);

        JTable jt = new JTable(model);
        while (rs.next()) {

            int id = rs.getInt("patientID");
            String name = rs.getString("patientName");
            String address = rs.getString("patientAddress");
            int pAge = rs.getInt("age");
            String sex = rs.getString("patientSex");
            String illness = rs.getString("patientIllness");
            String money = rs.getString("amountForAppointment");
            String date = rs.getString("joindate");
            String stat = rs.getString("status");

            model.addRow(new Object[]{String.valueOf(id), name, address, String.valueOf(pAge), sex, illness, money, date, stat});
        }

        jt.setModel(model);
        jt.setBounds(100,100,2000,400);
        JScrollPane sp=new JScrollPane(jt);
        frameAll.add(sp);
        frameAll.setTitle("PATIENTS");
        frameAll.setSize(2000,400);
        frameAll.setVisible(true);

    }

}
