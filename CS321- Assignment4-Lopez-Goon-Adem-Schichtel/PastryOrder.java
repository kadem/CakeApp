package bakery.cake.bakeryorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;

import bakery.cake.bakeryorder.Model.Category;
import bakery.cake.bakeryorder.ViewHolder.MenuViewHolder;

public class PastryOrder extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase database;
    DatabaseReference category;
    AppCompatImageButton  shoppingCart;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);

        //Load Database
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        shoppingCart = findViewById(R.id.Shopping_Cart_Button_P);
        shoppingCart.setOnClickListener(this);

        //Load Menu
        recycler_menu = (RecyclerView) findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        loadMenu();

    }

    private void loadMenu(){
         adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,
                 R.layout.menu_item,
                 MenuViewHolder.class,
                 category){
                @Override
                protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position){
                            viewHolder.txtMenuName.setText(model.getName());
                            Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                            final Category clickItem = model;

                    Log.d("tag","In Pastry Order populateViewHolder, txtMenuName: "+ viewHolder.txtMenuName.toString());
                            viewHolder.setItemClickListener(new ItemClickListener() {

                               public void onClick(View view, int position, boolean isLongClick){
                                   //Get CategoryId and send to new Activity
                                   Intent confectionList = new Intent(PastryOrder.this,ConfectionList.class);
                                   //Firebase Adapter is the  key, just get key of this item
                                   confectionList.putExtra("CategoryId",adapter.getRef(position).getKey());
                                   //Then Make a Toast!
                                   Toast.makeText(PastryOrder.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();
                                   startActivity(confectionList);

                                }
                            });
                }


        };
        recycler_menu.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.Shopping_Cart_Button_P):
               Intent intent = new Intent(this, Cart.class);
                startActivity(intent);
                break;
        }
    }
}
