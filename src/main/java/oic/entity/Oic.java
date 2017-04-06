package oic.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties
public class Oic {
    private long number;
    private String name;
    private String docNum;
    private String type;
    private Date priorityDate;
    private Date deliveryDate;
    private String authors;
    private String department;
}
