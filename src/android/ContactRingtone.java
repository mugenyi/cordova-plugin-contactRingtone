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

import android.util.Log;

import java.util.Date;


public class ContactRingtone extends CordovaPlugin {


@Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {



        if (action.equals("setRingtone")) {
            String id = args.getString(0);
            String path = args.getString(1);

            String[] PROJECTION = null;

            Uri contactData = ContactsContract.Contacts.CONTENT_URI;
            String contactId = contactData.getLastPathSegment();


            Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, id);
            ContentValues localContentValues = new ContentValues();

            localContentValues.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
            localContentValues.put(ContactsContract.Data.CUSTOM_RINGTONE, path);
            this.cordova.getActivity().getApplicationContext().getContentResolver().update(localUri, localContentValues, null, null);


            callbackContext.success("Ringtone set for contact");
            return true;
        }

        callbackContext.error("Invalid action: " + action);
        return false;
    }


}
