package univ.rouen.rss.projetrss.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@ToString
public class Content implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlAttribute(name = "href")
    @Column(columnDefinition = "TEXT")
    private String href;
    @XmlAttribute(name = "type")
    private String type;
    @XmlValue
    @Column(columnDefinition = "TEXT")
    private String content;
}
