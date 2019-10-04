package bakery.cake.bakeryorder;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import bakery.cake.bakeryorder.Database.Database;
import bakery.cake.bakeryorder.Model.Order;
import bakery.cake.bakeryorder.Model.Request;
import bakery.cake.bakeryorder.ViewHolder.CartAdapter;
import info.hoang8f.widget.FButton;

/**
 * Created By Kevin Lopez on 4/21/2018
 *
 * Source Used: Android Studio Tutorial - Order Foods Part 5: https://www.youtube.com/watch?v=nlQTN7vkc0c
 *
 * This class starts the cart page and allows the Orders to be placed into a request and sent
 * to the firebase server.
 *
 */
public class Cart extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase Init
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Initialize the the Recycler View to list Order objects in the cart
        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        txtTotalPrice = (TextView) findViewById(R.id.total);
        btnPlace = (FButton) findViewById(R.id.btnPlaceOrder);
        btnPlace.setOnClickListener(this);
        loadListConfection();
    }

    /**
     * Loads all confection orders in the cart
     */
    private void loadListConfection() {
        Database dbs = new Database(this);//cart = new Database(this).getCarts();
        cart = dbs.getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

       // Calculate Total Price
        int total = 0;
        for(Order order : cart){
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        }

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        txtTotalPrice.setText(fmt.format(total));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.btnPlaceOrder):
                showAlertDialog();
                Toast.makeText(Cart.this, "Hello" , Toast.LENGTH_SHORT).show();
                //Request(String email,
                // String name,
                // String date,
                // tring time,
                // List<Order> confections)
            break;

        }

    }

    /**
     * Starts a dialog box to Ask for the customer's email before
     * submitting the order
     *
     */
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more Step!");
        alertDialog.setMessage("Enter your email.");

        final EditText editEmail = new EditText(Cart.this);
        final EditText editName = new EditText(Cart.this);
        final EditText editDate = new EditText(Cart.this);
        final EditText editTime = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
        );

        editEmail.setLayoutParams(lp);
        editName.setLayoutParams(lp);
        editDate.setLayoutParams(lp);
        editTime.setLayoutParams(lp);
        alertDialog.setView(editEmail);
        //alertDialog.setView(editName);
        //alertDialog.setView(editDate);
        //alertDialog.setView(editTime);
        alertDialog.setIcon(R.drawable.shopping_cart_button);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Request request = new Request(
                       editEmail.getText().toString(),
                       editName.getText().toString(),
                       editDate.getText().toString(),
                       editTime.getText().toString(),
                       cart
               );

                // Submit to Firebase
                // Using System.CurrentMilli
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                //Delete Cart
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Order Submitted, Thank you!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}