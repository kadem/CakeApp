package bakery.cake.bakeryorder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created By Kevin Lopez
 *
 * Source Used: Android Studio Tutorial For Beginners - 1 Edureka: https://www.youtube.com/watch?v=ZLNO2c7nqjw
 * Android Studio Tutorial For Beginners - 2 Edureka: https://www.youtube.com/watch?v=D-iqMlLOrec
 *
 * This class selects which bakery to order from.
 */
public class SelectBakeryActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button bakery1;
    Button bakery2;

    // like a constructor for the program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call the super constructor
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_bakery);
        textView = findViewById(R.id.textView);
        bakery1 = findViewById(R.id.Bakery1_Button);
        bakery2 = findViewById(R.id.Bakery2_Button);
        bakery1.setOnClickListener(this);
        bakery2.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.Bakery1_Button):
                Intent intent1 = new Intent(this, BakeryOptions.class);
                startActivity(intent1);
                break;

            case (R.id.Bakery2_Button):
                Intent intent2 = new Intent(this, BakeryOptions.class);
                startActivity(intent2);
                break;
        }
    }

}
