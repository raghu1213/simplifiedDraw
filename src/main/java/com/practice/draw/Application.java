package com.practice.draw;

import com.practice.draw.controller.DefaultController;
import com.practice.draw.print.Printer;
import com.practice.draw.utils.Result;
import com.practice.draw.command.Command;
import com.practice.draw.controller.CommandController;
import com.practice.draw.utils.CommandFactory;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        CommandController controller = new DefaultController();
        while(true){
            try {
                System.out.print("Enter Command:");
                String input = inputScanner.nextLine();
                Command command = CommandFactory.createCommand(input);

                Result result = controller.execute(command);

                if (result.isSuccess()) {
                    Printer.print(controller.getResults());
                } else {
                    System.out.println(result.getMessage());
                }
            }
            catch (Exception ex){
                System.out.println("Unexpected error");
                System.out.println(ex);
                System.out.print("Do you want to continue (y/n):");
                String input = inputScanner.nextLine();
                if (!input.toUpperCase().equals("Y")){
                    break;
                }
            }
        }

    }
}
