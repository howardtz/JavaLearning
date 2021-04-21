package ex.ex4;

import java.util.ArrayList;
import java.util.List;

class DislikedFoodMenu {
    private List<String> dislikedFood = new ArrayList<>();

    public DislikedFoodMenu() {
    }

    public void addDislikedFood(String food) {
        dislikedFood.add(food);
    }

    public void checkFood(String food) throws BadFoodException {
        for (String element : dislikedFood) {
            if (element.equalsIgnoreCase(food)) {
                throw new BadFoodException("有人不喜欢该菜品...");
            }
        }
    }
}
