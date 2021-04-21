package ex.ex4;

import java.util.ArrayList;
import java.util.List;

class FoodMenu {
    private List<String> menu = new ArrayList<>();

    public FoodMenu() {

    }

    public void addFood(String food) {
        menu.add(food);
    }

    public String getFoodByNo(int no) {
        return menu.get(no - 1);
    }

    public void showMenu() {
        System.out.println("******菜单******");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + "." + menu.get(i));
        }
    }

    public void showOrder() {
        System.out.println("您已点的菜品如下：");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + "." + menu.get(i));
        }
    }
}
