package util;


import model.UserMeal;
import model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin 31.05.2015.
 */
public class UserMealsUtil {
	public static void main(String[] args) {
		List<UserMeal> mealList = Arrays.asList(
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Dinner", 1000),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Supper", 500),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "dinner", 500),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Supper", 510));
		List<UserMealWithExceed> filteredWithExceededList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

		filteredWithExceededList.stream().forEach(k ->  System.out.println(": " + k + "  "));
//        .toLocalDate();
//        .toLocalTime();
	}

	public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
																   LocalTime endTime, int caloriesPerDay) {
		// TODO return filtered list with correctly exceeded field

		// grouping by dates , and summing calories per one day, convert to calorienProTagMap
		Map<LocalDate, Integer> calorienProTagMap = mealList.stream().collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),
				Collectors.summingInt(meal -> meal.getCalories()))
		);


		// use TimeUtil calorienProTagMap to compare > caloriesPerDay

		List<UserMealWithExceed> userMealWithExceedList = mealList.stream().filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
				.map(x -> new UserMealWithExceed(x.getDateTime(), x.getDescription(), x.getCalories(),
						calorienProTagMap.get(x.getDateTime().toLocalDate()) > caloriesPerDay
				))
				.collect(Collectors.toList());

		return userMealWithExceedList;
	}
}
