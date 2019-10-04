package bakery.cake.bakeryorder;

//https://www.foodnetwork.com/recipes/food-network-kitchen/french-macarons-3362691
//https://www.tastemade.com/videos/chocolate-snowflake-mousse-cake
//http://www.foodandwine.com/recipes/rainbow-cake

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ViewPreviousOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_main);

        //https://cdn.journaldev.com/wp-content/uploads/android/ViewPager.zip
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));//creates slider
    }

}
