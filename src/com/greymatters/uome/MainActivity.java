package com.greymatters.uome;

import java.util.Iterator;
import java.util.Set;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;  
import android.provider.ContactsContract.CommonDataKinds.Email;  

public class MainActivity extends Activity {


	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNextScreen = (Button) findViewById(R.id.new_uome);
      //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                //Starting a new Intent
           //     Intent nextScreen = new Intent(getApplicationContext(), AddActivity.class);
 
               
             //   startActivity(nextScreen);
             //   overridePendingTransition(R.anim.push_left_in,R.anim.push_left_in); 
 
            	
            
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                startActivityForResult(pickContactIntent, 1001);
            	}  
            	
            
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (resultCode == RESULT_OK) {  
            switch (requestCode) {  
            case 1001:  
                // handle contact results  
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(Phone.NUMBER);
                String number = cursor.getString(column);

            	        SmsManager sms = SmsManager.getDefault();
            	        sms.sendTextMessage(number, null, "You owe me $10!!!", null, null);
            	   
            	
                break;  
            }  
        } else {  
            // gracefully handle failure  
          //  Log.w(DEBUG_TAG, "Warning: activity result not ok");  
        }  
    }  
}
