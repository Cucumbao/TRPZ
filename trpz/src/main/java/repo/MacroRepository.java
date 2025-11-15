package repo;
import model.File;
import model.Macro;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MacroRepository implements Repository<Macro> {
    private final String jsonFilePath;

    public MacroRepository(String jsonFilePath ) {
        this.jsonFilePath = jsonFilePath;
    }

    public List<Macro> getMacrosFromJson() {
        List<Macro> macros = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Macro macro = new Macro(
                        obj.getLong("id"),
                        obj.getString("name"),
                        obj.getString("content")
                );
                macros.add(macro);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return macros;
    }

    public static void main(String[] args) {
        MacroRepository Macrorepo = new MacroRepository("src/main/java/TestData/Macros.json");
        System.out.println("Всі файли:");
        Macrorepo.findAll();
        System.out.println("\nМакрос з id=2:");
        Macrorepo.findById(2L);
    }


    @Override
    public File findById(Long id) {
        List<Macro> macros = getMacrosFromJson();
        for (Macro b : macros) {
            if (b.getId().equals(id)) {
                System.out.println(b);
                return null;
            }
        }
        System.out.println("Macros with id=" + id + " not found.");
        return null;
    }

    @Override
    public void findAll() {
        List<Macro> macros = getMacrosFromJson();
        macros.forEach(System.out::println);
    }

    @Override
    public void save(Macro macro) {
    }

    @Override
    public Macro delete(Long id) {
        return null;
    }

    @Override
    public Macro update(Macro macro) {return null;}
}
