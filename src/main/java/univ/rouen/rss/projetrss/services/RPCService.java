package univ.rouen.rss.projetrss.services;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

@Service
public class RPCService {

    public boolean valid(File file,String xmlString){
        Source xml=new StreamSource(new StringReader(xmlString));
        SchemaFactory schemaFactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema=schemaFactory.newSchema(file);
            Validator validator=schema.newValidator();
            validator.validate(xml);
            System.out.println("IS VALID");
        } catch (IOException|SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public  void transformXSL(String xml,File html) throws Exception {
        // Création du reader initial
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        XMLReader reader = spf.newSAXParser().getXMLReader();

        // Creation du filtre à appliquer au reader
        SAXTransformerFactory stf = (SAXTransformerFactory) TransformerFactory.newInstance();
        XMLFilter filtre = stf.newXMLFilter(new StreamSource (new File("src/main/resources/xsd/item.xslt")));

        // On "lie" le reader au filtre
        filtre.setParent(reader);

        // Création de la source
        SAXSource source = new SAXSource(filtre, new InputSource(new StringReader(xml)));

        StreamResult resultat = new StreamResult(new FileOutputStream(html));

        // Transformation
        Transformer transformer = stf.newTransformer();
        transformer.transform(source, resultat);
    }

}
