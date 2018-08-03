package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    // CaloriesFood_0
    
    private final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		 String format = dateTime.format(formatter);
		
//		 return "UserMeal [dateTime=" + dateTime + ", description="
		return "UserMeal [dateTime=" + format + ", description="
		+ description + ", calories=" + calories + "]";
	}
    
    
}
