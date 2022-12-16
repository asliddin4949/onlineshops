package onlineshops.Interfaces;

import onlineshops.DataStorage.Storage;
import onlineshops.Objects.User;

import java.util.Scanner;

public class RegisterInterface {
    static void registerUp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        if (!User.isExist(username)){
            Storage.users.add(new User(name,username,User.countId));
            User.countId++;
            System.out.println("* * * You are successfully registered! * * *");
            MainMenu.mainMenu();
        }else {
            System.out.println("* This username has already taken! Please, Try Again! *");
            System.out.println();
            MainMenu.mainMenu();
        }
    }

}
