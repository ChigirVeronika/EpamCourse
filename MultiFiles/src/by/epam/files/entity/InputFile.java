package by.epam.files.entity;

import java.io.File;

/**
 * Created by Вероника on 08.01.2016.
 */
public class InputFile extends File {
    private static final long serialVersionUID = 1L;

    private File f;

    public InputFile(String path){
        super(path);
        f = new File(path);
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }


}
