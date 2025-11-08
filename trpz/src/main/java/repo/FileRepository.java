package repo;
import model.File;
import org.json.JSONArray;
import org.json.JSONObject;
import strategy.FileSaver;
import strategy.SaveAsJson;
import strategy.SaveAsTxt;
import strategy.SaveAsXml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileRepository implements Repository<File> {
    private final String jsonFilePath;

    public FileRepository(String jsonFilePath ) {
        this.jsonFilePath = jsonFilePath;
    }
    private final List<File> filesInMemory = new ArrayList<>();


    public List<File> getFilesFromJson() {
        List<File> files = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                File file = new File();

                file.setId(obj.getLong("id"));
                file.setFileName(obj.getString("name"));
                file.setFilePath(obj.getString("filepath"));
                file.setContent(obj.getString("filecontent"));
                file.setUser(obj.getLong("userid"));
                file.setLastUpdate(obj.getString("lastUpdate"));

                files.add(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public static void main(String[] args) {
        FileRepository repo = new FileRepository("src/main/java/TestData/File.json");

        System.out.println("Всі файли:");
        repo.findAll();

        System.out.println("\nФайл з id=2:");
        repo.findById(2L);

        repo.delete(4L);
    }


    @Override
    public File findById(Long id) {
        List<File> files = getFilesFromJson();
        for (File b : files) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        System.out.println("File with id=" + id + " not found.");
        return null;
    }
    @Override
    public void findAll() {
        List<File> files = getFilesFromJson();
        files.forEach(System.out::println);
    }

    @Override
    public void save(File file) {
        filesInMemory.add(file);
        FileSaver saver = new FileSaver();
        String path = file.getFilePath().toLowerCase();
        if (path.endsWith("json")) {
            saver.setStrategy(new SaveAsJson());
        } else if (path.endsWith("xml")) {
            saver.setStrategy(new SaveAsXml());
        } else if (path.endsWith("txt")) {
            saver.setStrategy(new SaveAsTxt());
        }
        saver.save(file);
    }

    @Override
    public File delete(Long id) {
        File deletedFile = findByIdInMemory(id);
        if (deletedFile == null) {
            System.out.println("File with id=" + id + " not found.");
            return null;
        }

        filesInMemory.remove(deletedFile);

        Path path = Paths.get(deletedFile.getFilePath());
        try {
            Files.deleteIfExists(path);
            System.out.println("File deleted: " + deletedFile.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deletedFile;
    }
    public File findByIdInMemory(Long id) {
        for (File f : filesInMemory) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }
}
