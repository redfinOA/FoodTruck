package com.foodtruck.app;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static com.foodtruck.app.Utils.Util.isAfterOneMinute;
import static com.foodtruck.app.Utils.Util.prettyPrint;
import com.foodtruck.app.Model.FoodTruckInfo;
import com.foodtruck.app.Service.FoodTrackService;

public class App 
{
	private static final String statement = "Please click Enter to see next page";
	private static final String NAME = "NAME";
	private static final String LOCATION = "LOCATION";
    private static FoodTrackService foodTrackService;

	public static void main(String[] args) {
		foodTrackService = new FoodTrackService();
		List<FoodTruckInfo> res = foodTrackService.getFoodTruckData();
		//Get initial timestamp
		Calendar calendar = Calendar.getInstance();
		int preHour = calendar.get(Calendar.HOUR);
		int preMin = calendar.get(Calendar.MINUTE);
		Scanner scan = new Scanner(System.in);
		int i=0;
		try {
			while(i<res.size()) {
				System.out.println(prettyPrint(new FoodTruckInfo(NAME, LOCATION)));
				for(int j=0;j<10 && i<res.size();i++,j++) {
					System.out.println(prettyPrint(res.get(i)));
				}
				if(i<res.size()) {
					System.out.println(statement);
					while(scan.nextLine()!=null) {
						//Get current timestamp
						calendar = Calendar.getInstance();
						int curHour = calendar.get(Calendar.HOUR);
						int curMin = calendar.get(Calendar.MINUTE);
						if(isAfterOneMinute(preHour, preMin, curHour, curMin)) {
							preHour = curHour;
							preMin = curMin;
							System.out.println("Data is expired, refreshing data...");
							res = foodTrackService.getFoodTruckData();
							i=0;
						}
						System.out.println(prettyPrint(new FoodTruckInfo(NAME, LOCATION)));
						for(int j=0;j<10 && i<res.size();i++,j++) {
							System.out.println(prettyPrint(res.get(i)));
						}
						if(i==res.size()) break;
						System.out.println(statement);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
