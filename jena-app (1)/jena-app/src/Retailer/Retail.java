package Retailer;

import codebinding.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Retail extends JFrame{
    public JButton browseButton;
    public JPanel panel2;
    public JTextField textField1;
    private JButton uploadButton;
    private JButton customerButton;
    private JButton retailerButton;


    public Retail() {
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f =chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                textField1.setText(filename);
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = new File("/Users/shubhambaranwal/Desktop/Retailer/laptops.csv");
                boolean success = file.renameTo(new File("/Users/shubhambaranwal/Desktop/CSV2RDF-master/target/laptop.csv"));

            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App app = new App();
                app.setVisible(true);
            }
        });

        retailerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Retail");
        frame.setContentPane(new Retail().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.show();
    }
    public void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
