/**
 */
package cordova.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.LOG;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

import android.util.Log;

import java.util.Date;


public class ContactRingtone extends CordovaPlugin {

    Context context;

    private static final String TAG = "ContactRingtone";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing ContactRingtone Plugin");
  }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("set_ringtone".equals(action)) {
            String contact_id = args.getString(0);
            String ringtone_path = args.getString(1);
            this.context = this.cordova.getActivity().getApplicationContext();

             int id = Integer.parseInt(contact_id);

            this.setTone(id,ringtone_path);
            callbackContext.success("Ringtone set for contact");
            return true;
        }

        callbackContext.error("Invalid action: " + action);
        return false;
    }

    public void setTone(int id, String path){


        String[] PROJECTION = null;

        Uri contactData = ContactsContract.Contacts.CONTENT_URI;
        String contactId = contactData.getLastPathSegment();

        Cursor localCursor =    context.getApplicationContext().getContentResolver().query(contactData, PROJECTION, null, null, null);
        localCursor.move(id/*CONTACT ID NUMBER*/);

        String str1 = localCursor.getString(localCursor.getColumnIndexOrThrow("_id"));
        String str2 = localCursor.getString(localCursor.getColumnIndexOrThrow("display_name"));
        Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, str1);
        ContentValues localContentValues = new ContentValues();

        localContentValues.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
        localContentValues.put(ContactsContract.Data.CUSTOM_RINGTONE, path);
        context.getApplicationContext().getContentResolver().update(localUri, localContentValues, null, null);
      

    }

}
