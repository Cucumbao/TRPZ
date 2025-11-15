package templateMethod;

import repo.FileRepository;

public class UppercaseFormatter extends TextFormatterTemplate {
    public UppercaseFormatter(FileRepository fileRepository) {
        super(fileRepository);
    }

    @Override
    protected String transform(String text) {
        return text.toUpperCase();
    }
}