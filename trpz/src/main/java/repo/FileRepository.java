package repo;
import model.File;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileRepository implements Repository<File> {
    private final String jsonFilePath;


    public FileRepository(String jsonFilePath ) {
        this.jsonFilePath = jsonFilePath;
    }

    public List<File> getFilesFromJson() {
        List<File> files = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                File file = new File(
                        obj.getLong("id"),
                        obj.getString("name"),
                        obj.getString("filecontent"),
                        obj.getLong("userid"),
                        obj.getString("lastUpdate")
                );
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
    public void findById(Long id) {
        List<File> files = getFilesFromJson();
        for (File b : files) {
            if (b.getId().equals(id)) {
                System.out.println(b);
            }
        }
        System.out.println("Bookmark with id=" + id + " not found.");
    }
    @Override
    public void findAll() {
        List<File> files = getFilesFromJson();
        files.forEach(System.out::println);
    }

    @Override
    public void save(File file) {
    }

    @Override
    public void delete(Long id) {
        List<File> files = getFilesFromJson();
        boolean removed = files.removeIf(f -> f.getId().equals(id));

        if (!removed) {
            System.out.println("Файл з id=" + id + " не знайдено.");
            return;
        }
        JSONArray jsonArray = new JSONArray();
        for (File f : files) {
            JSONObject obj = new JSONObject();
            obj.put("id", f.getId());
            obj.put("name", f.getFileName());
            obj.put("filecontent", f.getContent());
            obj.put("userid", f.getUser());
            obj.put("lastUpdate", f.getLastUpdate());
            jsonArray.put(obj);
        }

        try {
            Files.write(Paths.get(jsonFilePath), jsonArray.toString(4).getBytes());
            System.out.println("Файл з id=" + id + " успішно видалено.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
