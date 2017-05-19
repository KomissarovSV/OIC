package oic.entity;

import lombok.Data;
import oic.entity.tree.GRNTI;

import java.util.Date;
import java.util.List;

@Data
public class OicModal {

    private Long number;
    private String name;
    private Boolean active;
    private Long typeOic;
    private Long typeOsnov;

    private Date priorityDate;
    private Date deliveryDate;
    private Date takeDate;
    private Date duration;

    private String secDocNum;
    private String belNum;
    private Long innerNumZaiv = 0L;
    private String innerDeloNum;
    private String shifr;

    private Boolean hasContracts;
    private Boolean hasRND;
    private Boolean hasFond;

    private Boolean hasBalance;
    private Date postan;
    private Double cost;

    private String balanceNote;
    private String rightNote;
    private String contactNote;
    private String generalNote;
    private String referat;

    private String authors;

    private List<GRNTI> grntiList;
}
