package oic.entity;

import lombok.Data;

@Data
public class OicType {
    private Long typeId;
    private String name;
    private long categoryId;
    private String shortName;
}
