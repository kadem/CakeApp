package bakery.cake.bakeryorder;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



// Deals with setting up Calendar inter face and initializes initial events
public class ViewCalendar extends AppCompatActivity implements CalendarPickerController {

        // Initial variables
        private static final String LOG_TAG = ViewCalendar.class.getSimpleName();
        AgendaCalendarView mAgendaCalendarView;


        // Deals with initialization of class
        @Override
        protected void onCreate(Bundle savedInstanceState) {

                // Switches view to calendar
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_view_calendar);

                // Creates calendar and initializes the minimum and maximum dates
                mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);
                Calendar minDate = Calendar.getInstance();
                Calendar maxDate = Calendar.getInstance();

                // Sets minimum date of calendar to current month and max date to one year from today
                minDate.add(Calendar.MONTH, 0);
                minDate.set(Calendar.DAY_OF_MONTH, 1);
                maxDate.add(Calendar.YEAR, 1);

                // Initializes list of events and adds simulated events into list
                List<CalendarEvent> eventList = new ArrayList<>();
                mockList(eventList);

                // Adds the events onto the calendar
                mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);

        }


        // Method which deals with day selection
        @Override
        public void onDaySelected(DayItem dayItem) {
                Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
        }

        // Method which deals with event selection
        @Override
        public void onEventSelected(CalendarEvent event) {
                Log.d(LOG_TAG, String.format("Selected event: %s", event));
        }

        // Method which deals with Calendar Scrolling
        @Override
        public void onScrollToDate(Calendar calendar) {
                if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
                }
        }


        // Creates the initial list of events for the calendar
        private void mockList(List<CalendarEvent> eventList) {

                // Creates event that stretches for a period of one month for a Custom Cake Order
                Calendar startTime1 = Calendar.getInstance();
                Calendar endTime1 = Calendar.getInstance();       // Sets initial day of event as today
                endTime1.add(Calendar.MONTH, 1);              // Sets end time as one month from today
                BaseCalendarEvent event1 = new BaseCalendarEvent("Custom Cake Order", "4-tier cake", "Pickup",
                        ContextCompat.getColor(this, R.color.reception_status_blue), startTime1, endTime1, true);
                eventList.add(event1);

                // Creates event that stretches for a period of one day for a regular cake order
                Calendar startTime2 = Calendar.getInstance();
                startTime2.add(Calendar.DAY_OF_YEAR, 0);      // Sets initial day of event
                Calendar endTime2 = Calendar.getInstance();
                endTime2.add(Calendar.DAY_OF_YEAR, 0);        // Sets end day of event
                BaseCalendarEvent event2 = new BaseCalendarEvent("Cheesecake", "Pre-designed cake", "Pickup",
                        ContextCompat.getColor(this, R.color.blue_dark), startTime2, endTime2, true);
                eventList.add(event2);

                // Creates event that stretches for a period of one day for Red Velvet Cupcakes
                Calendar startTime3 = Calendar.getInstance();
                Calendar endTime3 = Calendar.getInstance();
                startTime3.set(Calendar.HOUR_OF_DAY, 14);         // Sets initial hour time of event
                startTime3.set(Calendar.MINUTE, 0);               // Sets initial minute time of event
                endTime3.set(Calendar.HOUR_OF_DAY, 15);           // Sets end hour time of event
                endTime3.set(Calendar.MINUTE, 0);                 // Sets end minute time of event

                DrawableCalendarEvent event3 = new DrawableCalendarEvent("Chocolate cake", "cake", "Delivery",
                        ContextCompat.getColor(this, R.color.main_blue), startTime3, endTime3, false, android.R.drawable.ic_dialog_info);
                eventList.add(event3);
        }

}
