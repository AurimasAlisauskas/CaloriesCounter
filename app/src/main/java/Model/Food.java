package Model;

import java.io.Serializable;

public class Food implements Serializable {
    private static final long serialVersionUID = 10L;
    private String foodName;
    private int calories;
    private int foddId;
    private String recordDate;

    public Food(String food, int cals, int id, String date) {
        foodName = food;
        calories = cals;
        foddId = id;
        recordDate = date;
    }

    public Food() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFoddId() {
        return foddId;
    }

    public void setFoddId(int foddId) {
        this.foddId = foddId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
