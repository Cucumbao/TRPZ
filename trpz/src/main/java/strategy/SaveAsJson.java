package strategy;
import model.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;

public class SaveAsJson implements SaveStrategy {
    @Override
    public void save(File file) {
        JSONObject obj = new JSONObject();
        obj.put("id", file.getId());
        obj.put("name", file.getFileName());
        obj.put("filepath", file.getFilePath());
        obj.put("filecontent", file.getContent());
        obj.put("userid", file.getUser());
        obj.put("lastUpdate", file.getLastUpdate());

        try {
            Path originalPath = Paths.get(file.getFilePath());

            Path folder;
            if (Files.isDirectory(originalPath) || !originalPath.getFileName().toString().contains(".")) {
                folder = originalPath;
            } else {
                folder = originalPath.getParent();
            }

            Files.createDirectories(folder);

            Path filePath = folder.resolve(file.getFileName() + ".json");


            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(obj.toString(4));
            }

            if (!file.getFilePath().endsWith(file.getFileName() + ".json")) {
                file.setFilePath(filePath.toString());
            }

            System.out.println("Файл збережено як JSON: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
