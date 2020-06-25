package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.collections.ObservableList;

public class DosyaIslemleri {

    private static String yol;
    private static String klasor = "dosyalar";

    private DosyaIslemleri() {
    }

    public static void dosyayaYaz(ObservableList liste, String y) {
        String anaYol = System.getProperty("user.dir") + "/" + klasor + "/" + y + ".txt";
        try {
            File f = new File(anaYol);
            if (!f.exists()) {
                File k = new File(klasor);
                if (!k.exists()) {
                    k.mkdir();
                }
                f.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter yazici = new BufferedWriter(new FileWriter(anaYol))) {
            int size = liste.size();
            for (int i = 0; i < size; i++) {
                yazici.write(liste.get(i).toString());
                yazici.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static BufferedReader dosyayiCagir(String dosyaAdi) {
        String anaYol = System.getProperty("user.dir") + "/" + klasor + "/" + dosyaAdi + ".txt";
        try {
            File f = new File(anaYol);
            if (!f.exists()) {
                File k = new File(klasor);
                if (!k.exists()) {
                    k.mkdir();
                }
                f.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return new BufferedReader(new FileReader(anaYol));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
