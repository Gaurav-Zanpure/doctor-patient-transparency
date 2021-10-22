import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class HOMEPAGE {

    public static void main(String[] args) {

        HospitalHomePage homepage = new HospitalHomePage();

    }

}
 class HospitalHomePage extends JFrame {


    JTextArea header;
    JRadioButton loginAsAAdmin;
    JRadioButton loginAsAUser;
    JButton proceed;


    public HospitalHomePage(){


        header = new JTextArea("\n                           HOSPITAL MANAGEMENT SYSTEM");
        loginAsAAdmin = new JRadioButton("LOGIN AS A ADMIN");
        loginAsAUser = new JRadioButton("LOGIN AS A USER");


        proceed = new JButton("Proceed");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(loginAsAAdmin);
        buttonGroup.add(loginAsAUser);


        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0,1));
        radioPanel.add(header);
        Font boldFont = new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize());
        header.setFont(boldFont);
        header.setBackground(Color.pink);
        header.setEditable(false);
        radioPanel.add(loginAsAAdmin);
        radioPanel.add(loginAsAUser);
        radioPanel.add(proceed);
        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"WELCOME !"));

        setContentPane(radioPanel);

        proceed.addActionListener(e -> {

            if(loginAsAAdmin.isSelected()){

                AdminOptions adminOptions = new AdminOptions();

            }else if(loginAsAUser.isSelected()) {

                UserOptions userOptions = new UserOptions();

            }



        });

        setTitle("HOSPITAL MANAGEMENT SYSTEM");
        setVisible(true);
        setSize(400,300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}


