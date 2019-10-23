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
public class main_Fruit_shop_ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Manager Mg = new Manager();
        ArrayList<Fruit> lf = new ArrayList<>();
        lf.add(new Fruit("1", "apple", 5, 110, "USA"));
        lf.add(new Fruit("2", "banana", 2, 10, "Viet Nam"));
        lf.add(new Fruit("3", "strawberry", 1, 195423, "Da Lat"));
        lf.add(new Fruit("4", "blue berry", 1, 110000, "Italia"));
        Hashtable<String, ArrayList<Order>> ht = new Hashtable<>();
        do {
            int choice = Mg.menu();
            switch (choice) {
                case 1:
                    Mg.createFruit(lf);
                    break;
                case 2:
                    Mg.viewOrder(ht);
                    break;
                case 3:
                    Mg.shopping(lf, ht);
                    break;
                case 4:
                    return;
            }
        } while (true);

    }

}
