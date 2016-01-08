package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.File;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see Thread
 */
public class Walker extends Thread {

    /**
     * Number to put in matrix instead of zero
     */
    private int ownNumber;
    private InputFile[] files;

    public Walker(String s, int ownNumber, InputFile[] files){
        super(s);
        this.ownNumber=ownNumber;
        this.files = files;
    }

    /**
     * Try to replace zero on matrix main diagonal and sleep 5 seconds.
     */
    public void run(){
        int millisecondsToSleep=500;
        int count=0;

        //������� ���� output.dat � ��������� ���������� �����
        synchronized(this) {
            File outFile = new File("E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\resource\\release\\out.dat");
            int outCount =  FileService.getStringCount(outFile);

        //����� �� ������� �����[outCount] - ���� ����� � 0 ���������
            if(outCount<files.length) {
                String path=files[outCount].getPath();
                files[outCount].doWork(path);
            }

            try {
                Thread.sleep(millisecondsToSleep);
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
    }
}
