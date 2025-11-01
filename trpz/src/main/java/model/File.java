package model;

public class File {
    private Long id;
    private String fileName;
    private String content;
    private Long userid;
    private String lastUpdate;

    public File(Long id, String fileName, String content,Long userid, String lastUpdate) {
        this.id = id;
        this.fileName = fileName;
        this.content = content;
        this.userid = userid;
        this.lastUpdate = lastUpdate;
    }


    // геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }


    public Long getUser() { return userid; }
    public void setUser(Long user) { this.userid = user; }

    public String getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(String lastUpdate) { this.lastUpdate = lastUpdate; }

    @Override
    public String toString() {
        return "model.File.json{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", user=" + (userid) +
                ", content=" + (content) +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
