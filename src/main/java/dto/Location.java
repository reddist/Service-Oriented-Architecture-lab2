package dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class Location {
    @NotNull
    private Long x; //Поле не может быть null

    @NotNull
    private Integer y; //Поле не может быть null

    @NotNull
    private Integer Z; //Поле не может быть null
}
