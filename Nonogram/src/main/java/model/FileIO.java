package model;

/*
An interface asserting, that Board, Game etc. operations involving files are unified.
Prob. file-based constructor, writing to a file etc.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class CorruptedFile extends RuntimeException {
    public CorruptedFile() {}

    public CorruptedFile(String message) {
        super(message);
    }
}

public interface FileIO {
    void readFromFile(String path);

    void saveToFile(String path) throws IOException;
}
