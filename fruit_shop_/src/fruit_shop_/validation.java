/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop_;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tien_do
 */
public class validation {
     private final static Scanner in = new Scanner(System.in);

    public static String check_input_string(String MSG) {
        //loop until user input correct
        String input = " ";
        do {
            System.out.println(MSG);
            input = in.nextLine().trim();
            if (!input.matches("[\\w\\s]+")) {
                System.out.println("Not right.Please enter again !!!");
            } else {
                return input;
            }

        } while (true);
    }

    public static int check_input_int(String MSG, int min, int max) {
        int num;
        String input;
        do {
            System.out.println(MSG);
            input = in.nextLine().trim();
            if (input.matches("\\d+")) {
                num = Integer.parseInt(input);
                if (num >= min && num <= max) {
                    break;
                } else {
                    System.err.println("type mismatch");
                    continue;
                }
            }

        } while (true);
        return num;
    }

    public static double check_input_double(String MSG, double min, double max) {
        double num;
        String input;
        do {
            System.out.println(MSG);
            input = in.nextLine().trim();
            try {
                num = Double.parseDouble(input);
                break;
            } catch (Exception e) {
                System.err.println("type mismatch");
                continue;
            }
        } while (true);

        return num;
    }

    public static boolean check_input_YN(String MSG) {
        do {
            String input = check_input_string(MSG);
            if (input.matches("[yY]")) {
                return true;
            }
            if (input.matches("[nN]")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        } while (true);
    }

    public static boolean checkIdExist(ArrayList<Fruit> lf, String id) {
        for (Fruit fruit : lf) {
            if (id.equalsIgnoreCase(fruit.getFruitId())) {
                return false;
            }
        }
        return true;
    }
    
    //check item exist or not
    public static boolean checkItemExist(ArrayList<Order> lo, String id) {
        for (Order order : lo) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }
}
