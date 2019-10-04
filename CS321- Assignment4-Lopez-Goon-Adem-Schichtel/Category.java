package bakery.cake.bakeryorder.Model;
/**
 * Created by Kevin Lopez on 4/20/18.
 *
 * Android using Firebase Tutorial 2: https://www.youtube.com/watch?v=dJm7LACOn80
 *
 * The serves as the category of items in the database.
 */

public class Category {
    private String Name;
    private String Image;

    public Category(){}

    public Category(String name, String image){
        this.Name = name;
        this.Image = image;
    }

    public String getName() {
           return this.Name;
    }

    public void setName(String name){
        Name = name;
    }

    public String getImage(){
        return this.Image;

    }
}
