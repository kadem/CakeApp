package bakery.cake.bakeryorder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//https://cdn.journaldev.com/wp-content/uploads/android/ViewPager.zip
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {//creates view on screen
        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {//removes view so
        collection.removeView((View) view);                        // new view can be displayed
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }//gets number of views

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {//gets views position
        ModelObject customPagerEnum = ModelObject.values()[position];

        return mContext.getString(customPagerEnum.getTitleResId());
    }

}
