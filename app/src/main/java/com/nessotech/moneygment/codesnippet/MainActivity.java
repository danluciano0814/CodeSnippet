package com.nessotech.moneygment.codesnippet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.nessotech.moneygment.codesnippet.application.AppDatabase;
import com.nessotech.moneygment.codesnippet.dao.UserDao;
import com.nessotech.moneygment.codesnippet.entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomSnippet();
        checkInternetSnippet(this);
        savePreferenceSnippet();
        getPreferenceSnippet();
        serviceSnippet(this);

    }

    private void roomSnippet(){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();

        UserDao userDao = db.userDao();

        User user = new User();
        user.setFirstName("Dan");
        user.setLastName("Luciano");

        User user1 = new User();
        user1.setFirstName("Gle");
        user1.setLastName("Reyes");

        userDao.insertAll(user, user1);

        String result = "";

        for(User u : userDao.getAll()){
            result += "FNAME : " + u.getFirstName() + " - LASTNAME : " + u.getLastName() + "\n";
        }

        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

        userDao.delete(user);
        userDao.delete(user1);
    }

    private String checkInternetSnippet(Context context){
        String status = null;
        ConnectivityManager cm = (ConnectivityManager)           context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled";
                return status;
            }
        } else {
            status = "No internet is available";
            return status;
        }
        return status;
    }

    private void savePreferenceSnippet(){
        SharedPreferences.Editor editor = getSharedPreferences("PREF NAME", MODE_PRIVATE).edit();
        editor.putString("name", "Elena");
        editor.putInt("idName", 12);
        editor.apply();
    }

    private void getPreferenceSnippet(){
        SharedPreferences prefs = getSharedPreferences("PREF NAME", MODE_PRIVATE);
        String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        int idName = prefs.getInt("idName", 0); //0 is the default value.
    }

    private void serviceSnippet(Context context){
        // use this to start and trigger a service
        Intent i= new Intent(context, MyService.class);
        // potentially add data to the intent
        i.putExtra("KEY1", "Value to be used by the service");
        context.startService(i);
    }

    private void boundServiceSnippet(){
        /*Notification notification = new Notification(R.drawable.icon, getText(R.string.ticker_text),
                System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, ExampleActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(this, getText(R.string.notification_title),
                getText(R.string.notification_message), pendingIntent);
        startForeground(ONGOING_NOTIFICATION_ID, notification);*/
    }


}
