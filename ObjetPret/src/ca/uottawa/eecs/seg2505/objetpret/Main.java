package ca.uottawa.eecs.seg2505.objetpret;

import android.app.Application;

import ca.uottawa.eecs.seg2505.objetpret.db.ParseFacade;

import com.parse.Parse;

public class Main extends Application {
	
	@Override
	public void onCreate() {
	    super.onCreate();

	    Parse.initialize(this, "448LCfDpX4KQW2j6HHbpXbhOBLiRHm2eqCrEjHWD", "d4x4A5SJVyWYyznzqlOj6Q8MXnGdD2W6K3HkrfHl"); // Your Application ID and Client Key are defined elsewhere
	    Delegateur.setDBFacade(new ParseFacade());
	}

}
