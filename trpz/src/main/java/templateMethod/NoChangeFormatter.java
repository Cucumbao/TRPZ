package templateMethod;

import repo.FileRepository;

public class NoChangeFormatter extends TextFormatterTemplate {
    public NoChangeFormatter(FileRepository fileRepository) {
        super(fileRepository);
    }

    @Override
    protected String transform(String text) {
        return text;
    }
}