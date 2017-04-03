package oic.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Oic {
    private long number;
    private String name;
    private String docNum;
    private String type;
    private Date priorityDate;
    private Date deliveryDate;
    private String authors;
}
