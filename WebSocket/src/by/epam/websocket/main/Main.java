package by.epam.websocket.main;

import by.epam.websocket.service.FileService;
import by.epam.websocket.service.HttpClient;
import by.epam.websocket.service.HttpResponseParser;

import java.util.Scanner;

/**
 * Go point of application.
 */
public class Main {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String GET_REQUEST_FILEPATH="E:\\EpamCourse\\WebSocket\\resource\\request\\getrequest.txt";

    public static void main(String args[]) {
        System.out.println("Choose request type: 1-GET, 2-POST");
        int choice = inputWayValidation();
        String request=null;
        switch (choice){
            case ONE:{
                request = FileService.readFile(GET_REQUEST_FILEPATH);
                break;
            }
            case TWO:{

                break;
            }
        }

        HttpClient httpClient = new HttpClient();
        String response = httpClient.sendRequest(request);

        System.out.println(HttpResponseParser.parse(response));
    }


    /**
     * Process user input and validate it.
     * In case of incorrect input show
     * appropriate message and give another try.
     * Let only 1 or 2 according to ways user has.
     *
     * @return number user entered
     */
    public static int inputWayValidation(){
        int number=0;
        Scanner scanner = new Scanner(System.in);
        while (!(number==1||number==2)){
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number. Try again: ");
                scanner.next();
            }
            number = scanner.nextInt();
            if(!(number==1||number==2)){
                System.out.println("Enter 1 or 2 please:");
            }
        }
        System.out.println("Got it!");
        return number;
    }
}
