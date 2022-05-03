package univ.rouen.rss.projetrss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.rouen.rss.projetrss.models.Author;
import univ.rouen.rss.projetrss.models.Item;
import univ.rouen.rss.projetrss.repos.*;
import univ.rouen.rss.projetrss.services.RPCService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.util.List;

@RestController
public class ItemController {
    private static File file=new File("src/main/resources/xsd/item.xsd");
    @Autowired
    private ItemRepos itemRepos;

    @Autowired
    private AuthorRepos authorRepos;
    @Autowired
    private CategoryRepos categoryRepos;
    @Autowired
    private ImageRepos imageRepos;
    @Autowired
    private ContentRepos contentRepos;

    @Autowired
    private RPCService service;

    @GetMapping(value = "/rss/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public List<Item> getXMLItems(){
        return itemRepos.findAll();
    }

    @GetMapping(value = "/rss/resume/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public Item getXMLItemsByGuid(@PathVariable("guid") String guid){
        return itemRepos.findItemByGuid(guid);
    }

    @PostMapping(value = "/rss/insert")
    public ResponseEntity<Item> addXMLItem(@RequestBody String flux) throws Exception{

        String out=flux.replaceAll("rss:","");

        if(service.valid(file,out)){

            JAXBContext jaxbContext=JAXBContext.newInstance(Item.class);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();

            StreamSource streamSource=new StreamSource(new StringReader(out));
            JAXBElement<Item> jaxbElement=unmarshaller.unmarshal(streamSource,Item.class);

            Item item=(Item) jaxbElement.getValue();
            System.out.println(item);

            imageRepos.save(item.getImage());
            categoryRepos.save(item.getCategory());
            contentRepos.save(item.getContent());
            authorRepos.save(item.getAuthor());
            //return new ResponseEntity("status:OK",HttpStatus.CREATED);
            return new ResponseEntity<>(itemRepos.save(item), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("status:error",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("guid")String guid){
        System.out.println(guid);
        itemRepos.deleteItemByGuid(guid);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
}
