package codebinding;
import Retailer.Retail;
import codebinding.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.jena.graph.compose.Union;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Filter;
import org.apache.thrift.TUnion;

import java.io.FilterReader;
import java.util.Scanner;

public class App extends JFrame {

    String selectedValue,selectedValue1;
    private static final Logger logger = LogManager.getLogger(App.class);

    public JPanel panel1;
    public JComboBox comboBox1;
    public JButton button1;
    public JComboBox comboBox2;
    public JTextField textField2;
    public JTextField textField3;
    private JLabel Label1;
    private JLabel Label2;
    private JButton retailerButton;


    public void sparqlTest() {
        int i=0;
        Scanner sc =new Scanner(System.in);
        String search,search1;
        System.out.println("Enter a value you want to search");
        search  = selectedValue;
        search1 = selectedValue1;
        System.out.println(search);
        System.out.println(search1);


        FileManager.get().addLocatorClassLoader(App.class.getClassLoader());
        Model model = org.apache.jena.util.FileManager.get().loadModel("/Users/shubhambaranwal/IdeaProjects/jena-app/src/data.rdf");



        String queryString =
                "PREFIX my:<http://purl.org/laptop-sa#>"+
                        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                        "SELECT ?x WHERE {" +
                        "?Laptop my:RAM \""+ search +"\"^^xsd:string." +
                        "?Laptop my:Processor \""+ search1 +"\"^^xsd:string." +
                        "?Laptop my:Name ?x ."+
                        "}";


        Query query = QueryFactory.create(queryString);
        QueryExecution qexec  = QueryExecutionFactory.create(query,model);

        try {

            ResultSet results = qexec.execSelect();
            while(results.hasNext() )
            {
                QuerySolution soln = results.nextSolution();
                Literal name = soln.getLiteral("x");
                //Literal name1 = soln.getLiteral("y");
                System.out.println(name);
                Literal S = model.createTypedLiteral(name);
                String S1 = S.getString();
                //textField1.setText(S1);
                i++;
                if(i==1) {
                    Label1.setText(S1);
                }
                if(i==2){
                    Label2.setText(S1);
                }

                //System.out.println(name1);
            }
            if(i==0){
                Label1.setText("Product currently not available");
                Label2.setText("Please select any other specification");
            }


        }finally
        {
            qexec.close();
        }

    }


    public App() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GET SELECTED VALUE FROM THE COMBO BOX

                selectedValue = comboBox1.getSelectedItem().toString();

                // now set this selected item into the textfield
                textField2.setText(selectedValue);
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GET SELECTED VALUE FROM THE COMBO BOX

                selectedValue1 = comboBox2.getSelectedItem().toString();

                // now set this selected item into the textfield
                textField3.setText(selectedValue1);

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Label1.setText("");
                Label2.setText("");
                sparqlTest();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.show();
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
