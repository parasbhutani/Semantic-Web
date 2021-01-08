
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.jena.propertytable.lang.CSV2RDF;

import java.io.InputStream;

public class CSV2RDF {

    public static void main(String ... strings) throws Exception {
        CSV2RDF.init();
       //load through manager:
        Model m = RDFDataMgr.loadModel("test.csv") ;
        /*classic way to load:
        Model m = ModelFactory.createDefaultModel();
        try (InputStream in = JenaCSVTest.class.getResourceAsStream("/test.csv")) {
            m.read(in, "http://example.com", "csv");
        }*/
        m.setNsPrefix("test", "http://example.com#");
        m.write(System.out, "ttl");
    }

    private static void init() {
    }


}
