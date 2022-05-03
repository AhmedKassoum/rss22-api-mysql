package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "image")
@XmlAccessorType(XmlAccessType.PROPERTY)
@ToString
@Entity
@Getter
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute(name = "alt")
    @Column(columnDefinition = "TEXT")
    private String alt;

    @XmlAttribute(name = "href")
    @Column(columnDefinition = "TEXT")
    private String href;

    @XmlAttribute(name = "length")
    private int lenght;
    @XmlAttribute(name = "type")
    String type;

}
