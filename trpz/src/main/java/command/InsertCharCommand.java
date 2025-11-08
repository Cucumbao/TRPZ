package command;
import model.File;
import repo.FileRepository;

public class InsertCharCommand implements Command {
    private FileRepository reseiver;
    private Long fileId;
    private char character;
    private int position;
    private String oldContent; // для Undo

    public InsertCharCommand(FileRepository repository, Long fileId, char character, int position) {
        this.reseiver = repository;
        this.fileId = fileId;
        this.character = character;
        this.position = position;
    }

    @Override
    public void execute() {
        File file = reseiver.findByIdInMemory(fileId);
        if (file != null) {
            oldContent = file.getContent();
            StringBuilder sb = new StringBuilder(file.getContent());
            if (position < 0 || position > sb.length()) {
                position = sb.length();
            }
            sb.insert(position, character);
            file.setContent(sb.toString());
            reseiver.save(file);
            System.out.println("Char " + "`" + character + "`" +  " inserted in File: " + file.getFileName());
        }
    }

    @Override
    public void undo() {
        File file = reseiver.findById(fileId);
        if (file != null && oldContent != null) {
            file.setContent(oldContent);
            reseiver.save(file);
            System.out.println("Char deleted from File: " + file.getFileName());
        }
    }
}
