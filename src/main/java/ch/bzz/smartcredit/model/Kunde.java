package ch.bzz.smartcredit.model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 3.0
 */

public class Kunde {

    private String kunde;
    private String kundeUUID;

    @NotEmpty
    @FormParam("vorName")
    @Size(min = 2, max = 40)
    private String vorName;

    @NotEmpty
    @FormParam("nachName")
    @Size(min = 2, max = 50)
    private String nachName;

    @FormParam("alter")
    @Min(value = 7)
    @Max(value = 999)
    private Integer alter;




    //-------------Getter und Setter----------------//
    public String getKundeUUID() {
        return kundeUUID;
    }

    public String getVorName() {
        return vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public Integer getAlter() {
        return alter;
    }

    public void setKundeUUID(String kundeUUID) {
        this.kundeUUID = kundeUUID;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public void setAlter(Integer alter) {
        this.alter = alter;
    }

    public String getKunde() {
        return kunde;
    }

    public void setKunde(String kunde) {
        this.kunde = kunde;
    }
}
