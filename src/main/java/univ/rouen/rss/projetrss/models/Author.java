package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "email")
    private String mail;
    @XmlElement(name = "uri")
    @Column(columnDefinition = "TEXT")
    private String uri;

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
