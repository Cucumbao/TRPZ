package repo;

import model.Bookmark;
import model.File;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository implements Repository<Bookmark> {
    private final String jsonBookmarkPath;


    public BookmarkRepository(String jsonBookmarkPath ) {
        this.jsonBookmarkPath = jsonBookmarkPath;
    }

    public List<Bookmark> getBookmarkFromJson() {
        List<Bookmark> bookmarks = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonBookmarkPath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Bookmark bookmark = new Bookmark(
                        obj.getLong("id"),
                        obj.getInt("lineNumber"),
                        obj.getString("description"),
                        obj.getLong("fileid")
                );
                bookmarks.add(bookmark);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookmarks;
    }

    public static void main(String[] args) {
        BookmarkRepository repo = new BookmarkRepository("src/main/java/TestData/Bookmark.json");

        System.out.println("Всі закладки:");
        repo.findAll();

        System.out.println("\nЗакладка з id=2:");
        repo.findById(2L);
    }


    @Override
    public void findById(Long id) {
        List<Bookmark> bookmarks = getBookmarkFromJson();
        for (Bookmark b : bookmarks) {
            if (b.getId().equals(id)) {
                System.out.println(b);
            }
        }
        System.out.println("Bookmark with id=" + id + " not found.");
    }
    @Override
    public void findAll() {
        List<Bookmark> bookmarks = getBookmarkFromJson();
        bookmarks.forEach(System.out::println);
    }
    @Override
    public void save(Bookmark bookmark) {
    }

    @Override
    public void delete(Long id) {
    }
}
