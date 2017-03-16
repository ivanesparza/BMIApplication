package com.esparza.bmiapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class YourBMI extends Fragment {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 12345;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notification = new NotificationCompat.Builder(getActivity());
        notification.setAutoCancel(true);
    }


    public YourBMI() {
        // Required empty public constructor
    }

    public static YourBMI newInstance()
    {
        YourBMI fragment = new YourBMI();

        return fragment;
    }

    private static EditText weight;
    private static EditText feet;
    private static EditText inches;

    private String weightString;
    private String feetString;
    private String inchesString;

    private static TextView BMI;

    TopSectionListener activityCommander;

    public interface TopSectionListener
    {
        public void clickSub();
    }

   /* @Override
    public void onAttach (Activity activity)
    {
        super.onAttach(activity);

        try {
            activityCommander = (TopSectionListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_your_bmi, container, false);

        weight = (EditText)view.findViewById(R.id.weightET);
        feet = (EditText)view.findViewById(R.id.feetET);
        inches = (EditText)view.findViewById(R.id.inchesET);




        BMI =(TextView)view.findViewById(R.id.bmi);

        final Button button = (Button)view.findViewById(R.id.sub);

        button.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v){onButtonClick(v);}
                }
        );
        return view;
    }


    //ADDING A FUNCTION TO OUR BUTTON
    public void onButtonClick(View v)
    {

        weightString = weight.getText().toString();
        feetString = feet.getText().toString();
        inchesString = inches.getText().toString();

        //VALIDATING THE INPUT TO MAKE SURE WE DONT CRASH
        //THE APP
        float w;
        float f;
        float inn;
        if(weightString.equals(""))
        {
            w = 0;
        }

        else
        {
            w = Float.parseFloat(weightString);
        }

        if(feetString.equals(""))
        {
            f = 0;
        }

        else
        {
            f = Float.parseFloat(feetString);
        }

        if(inchesString.equals(""))
        {
            inn = 0;
        }

        else
        {
            inn = Float.parseFloat(inchesString);
        }


        //SETTING THE NOTIFICATION
        notification.setSmallIcon(R.drawable.fitnesscenter);
        notification.setTicker("BMI");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("The BMI converter");
        notification.setContentText("How high is your BMI?");

        Intent i = new Intent (getActivity(), YourBMI.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());

        double bmi;

        //BMI FORMULA
        bmi = (w * 0.45) / ((((f*12) + inn) * 0.025) * (((f*12) + inn) * 0.025));

        //CHANGING TEXT OF THE TEXTVIEW
        BMI.setText(Double.toString(bmi));


    }




}
