package entity;

import java.io.Serializable;

public class ServerRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private long fileLength;
    private ParserType parserType;

    public ServerRequest(){}

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
