package ch.bzz.smartcredit.data;

import ch.bzz.smartcredit.model.KKarte;
import ch.bzz.smartcredit.model.Kunde;
import ch.bzz.smartcredit.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<KKarte> kKarteList;
    private List<Kunde> kundeList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setKundeList(new ArrayList<>());
        readKundeJSON();
        setKKarteList(new ArrayList<>());
        readKKarteJSON();
    }

    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    public List<KKarte> readAllKKarten() {
        return getKKarteList();
    }

    public KKarte readKKarteByUUID(String kkarteUUID) {
        KKarte kKarte = null;
        for (KKarte entry : getKKarteList()) {
            if (entry.getKKarteUUID().equals(kkarteUUID)) {
                kKarte = entry;
            }
        }
        return kKarte;
    }

    public List<Kunde> readAllKunde() {

        return getKundeList();
    }

    /**
     * reads a publisher by its uuid
     * @param kundeUUID
     * @return the Publisher (null=not found)
     */
    public Kunde readKundeByUUID(String kundeUUID) {
        Kunde kunde = null;
        for (Kunde entry : getKundeList()) {
            if (entry.getKundeUUID().equals(kundeUUID)) {
                kunde = entry;
            }
        }
        return kunde;
    }

    /**
     * reads the books from the JSON-file
     */
    private void readKKarteJSON() {
        try {
            String path = Config.getProperty("kkarteJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            KKarte[] kKartes = objectMapper.readValue(jsonData, KKarte[].class);
            for (KKarte kKarte : kKartes) {
                getKKarteList().add(kKarte);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readKundeJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("kundeJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Kunde[] kundes = objectMapper.readValue(jsonData, Kunde[].class);
            for (Kunde kunde : kundes) {
                getKundeList().add(kunde);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets bookList
     *
     * @return value of bookList
     */
    private List<KKarte> getKKarteList() {
        return kKarteList;
    }

    /**
     * sets bookList
     *
     * @param kKarteList the value to set
     */
    private void setKKarteList(List<KKarte> kKarteList) {
        this.kKarteList = kKarteList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Kunde> getKundeList() {
        return kundeList;
    }

    /**
     * sets publisherList
     *
     * @param kundeList the value to set
     */
    private void setKundeList(List<Kunde> kundeList) {
        this.kundeList = kundeList;
    }


}
