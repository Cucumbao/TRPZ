package strategy;
import model.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveAsTxt implements SaveStrategy {
    @Override
    public void save(File file) {
        try {
            Path originalPath = Paths.get(file.getFilePath());

            Path folder;
            if (Files.isDirectory(originalPath) || !originalPath.getFileName().toString().contains(".")) {
                folder = originalPath;
            } else {
                folder = originalPath.getParent();
            }

            Files.createDirectories(folder);

            Path filePath = folder.resolve(file.getFileName() + ".txt");

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(file.getContent());
            }
            file.setFilePath(filePath.toString());
            System.out.println("Збережено як TXT: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
