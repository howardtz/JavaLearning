package ex.ex4;

import java.util.Scanner;

class Test {
    public static void main(String[] args) {
        int target;
        Scanner input = new Scanner(System.in);
        FoodMenu menu = new FoodMenu();
        FoodMenu order = new FoodMenu();
        DislikedFoodMenu dislikedMenu = new DislikedFoodMenu();
        init(menu, dislikedMenu);
        menu.showMenu();
        while (true) {
            System.out.print("请为大家点菜，输入编号即可（0表示点菜结束）：");
            target = input.nextInt();
            if (target == 0)
                break;
            try {
                dislikedMenu.checkFood(menu.getFoodByNo(target));
                order.addFood(menu.getFoodByNo(target));
            } catch (BadFoodException e) {
                System.out.println(e.getMessage());
            }
            order.showOrder();
        }
    }

    public static void init(FoodMenu menu, DislikedFoodMenu dislikedMenu) {
        menu.addFood("麻辣香锅");
        menu.addFood("排骨炖豆角");
        menu.addFood("葱爆羊肉");
        menu.addFood("酸辣土豆丝");
        menu.addFood("清炒菜花");
        menu.addFood("清炒芥蓝");
        dislikedMenu.addDislikedFood("葱爆羊肉");
    }
}
