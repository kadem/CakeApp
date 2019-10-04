package bakery.cake.bakeryorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ShoppingCart extends AppCompatActivity implements View.OnClickListener{
    Button emailInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        emailInfo = findViewById(R.id.EmailInfo_Button);//button to go to email method

        emailInfo.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.EmailInfo_Button)://if the Email info is button is clicked start Email info
                Intent intent1 = new Intent(this, EmailInfo.class);
                startActivity(intent1);
                break;
        }
    }
}
