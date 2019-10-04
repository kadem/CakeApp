package bakery.cake.bakeryorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.render.EventRenderer;

// Parses all information received form ViewCalendar
public class DrawableEventRenderer extends EventRenderer<DrawableCalendarEvent> {


    // Initializes event fields
    @Override
    public void render(View view, DrawableCalendarEvent event) {

        // Initializes the event fields in the xml file
        ImageView imageView = (ImageView) view.findViewById(R.id.view_agenda_event_image);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_agenda_event_title);
        TextView txtLocation = (TextView) view.findViewById(R.id.view_agenda_event_location);
        LinearLayout descriptionContainer = (LinearLayout) view.findViewById(R.id.view_agenda_event_description_container);
        LinearLayout locationContainer = (LinearLayout) view.findViewById(R.id.view_agenda_event_location_container);

        descriptionContainer.setVisibility(View.VISIBLE);

        imageView.setImageDrawable(view.getContext().getResources().getDrawable(event.getDrawableId()));

        txtTitle.setTextColor(view.getResources().getColor(android.R.color.black));

        // Sets the text for the events in the xml files
        txtTitle.setText(event.getTitle());
        txtLocation.setText(event.getLocation());
        if (event.getLocation().length() > 0) {
            locationContainer.setVisibility(View.VISIBLE);
            txtLocation.setText(event.getLocation());
        } else {
            locationContainer.setVisibility(View.GONE);
        }

        // Sets color of text depending on whether there are events on this day
        if (event.getTitle().equals(view.getResources().getString(R.string.agenda_event_no_events))) {
            txtTitle.setTextColor(view.getResources().getColor(android.R.color.black));
        } else {
            txtTitle.setTextColor(view.getResources().getColor(R.color.theme_text_icons));
        }
        descriptionContainer.setBackgroundColor(event.getColor());
        txtLocation.setTextColor(view.getResources().getColor(R.color.theme_text_icons));
    }

    // Returns the Drawable of the event
    @Override
    public int getEventLayout() {
        return R.layout.view_agenda_drawable_event;
    }

    // Returns the class
    @Override
    public Class<DrawableCalendarEvent> getRenderType() {
        return DrawableCalendarEvent.class;
    }

    // endregion
}
