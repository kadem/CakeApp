package bakery.cake.bakeryorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by Kevin Lopez and Victoria Goon
 *
 * A view holder for the Confection
 *
 * Android using Firebase Tutorial 3: https://www.youtube.com/watch?v=k1RUOexThGs
 */
public class CreateOrder extends AppCompatActivity implements View.OnClickListener{
    Button pastry;
    Button cake;
    AppCompatImageButton shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        pastry = findViewById(R.id.Pastry_Button);
        cake = findViewById(R.id.Cake_Button);
        shoppingCart = findViewById(R.id.Shopping_Cart_Button);
        pastry.setOnClickListener(this);
        cake.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case(R.id.Pastry_Button):
                Intent intent1 = new Intent(this,PastryOrder.class);
                startActivity(intent1);
                break;
            case(R.id.Cake_Button):
                Intent intent2 = new Intent(this,CakeOrder.class);
                startActivity(intent2);
                break;
            case(R.id.Shopping_Cart_Button):
                Intent intent3 = new Intent(this, Cart.class);
                startActivity(intent3);
                break;
        }
    }
}


