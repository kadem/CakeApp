package bakery.cake.bakeryorder.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.List;
import bakery.cake.bakeryorder.Model.Order;

/**
 * Created by Kevin Lopez on 4/21/18.
 *
 * This class Interacts with the BakeryOrder Database
 * which is sent to the Firebase Server.
 *
 * code Source: Android Studio Tutorial - Order Foods Part 5: https://www.youtube.com/watch?v=nlQTN7vkc0c
 */

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="BakeryOrder.db";
    private static final int DB_VER = 1;

    //constructor instatiate the database
    public Database(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    // Queries the BakeryOrder.db for Orders and returns a list of orders
    public List<Order> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"ProductName","ProductId","Quantity","Price"};
        String sqlTable= "OrderDetal";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db , sqlSelect,null,null,null,null, null);

        //list of orders
        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){

            //add each item to the order
            do{
                result.add( new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price"))
                        ));

            }while(c.moveToNext());

        }

        return result;
    }//end getCarts

    //adds the Item(s) to the cart
    public void addToCart(Order order){

        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("INSERT INTO OrderDetal(ProductId, ProductName,Quantity, Price) VALUES('%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice());

        db.execSQL(query);
    }

    //resets the cart to the initial state
    public void cleanCart(){

        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("DELETE FROM OrderDetal");

        db.execSQL(query);
    }

}