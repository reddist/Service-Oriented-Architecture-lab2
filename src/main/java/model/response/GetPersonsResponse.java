package model.response;

import model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetPersonsResponse {

    @XmlElement(name = "page")
    private Integer page;

    @XmlElement(name = "count")
    private Integer count;

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "person")
    private List<Person> persons;
}
