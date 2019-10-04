package bakery.cake.bakeryorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

/**
 * Created by Victoria Goon on 4/19/18.
 */

public class FormPage  extends AppCompatActivity implements View.OnClickListener {

    EditText firstnameWidget;
    EditText lastnameWidget;
    EditText emailWidget;
    DatePicker dateWidget;
    TimePicker timeWidget;
    Spinner icingSpinnerWidget;
    Spinner flavorSpinnerWidget;
    RadioGroup tiers;
    RadioGroup sizes;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_page);

        firstnameWidget = (EditText) findViewById(R.id.EditFirstName);
        lastnameWidget = (EditText) findViewById(R.id.EditLastName);
        emailWidget = (EditText) findViewById(R.id.EditEmail);
        dateWidget = (DatePicker) findViewById(R.id.datePicker);
        timeWidget = (TimePicker) findViewById(R.id.timePicker);
        icingSpinnerWidget = (Spinner) findViewById(R.id.icingSpinnerWidget);
        flavorSpinnerWidget = (Spinner) findViewById(R.id.flavorSpinnerWidget);
        tiers = (RadioGroup) findViewById(R.id.tierRadioGroup);
        sizes = (RadioGroup) findViewById(R.id.sizesRadioGroup);

        submitButton = (Button) findViewById(R.id.submitButton);

        //when submit button is clicked, all the inputs will be gathered. Not checking for empty slots.
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // All the inputs needed for the shopping cart
                String fname = firstnameWidget.getText().toString();                    //input from first name widget
                String lname = lastnameWidget.getText().toString();                     //input from last name widget
                String  email = emailWidget.getText().toString();                       //input from email widget
                int  day = dateWidget.getDayOfMonth();                                  //day input from date picker widget
                int month = dateWidget.getMonth();                                      //month input from date picker widget
                int year = dateWidget.getYear();                                        //year input from date picker widget
                int minute = timeWidget.getMinute();                                    //minute input from date picker widget
                int hour = timeWidget.getHour();                                        //hour input from date picker widget
                String icing = icingSpinnerWidget.getSelectedItem().toString();         //input from icing spinner widget
                String flavor = flavorSpinnerWidget.getSelectedItem().toString();         //input from flavor spinner widget
                RadioButton tierPicked = (RadioButton)findViewById(tiers.getCheckedRadioButtonId());
                String tierStr = tierPicked.toString();                                 //input from tier radio group widget
                RadioButton sizePicked = (RadioButton)findViewById(sizes.getCheckedRadioButtonId());
                String sizeStr = sizePicked.toString();                                 //input from flavor radio group widget

                //send info to shopping cart here, change screen and everything
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}


