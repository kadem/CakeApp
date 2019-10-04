package bakery.cake.bakeryorder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import bakery.cake.bakeryorder.Database.Database;
import bakery.cake.bakeryorder.Model.Confection;
import bakery.cake.bakeryorder.Model.Order;

/**
 *
 * Create By Kevin Lopez on 4/21/2018
 *
 * This class Displays the details of non-cake confection items and
 * allows the user to add up to 20 each particular item to the cart
 *
 * Android using Firebase Tutorial 3: https://www.youtube.com/watch?v=k1RUOexThGs
 */
public class ConfectionDetail extends AppCompatActivity implements View.OnClickListener {

    TextView confection_name, confection_price, confection_description;
    ImageView confection_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton shoppingCartBtn;
    ElegantNumberButton numberButton;

    String confectionId="";

    FirebaseDatabase database;
    DatabaseReference confections;

    Confection currentConfection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confection_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        confections = database.getReference("Confection");

        //Init view
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        shoppingCartBtn = findViewById(R.id.Shopping_Cart_Button_C);
        shoppingCartBtn.setOnClickListener(this);

        confection_description = (TextView) findViewById(R.id.confection_description);
        confection_name = (TextView) findViewById(R.id.confection_name);
        confection_price = (TextView) findViewById(R.id.confection_price);
        confection_image = (ImageView) findViewById(R.id.img_confection);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //get Confection Id from Intent
        if(getIntent()!= null) {
           // Log.d("tag","In confection Detail getIntent null");
            confectionId = getIntent().getStringExtra("ConfectionId");
        }
        if(!confectionId.isEmpty()){
            getDetailConfection(confectionId);
        }


    }

    /**
     * This method gets the detail about the confection image that was clicked on
     *
     * @param confectionId the confection to get the detail from
     */
    private void getDetailConfection(String confectionId) {
        confections.child(confectionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                currentConfection = dataSnapshot.getValue(Confection.class);

                //Set Image
                Picasso.with(getBaseContext()).load(currentConfection.getImage()).into(confection_image);
                collapsingToolbarLayout.setTitle(currentConfection.getName());
                confection_price.setText(currentConfection.getPrice());
                confection_name.setText(currentConfection.getName());
                confection_description.setText(currentConfection.getDescription());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Adds the item and quantity with price to the cart in the Database.
     *
     * @param view the view to perform action on the clickable object
     */
    @Override
    public void onClick(View view) {
        new Database(getBaseContext()).addToCart(new Order(
                confectionId,
                currentConfection.getName(),
                numberButton.getNumber(),
                currentConfection.getPrice()
        ));
        Toast.makeText(ConfectionDetail.this,"Added to Cart", Toast.LENGTH_SHORT).show();
    }
}
