package bakery.cake.bakeryorder;
import android.view.View;
/**
 * Created by Kevin Lopez on 4/21/18.
 *
 * Source Used: Android using Firebase Tutorial 2: https://www.youtube.com/watch?v=dJm7LACOn80
 */

/**
 * Helper interface to clickable items.
 */
public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
