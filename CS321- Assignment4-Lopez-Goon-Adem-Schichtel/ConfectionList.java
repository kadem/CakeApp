package bakery.cake.bakeryorder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import bakery.cake.bakeryorder.Model.Confection;
import bakery.cake.bakeryorder.ViewHolder.ConfectionViewHolder;

/**
 * Created By Kevin Lopez on 4/21/2018
 *
 * Source Used : Android Studio Tutorial - Order Foods Part 4: https://www.youtube.com/watch?v=T19qTLVDFV0&t=70s
 *
 * This class starts a recycler view for each type of confection item.
 * When a confection item is clicked the confectionDetail class is started
 * in a new Intent

 */
public class ConfectionList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference confectionList;
    String categoryId = "";

    FirebaseRecyclerAdapter<Confection, ConfectionViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confection_list);


        //Firebase
        database = FirebaseDatabase.getInstance();
        confectionList = database.getReference("Confection");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_confection);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent Here
        if(getIntent()!=null){
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty() && categoryId != null){
            loadListConfection(categoryId);
        }
    }

    /**
     * Loads the Recycler view list of confection from the Firebase Database
     *
     * @param categoryId
     */
    private void loadListConfection(String categoryId) {
        adapter = new FirebaseRecyclerAdapter< Confection, ConfectionViewHolder >(Confection.class,
                R.layout.confection_item,
                ConfectionViewHolder.class,
                confectionList.orderByChild("MenuId").equalTo(categoryId) //select from confectionss by MenuId
                ) {
            @Override
            protected void populateViewHolder( ConfectionViewHolder viewHolder, Confection model, int position ) {
                viewHolder.confection_name1.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.confection_image);

                final Confection clickItem  = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start New Activity

                        Intent confectionDetail = new Intent(ConfectionList.this,ConfectionDetail.class);
                        confectionDetail.putExtra("ConfectionId",adapter.getRef(position).getKey()); // send confection Id to new activity
                        Toast.makeText(ConfectionList.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();
                        startActivity(confectionDetail);
                    }
                });
            }
        };
        //Set Adapter
        recyclerView.setAdapter(adapter);
    }
}
