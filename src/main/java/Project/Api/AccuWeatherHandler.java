package Project.Api;

import java.util.HashMap;

public class AccuWeatherHandler implements WeatherApiStrategy {
	

	public float getTemp(String ciudad)
	{
		 HashMap<String, Object> temp = (HashMap<String, Object>) new AccuWeatherAPI().getWeather(ciudad).get(0).get("Temperature");
		return fahrenheitToCelsius((int) temp.get("Value"));
	}
	
	private float fahrenheitToCelsius(int fahrenheits)
	{
		return (fahrenheits - 32)*5/9;
	}
}
