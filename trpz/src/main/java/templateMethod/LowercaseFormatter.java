package templateMethod;

import repo.FileRepository;

public class LowercaseFormatter extends TextFormatterTemplate {

    public LowercaseFormatter(FileRepository fileRepository) {
        super(fileRepository);
    }

    @Override
    protected String transform(String text) {
        return text.toLowerCase();
    }
}

