package bakery.cake.bakeryorder;
////https://cdn.journaldev.com/wp-content/uploads/android/ViewPager.zip
public enum ModelObject {

    RED(R.string.red, R.layout.first),
    BLUE(R.string.blue, R.layout.second),
    GREEN(R.string.green, R.layout.third);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
