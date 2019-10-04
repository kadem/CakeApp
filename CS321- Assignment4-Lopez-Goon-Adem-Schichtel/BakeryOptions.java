package bakery.cake.bakeryorder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Crated by Kevin Lopez and Victoria Goon
 *
 * Source used: Android Studio Tutorial For Beginners - 2 Edureka: https://www.youtube.com/watch?v=D-iqMlLOrec
 *
 * Main page to select use cases, Order Cake,Order Pastry's. View Previous Orders, View Calender.
 */
public class BakeryOptions extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    ImageButton viewCalendar;
    ImageButton viewPreviousOrders;
    ImageButton pastryOrder;
    ImageButton cakeOrder;

    // like a constructor for the program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call the super constructor
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bakery_options);
        Log.d("tag","In onCreate");

        //Link Button objects and set the Listeners
        textView = findViewById(R.id.textView);
        viewCalendar = (ImageButton)findViewById(R.id.ViewCalendar_Button);
        viewPreviousOrders = (ImageButton)findViewById(R.id.ViewPreviousOrders_Button);
        pastryOrder = (ImageButton)findViewById(R.id.PastryOrder_Button);
        cakeOrder = (ImageButton)findViewById(R.id.CakeOrders_Button);
        viewCalendar.setOnClickListener(this);
        viewPreviousOrders.setOnClickListener(this);
        pastryOrder.setOnClickListener(this);
        cakeOrder.setOnClickListener(this);
    }

    /**
     * Action for clickable items
     * @param view the view to get id's from that were clicked
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.ViewCalendar_Button):

                Intent intent1 = new Intent(this, ViewCalendar.class);
                startActivity(intent1);
                break;

            case (R.id.ViewPreviousOrders_Button):
                Intent intent2 = new Intent(this, ViewPreviousOrders.class);
                startActivity(intent2);
                break;

            case (R.id.PastryOrder_Button):
                Intent intent3 = new Intent(this, PastryOrder.class);
                startActivity(intent3);
                break;

            case (R.id.CakeOrders_Button):
                Intent intent4 = new Intent(this, CakeOrder.class);
                startActivity(intent4);
                break;
        }
    }

}