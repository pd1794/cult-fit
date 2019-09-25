import controller.UserController;
import controller.WorkoutController;
import models.Slot;
import models.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static UserController userController;
    private static WorkoutController workoutController;
    public static void main(String[] args) throws IOException {
        init();
        while (true) {
            mainView();
        }
    }

    private static void init() {
        userController = new UserController(new HashMap<>());
        workoutController = new WorkoutController(new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    private static void mainView() throws IOException {
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Admin View");
        System.out.println("Enter option");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                UserInfo userInfo = loginView();
                if (userInfo == null) return;
                System.out.println("1. View workouts for day");
                System.out.println("2. Book workout");
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        System.out.println("Enter day");
                        String day = scanner.nextLine();
                        List<Slot> slots = workoutController.viewSlots(userInfo.getUserId(), day);
                        System.out.println(slots);
                        break;
                    case 2:
                        System.out.println("Enter Day");
                        String days = scanner.nextLine();
                        System.out.println("Enter Location");
                        String location = scanner.nextLine();
                        System.out.println("Enter Timing");
                        Integer timing = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter daySequence");
                        String daySequence = scanner.nextLine();
                        System.out.println("Enter workout");
                        String workout = scanner.nextLine();
                        System.out.println(workoutController.bookSlot(userInfo.getUserId(), location, timing, daySequence, days, workout));
                        break;
                }
                break;
            case 2:
                System.out.println("Enter userId");
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
                String userId = reader1.readLine();
                System.out.println("Enter password");
                String password = reader1.readLine();
                System.out.println(userController.registerUser(userId, password, "consumer"));
                break;
            case 3:
                System.out.println("Give details for slot to cancel");
                System.out.println("Enter Day");
                String days = scanner.nextLine();
                System.out.println("Enter Location");
                String location = scanner.nextLine();
                System.out.println("Enter Timing");
                Integer timing = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter daySequence");
                String daySequence = scanner.nextLine();
                System.out.println("Enter workout");
                String workout = scanner.nextLine();
                workoutController.cancelSlot(location, timing, daySequence, days, workout);
                break;
        }
    }

    private static UserInfo loginView() throws IOException {
        System.out.println("Enter userId");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userId = reader.readLine();
        System.out.println("Enter password");
        String password = reader.readLine();
        if (userController.userPasswordMatch(userId, password)) {
            System.out.println("Logged in");
            return userController.getUserInfo(userId);
        } else {
            System.out.println("User does not exist");
            return null;
        }
    }
}
