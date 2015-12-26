package client.by.epam.fullparser.client.bean;

import server.by.epam.fullparser.server.entity.ParserType;

import java.io.FileInputStream;

public class FileSendRequest extends Request {
    private ParserType parserType;
    private long fileLength;
    private FileInputStream fileInputStream;

    public ParserType getParserType() {
        return parserType;
    }

    public void setParserType(ParserType parserType) {
        this.parserType = parserType;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
}