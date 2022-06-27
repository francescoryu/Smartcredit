package ch.bzz.smartcredit.data;

import ch.bzz.smartcredit.model.KKarte;
import ch.bzz.smartcredit.model.Kunde;
import ch.bzz.smartcredit.model.User;
import ch.bzz.smartcredit.service.Config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private static List<KKarte> kkarteList;
    private static List<Kunde> kundeList;
    private static List<User> userList;

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

    public static void insertKKarte(KKarte kkarte) {
        getKKarteList().add(kkarte);
        writeKKarteJSON();
    }

    public static void updateKKarte() {
        writeKKarteJSON();
    }

    public static void insertKunde(Kunde kunde) {
        getKundeList().add(kunde);
        writeKundeJSON();
    }

    public static void updateKunde() {
        writeKundeJSON();
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

    public static boolean deleteKKarte(String kkarteUUID) {
        KKarte kkarte = readKKarteByUUID(kkarteUUID);
        if (kkarte != null) {
            getKKarteList().remove(kkarte);
            writeKKarteJSON();
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteKunde(String kundeUUID) {
        Kunde kunde = readKundeByUUID(kundeUUID);
        if (kunde != null) {
            getKundeList().remove(kunde);
            writeKundeJSON();
            return true;
        } else {
            return false;
        }
    }

    private static void writeKKarteJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String kkartePath = Config.getProperty("kkarteJSON");
        try {
            fileOutputStream = new FileOutputStream(kkartePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getKKarteList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeKundeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("kundeJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getKundeList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            KKarte[] kkarten = objectMapper.readValue(jsonData, KKarte[].class);
            for (KKarte kKarte : kkarten) {
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
            Kunde[] kunden = objectMapper.readValue(jsonData, Kunde[].class);
            for (Kunde kunde : kunden) {
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
        if (kkarteList == null) {
            setKKarteList(new ArrayList<>());
            readKKarteJSON();
        }
        return kkarteList;
    }

    /**
     * sets KKarteList
     *
     * @param kkarteList den Wert
     */
    private static void setKKarteList(List<KKarte> kkarteList) {
        DataHandler.kkarteList = kkarteList;
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

    private static void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static List<User> getUserList() {
        if (DataHandler.userList == null) {
            DataHandler.setUserList(new ArrayList<>());
            readUserJSON();
        }
        return userList;
    }
    public static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }
}
