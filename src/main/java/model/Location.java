package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @XmlElement(name = "x")
    @NotNull
    private Long x; //Поле не может быть null

    @XmlElement(name = "y")
    @NotNull
    private Integer y; //Поле не может быть null

    @XmlElement(name = "z")
    @NotNull
    private Integer Z; //Поле не может быть null
}
