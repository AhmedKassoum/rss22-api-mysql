package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement(name = "guid")
    private String guid;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "category")
    @OneToOne
    private Category category;
    @XmlElement(name = "published")
    private Date published;
    @XmlElement(name = "image")
    @OneToOne
    private Image image;
    @XmlElement(name = "content")
    @OneToOne
    private Content content;
    @XmlElement(name = "author")
    @OneToOne
    private Author author;

    @Override
    public String toString() {
        return "Item{" +
                "guid='" + guid + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", published=" + published +
                ", image=" + image.toString() +
                ", content='" + content.toString() + '\'' +
                ", author=" + author.toString() +
                '}';
    }
}
