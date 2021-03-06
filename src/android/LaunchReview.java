
package uk.co.workingedge.phonegap.plugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.apache.cordova.*;

public class LaunchReview extends CordovaPlugin {

	private static final String LOG_TAG = "LaunchReview";

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		boolean result = false;
		if ("launch".equals(action)){
			try {
				String appPackageName = args.getString(0);
				Log.d(LOG_TAG, "Opening market for ".concat(appPackageName));
				Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appPackageName));
				marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
				this.cordova.getActivity().startActivity(marketIntent);
				result = true;
			}catch( JSONException e ) {
				Log.e(LOG_TAG, "Exception occurred: ".concat(e.getMessage()));
			}
		} else {
			Log.e(LOG_TAG, "Invalid action");
		}
		if(result == true){
			callbackContext.success();
		}
		return result;
	}
}
