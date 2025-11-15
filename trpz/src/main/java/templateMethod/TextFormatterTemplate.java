package templateMethod;

import model.File;
import repo.FileRepository;

public abstract class TextFormatterTemplate {

    private final FileRepository fileRepository;

    public TextFormatterTemplate(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public final String format(Long fileId) {
        File file = openFile(fileId);
        String text = read(file);
        validate(text);
        text = transform(text);
        update(file, text);
        return text;

    }

    protected File openFile(Long id) {
        return fileRepository.findByIdInMemory(id);
    }

    protected String read(File file) {
        return file.getContent();
    }

    protected void validate(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Content is null!");
        }
    }


    protected abstract String transform(String text);

    protected void update(File file, String newContent) {
        file.setContent(newContent);
        fileRepository.save(file);
    }
}
