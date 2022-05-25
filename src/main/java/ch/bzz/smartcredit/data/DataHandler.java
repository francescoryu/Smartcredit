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

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 3.0
 */

public class DataHandler {
    private static List<KKarte> kKarteList;
    private static List<Kunde> kundeList;

    private DataHandler() {

    }

    /**
     * Liest alle KKarten
     *
     * @return KKartenliste
     */

    public static List<KKarte> readAllKKarten() {
        return getKKarteList();
    }

    /**
     * Liest alle KKarten anhand der UUID
     *
     * @param kkarteUUID
     * @return KKarte
     */

    public static KKarte readKKarteByUUID(String kkarteUUID) {
        KKarte kKarte = null;
        for (KKarte entry : getKKarteList()) {
            if (entry.getKKarteUUID().equals(kkarteUUID)) {
                kKarte = entry;
            }
        }
        return kKarte;
    }

    public static List<Kunde> readAllKunde() {

        return getKundeList();
    }

    /**
     * Liest Kunde anhand der UUID
     *
     * @param kundeUUID
     * @return Kunde (null = nicht gefunden)
     */
    public static Kunde readKundeByUUID(String kundeUUID) {
        Kunde kunde = null;
        for (Kunde entry : getKundeList()) {
            if (entry.getKundeUUID().equals(kundeUUID)) {
                kunde = entry;
            }
        }
        return kunde;
    }

    /**
     * liest die KKarten vom JSON-file
     */
    private static void readKKarteJSON() {
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
     * liest den Kunden anhand vom JSON-file
     */
    private static void readKundeJSON() {
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
     * get KKarteList
     *
     * @return Wert der KKartenliste
     */
    private static List<KKarte> getKKarteList() {
        if (kKarteList == null) {
            setKKarteList(new ArrayList<>());
            readKKarteJSON();

        }

        return kKarteList;
    }

    /**
     * sets KKarteList
     *
     * @param kKarteList den Wert
     */
    private static void setKKarteList(List<KKarte> kKarteList) {
        DataHandler.kKarteList = kKarteList;
    }

    /**
     * gets KundeList
     *
     * @return Wert der KundeList
     */
    private static List<Kunde> getKundeList() {
        if (kundeList == null) {
            setKundeList(new ArrayList<>());
            readKundeJSON();
        }

        return kundeList;
    }

    /**
     * sets KundeList
     *
     * @param kundeList den Wert
     */
    private static void setKundeList(List<Kunde> kundeList) {
        DataHandler.kundeList = kundeList;
    }


}
