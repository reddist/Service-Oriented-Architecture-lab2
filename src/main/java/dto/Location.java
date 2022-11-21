package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Location {
    @NotNull
    private Long x; //Поле не может быть null

    @NotNull
    private Integer y; //Поле не может быть null

    @NotNull
    private Integer Z; //Поле не может быть null
}
