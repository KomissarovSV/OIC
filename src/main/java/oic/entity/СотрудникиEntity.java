package oic.entity;

import java.util.Date;

public class СотрудникиEntity {
    private int idСотрудника;
    private String фамилия;
    private String имя;
    private String отчество;
    private Byte idЗвания;
    private Byte idСтепени;
    private Date датаРождения;
    private String телефонМобильный;
    private String телефонДомашний;
    private String eMailЛичный;
    private String примечания;
    private Integer шифрЛэти;
    private String фио;

    public int getIdСотрудника() {
        return idСотрудника;
    }

    public void setIdСотрудника(int idСотрудника) {
        this.idСотрудника = idСотрудника;
    }

    public String getФамилия() {
        return фамилия;
    }

    public void setФамилия(String фамилия) {
        this.фамилия = фамилия;
    }

    public String getИмя() {
        return имя;
    }

    public void setИмя(String имя) {
        this.имя = имя;
    }

    public String getОтчество() {
        return отчество;
    }

    public void setОтчество(String отчество) {
        this.отчество = отчество;
    }

    public Byte getIdЗвания() {
        return idЗвания;
    }

    public void setIdЗвания(Byte idЗвания) {
        this.idЗвания = idЗвания;
    }

    public Byte getIdСтепени() {
        return idСтепени;
    }

    public void setIdСтепени(Byte idСтепени) {
        this.idСтепени = idСтепени;
    }

    public Date getДатаРождения() {
        return датаРождения;
    }

    public void setДатаРождения(Date датаРождения) {
        this.датаРождения = датаРождения;
    }

    public String getТелефонМобильный() {
        return телефонМобильный;
    }

    public void setТелефонМобильный(String телефонМобильный) {
        this.телефонМобильный = телефонМобильный;
    }

    public String getТелефонДомашний() {
        return телефонДомашний;
    }

    public void setТелефонДомашний(String телефонДомашний) {
        this.телефонДомашний = телефонДомашний;
    }

    public String geteMailЛичный() {
        return eMailЛичный;
    }

    public void seteMailЛичный(String eMailЛичный) {
        this.eMailЛичный = eMailЛичный;
    }

    public String getПримечания() {
        return примечания;
    }

    public void setПримечания(String примечания) {
        this.примечания = примечания;
    }

    public Integer getШифрЛэти() {
        return шифрЛэти;
    }

    public void setШифрЛэти(Integer шифрЛэти) {
        this.шифрЛэти = шифрЛэти;
    }

    public String getФио() {
        return фио;
    }

    public void setФио(String фио) {
        this.фио = фио;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        СотрудникиEntity that = (СотрудникиEntity) o;

        if (idСотрудника != that.idСотрудника) return false;
        if (фамилия != null ? !фамилия.equals(that.фамилия) : that.фамилия != null) return false;
        if (имя != null ? !имя.equals(that.имя) : that.имя != null) return false;
        if (отчество != null ? !отчество.equals(that.отчество) : that.отчество != null) return false;
        if (idЗвания != null ? !idЗвания.equals(that.idЗвания) : that.idЗвания != null) return false;
        if (idСтепени != null ? !idСтепени.equals(that.idСтепени) : that.idСтепени != null) return false;
        if (датаРождения != null ? !датаРождения.equals(that.датаРождения) : that.датаРождения != null) return false;
        if (телефонМобильный != null ? !телефонМобильный.equals(that.телефонМобильный) : that.телефонМобильный != null)
            return false;
        if (телефонДомашний != null ? !телефонДомашний.equals(that.телефонДомашний) : that.телефонДомашний != null)
            return false;
        if (eMailЛичный != null ? !eMailЛичный.equals(that.eMailЛичный) : that.eMailЛичный != null) return false;
        if (примечания != null ? !примечания.equals(that.примечания) : that.примечания != null) return false;
        if (шифрЛэти != null ? !шифрЛэти.equals(that.шифрЛэти) : that.шифрЛэти != null) return false;
        if (фио != null ? !фио.equals(that.фио) : that.фио != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idСотрудника;
        result = 31 * result + (фамилия != null ? фамилия.hashCode() : 0);
        result = 31 * result + (имя != null ? имя.hashCode() : 0);
        result = 31 * result + (отчество != null ? отчество.hashCode() : 0);
        result = 31 * result + (idЗвания != null ? idЗвания.hashCode() : 0);
        result = 31 * result + (idСтепени != null ? idСтепени.hashCode() : 0);
        result = 31 * result + (датаРождения != null ? датаРождения.hashCode() : 0);
        result = 31 * result + (телефонМобильный != null ? телефонМобильный.hashCode() : 0);
        result = 31 * result + (телефонДомашний != null ? телефонДомашний.hashCode() : 0);
        result = 31 * result + (eMailЛичный != null ? eMailЛичный.hashCode() : 0);
        result = 31 * result + (примечания != null ? примечания.hashCode() : 0);
        result = 31 * result + (шифрЛэти != null ? шифрЛэти.hashCode() : 0);
        result = 31 * result + (фио != null ? фио.hashCode() : 0);
        return result;
    }
}
