package bakery.cake.bakeryorder;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;

import java.util.Calendar;

// Contains all the constructors for creating events
public class DrawableCalendarEvent extends BaseCalendarEvent {
    private int mDrawableId;

    // Creates event based on title, description, location, box color, startTime, endTime, duration and the drawable id
    public DrawableCalendarEvent(String title, String description, String location, int color, Calendar startTime, Calendar endTime, boolean allDay, int drawableId) {
        super(title, description, location, color, startTime, endTime, allDay);
        this.mDrawableId = drawableId;
    }

    // Creates event bases on existing event
    public DrawableCalendarEvent(DrawableCalendarEvent calendarEvent) {
        super(calendarEvent);
        this.mDrawableId = calendarEvent.getDrawableId();
    }


    // Returns the drawable
    public int getDrawableId() {
        return mDrawableId;
    }

    // Sets the drawable
    public void setDrawableId(int drawableId) {
        this.mDrawableId = drawableId;
    }

    // Creates CalendarEvent based on DrawableCalendarEvent
    @Override
    public CalendarEvent copy() {
        return new DrawableCalendarEvent(this);
    }

}
