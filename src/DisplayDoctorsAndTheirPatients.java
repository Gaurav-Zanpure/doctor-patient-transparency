import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DisplayDoctorsAndTheirPatients {

    JFrame frameAll;

    public DisplayDoctorsAndTheirPatients() throws SQLException {

        frameAll = new JFrame();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","root");
        Statement st = connection.createStatement();
        Statement st1 = connection.createStatement();

        st.execute("select * from doctors");
        ResultSet rs = st.getResultSet();
        st1.execute("select * from patients");
        ResultSet rs1 = st1.getResultSet();

        DefaultTableModel model = new DefaultTableModel(new String[]{"PATIENT ID", "PATIENT NAME", "DOCTOR ASSOCIATED"}, 0);

        JTable jt = new JTable(model);

        while (rs.next()) {
            int id = rs.getInt("patientID");
            String name = rs.getString("patientName");
            String doctorAssigned = rs.getString("doctorAssigned");

            model.addRow(new Object[]{String.valueOf(id),name,doctorAssigned});
        }

        jt.setModel(model);
        jt.setBounds(100,100,2000,400);
        JScrollPane sp=new JScrollPane(jt);
        frameAll.add(sp);
        frameAll.setTitle("DOCTORS");
        frameAll.setSize(2000,400);
        frameAll.setVisible(true);

    }

}
