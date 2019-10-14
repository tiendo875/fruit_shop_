/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop_;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author tien_do
 */
public class Manager {

    public int menu() {
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        int choice = validation.check_input_int("Enter your choice: ", 1, 4);
        return choice;
    }

    // 1.create fruit
    public void createFruit(ArrayList<Fruit> lf) {
        //loop until user don't want to create fruit
        while (true) {
            String fruitId = validation.check_input_string("Enter fruit id: ");
            //check id exist
            if (!validation.checkIdExist(lf, fruitId)) {
                System.err.println("Id exist");
                return;
            }
            String fruitName = validation.check_input_string("Enter fruit name: ");
            double price = validation.check_input_double("Enter price: ", 0, Double.MAX_VALUE);
            int quantity = validation.check_input_int("Enter quantity: ", 0, Integer.MAX_VALUE);
            String origin = validation.check_input_string("Enter origin: ");
            lf.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            //check user want to continue or not
            if (!validation.check_input_YN("Do you want to continue(Y/N): ")) {
                return;
            }
        }
    }

    //2.show orders
    public void viewOrder(Hashtable<String, ArrayList<Order>> ht) {
        for (String name : ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> lo = ht.get(name);
            displayListOrder(lo);
        }
    }

    public void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
    }

    //3.shopping
    public void shopping(ArrayList<Fruit> lf, Hashtable<String, ArrayList<Order>> ht) {
        if (lf.isEmpty()) {
            System.err.println("No have item.");
            return;
        }
        ArrayList<Order> lo = new ArrayList<>();
        while (true) {
            displayListFruit(lf);
            int item = validation.check_input_int("Enter item: ", 1, lf.size());
            Fruit fruit = getFruitByItem(lf, item);
            int quantity = validation.check_input_int("Enter quantity: ", 0, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            //check item exist or not
            if (!validation.checkItemExist(lo, fruit.getFruitId())) {
                updateOrder(lo, fruit.getFruitId(), quantity);
            } else {
                lo.add(new Order(fruit.getFruitId(), fruit.getFruitName(),
                        quantity, fruit.getPrice()));
            }

            if (!validation.check_input_YN("Do you want to continue(Y/N): ")) {
                break;
            }
        }
        displayListOrder(lo);
        String name = validation.check_input_string("Enter name: ");
        ArrayList<Order> al = ht.put(name.toLowerCase(), lo);
        if (al != null) {
            lo.addAll(al);
            ht.put(name.toLowerCase(), lo);
        }
        System.err.println("Add successfull");
    }

    //display list fruit in shop
    public void displayListFruit(ArrayList<Fruit> lf) {
        int countItem = 1;
        System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price");
        for (Fruit fruit : lf) {
            //check shop have item or not 
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10d%-20s%-20s%-15.0f$\n", countItem++,
                        fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
            }
        }
    }

    //get fruint user want to by
    public Fruit getFruitByItem(ArrayList<Fruit> lf, int item) {
        int countItem = 1;
        for (Fruit fruit : lf) {
            //check shop have item or not 
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
    }

    static void updateOrder(ArrayList<Order> lo, String id, int quantity) {
        for (Order order : lo) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }

}
