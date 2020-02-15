package util;

import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class Attachments {
    @Attachment(value = "Вложение", fileExtension = ".txt")
    public static byte[] getBytes(Map<String, Integer> map) throws IOException {
        File file = new File("map");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            Integer[] a = map.values().toArray(new Integer[0]);
            Arrays.sort(a);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (a[a.length - 1].equals(entry.getValue())) {
                    writer.write("\n");
                    writer.write("Товар с наибольшей ценой " + entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
            map.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Files.readAllBytes(Paths.get("map"));
    }

}
