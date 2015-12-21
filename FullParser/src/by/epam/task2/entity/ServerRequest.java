package by.epam.task2.entity;

import java.io.Serializable;

public class ServerRequest implements Serializable {
    private long fileLength;
    private ParserType parserType;

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public ParserType getParserType() {
        return parserType;
    }

    public void setParserType(ParserType parserType) {
        this.parserType = parserType;
    }
}
