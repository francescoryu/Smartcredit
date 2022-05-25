package ch.bzz.smartcredit.model;
import java.util.List;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 3.0
 */

public class Kunde {

    private String kunde;
    private String kundeUUID;
    private String vorName;
    private String nachName;
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
