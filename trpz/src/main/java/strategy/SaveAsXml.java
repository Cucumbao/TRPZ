package strategy;
import model.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveAsXml implements SaveStrategy {
    @Override
    public void save(File file) {
        String xml = "<file>\n" +
                "  <id>" + file.getId() + "</id>\n" +
                "  <name>" + file.getFileName() + "</name>\n" +
                "  <userId>" + file.getUser() + "</userId>\n" +
                "  <lastUpdate>" + file.getLastUpdate() + "</lastUpdate>\n" +
                "  <content>" + file.getContent() + "</content>\n" +
                "</file>";
        try {
            Path originalPath = Paths.get(file.getFilePath());

            Path folder;
            if (Files.isDirectory(originalPath) || !originalPath.getFileName().toString().contains(".")) {
                folder = originalPath;
            } else {
                folder = originalPath.getParent();
            }

            Files.createDirectories(folder);

            Path filePath = folder.resolve(file.getFileName() + ".xml");

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(xml);
            }
            file.setFilePath(filePath.toString());
            System.out.println("Збережено як XML: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}