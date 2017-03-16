package com.esparza.bmiapplication;
//IMPORT STATEMENTS
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CaloriesBurned extends Fragment {
    public CaloriesBurned() {
        // Required empty public constructor
    }

    public static CaloriesBurned newInstance()
    {
        //CREATING THE FRAGMENT
        CaloriesBurned fragment = new CaloriesBurned();

        return fragment;
    }

    //DECLARING OUR WIDGETS
    private static EditText aWeek;
    private static EditText how;
    private static EditText work;

    private static TextView perWeek;
    private static TextView LBSperWeek;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.fragment_calories_burned, container,false);

        //CALLING OUR WIDGETS FROM THE XML FILE
        aWeek = (EditText)view.findViewById(R.id.aWeekET);
        how = (EditText)view.findViewById(R.id.howET);
        work = (EditText)view.findViewById(R.id.workET);

        perWeek =(TextView)view.findViewById(R.id.perWeekET);
        LBSperWeek =(TextView)view.findViewById(R.id.LBSperWeekET);

        final Button button = (Button)view.findViewById(R.id.calculate);
        //SETTING A LISTENER FOR THE BUTTON
        button.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        onButtonClick(v);
                    }
                }
        );
        return view;
    }

    //MAKING THE BUTTON DO CALCULATIONS
    public void onButtonClick(View v)
    {
        //CONVERTING THE INTEGERS TO STRING TYPE
        int AWEEK = Integer.parseInt(aWeek.getText().toString());
        int HOW = Integer.parseInt(how.getText().toString());
        int WORK = Integer.parseInt(work.getText().toString());
        //DECLARING PER WEEK AND POUNDS LOST PER WEEK
        int PW;
        double LBS;

        //CALCULATING  THE AMOUNT OF CALORIES BURNED PER WEEK
        WORK = WORK *250;
        PW = (AWEEK * HOW * 200) + WORK  * 7;

        //CALCULATING THE POUNDS LOST PER WEEK
        LBS = PW /3500;

        //CHANGING THE TEXT ON OUR TEXTVIEWS
        perWeek.setText(Integer.toString(PW));
        LBSperWeek.setText(Double.toString(LBS));
    }

}
