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
            String contact_id = args.getString(0);
            String path = args.getString(1);

             int id = Integer.parseInt(contact_id);

            String[] PROJECTION = null;

            Uri contactData = ContactsContract.Contacts.CONTENT_URI;
            String contactId = contactData.getLastPathSegment();

            Cursor localCursor =    this.cordova.getActivity().getApplicationContext().getContentResolver().query(contactData, PROJECTION, null, null, null);
            localCursor.move(id);

            String str1 = localCursor.getString(localCursor.getColumnIndexOrThrow("_id"));
            String str2 = localCursor.getString(localCursor.getColumnIndexOrThrow("display_name"));
            Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, str1);
            ContentValues localContentValues = new ContentValues();

            localContentValues.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
            localContentValues.put(ContactsContract.Data.CUSTOM_RINGTONE, path);
            this.cordova.getActivity().getApplicationContext().getContentResolver().update(localUri, localContentValues, null, null);


            callbackContext.success("Ringtone set for contact: "+str2);
            return true;
        }

        callbackContext.error("Invalid action: " + action);
        return false;
    }


}
