package bakery.cake.bakeryorder.Model;

import java.util.List;

/**
 * Created by Kevin Lopez on 4/22/18.
 *
 * Source Used: Android Studio Tutorial - Order Foods Part 5: https://www.youtube.com/watch?v=nlQTN7vkc0c
 *
 * This is the request object that fills in the order request.
 */

public class Request {
    private String email; // customer's email
    private String name; //customer name
    private String Date; //date requeste
    private String Time; // time requested, round to nearest hour.
    private List<Order> confections; // foods

    public Request(String email, String name, String date, String time, List<Order> confections) {
        this.email = email;
        this.name = name;
        this.Date = date;
        this.Time = time;
        this.confections = confections;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public List<Order> getFoods() {
        return confections;
    }

    public void setFoods(List<Order> foods) {
        this.confections = confections;
    }
}


