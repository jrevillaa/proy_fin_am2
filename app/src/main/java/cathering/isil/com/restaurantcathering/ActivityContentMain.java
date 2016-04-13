package cathering.isil.com.restaurantcathering;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;
import cathering.isil.com.restaurantcathering.fragments.FragmentCartaPlatos;
import cathering.isil.com.restaurantcathering.fragments.FragmentGallery;
import cathering.isil.com.restaurantcathering.fragments.FragmentUbicanos;

/**
 * Created by helbert on 16/05/15.
 */
public class ActivityContentMain extends NavigationLiveo implements NavigationLiveoListener {

    public List<String> mListNameItem;

    @Override
    public void onUserInformation() {



        this.mUserName.setText(getString(R.string.local_name));
        this.mUserEmail.setText(getString(R.string.local_email));
        this.mUserPhoto.setImageResource(R.drawable.logo);
        this.mUserBackground.setImageResource(R.drawable.background_drawer);

        initImageLoader(getApplicationContext());

    }

    @Override
    public void onInt(Bundle savedInstanceState) {

        this.setNavigationListener(this);

        if (savedInstanceState == null)
            this.setDefaultStartPositionNavigation(1);


        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.menu_option1));
        mListNameItem.add(1, getString(R.string.menu_option2));
        mListNameItem.add(2, getString(R.string.menu_option3));
        mListNameItem.add(3, getString(R.string.menu_option4));
        mListNameItem.add(4, getString(R.string.menu_option5));
        mListNameItem.add(5, getString(R.string.menu_option6));
        mListNameItem.add(6, getString(R.string.menu_option7));
        mListNameItem.add(7, getString(R.string.menu_option8));

        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, 0);
        mListIconItem.add(1, R.drawable.icon_entrada);
        mListIconItem.add(2, R.drawable.icon_plato);
        mListIconItem.add(3, R.drawable.icon_postre);
        mListIconItem.add(4, R.drawable.icon_bebida);
        mListIconItem.add(5, 0);
        mListIconItem.add(6, R.drawable.ic_carta_black_24dp);
        mListIconItem.add(7, R.drawable.ic_carta_black_24dp);


        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(0);
        mListHeaderItem.add(5);

        SparseIntArray mSparseCounterItem = new SparseIntArray();
        mSparseCounterItem.put(1, 1);
        mSparseCounterItem.put(2, 2);
        mSparseCounterItem.put(3, 3);
        mSparseCounterItem.put(4, 4);

        this.setFooterNavigationVisible(false);
        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.closeDrawer();
    }

    @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle bundle = new Bundle();

        Fragment mFragment=null;

        switch(position){
            case 1:
                bundle.putString(getString(R.string.tipo), getString(R.string.entrada));
                mFragment = new FragmentCartaPlatos();
                mFragment.setArguments(bundle);
                loadFragment(mFragment,layoutContainerId,getString(R.string.menu_option2));
                break;
            case 2:
                bundle.putString(getString(R.string.tipo), getString(R.string.fondo));
                mFragment = new FragmentCartaPlatos();
                mFragment.setArguments(bundle);
                loadFragment(mFragment,layoutContainerId,getString(R.string.menu_option3));
                break;
            case 3:
                bundle.putString(getString(R.string.tipo), getString(R.string.bebida));
                mFragment = new FragmentCartaPlatos();
                mFragment.setArguments(bundle);
                loadFragment(mFragment,layoutContainerId,getString(R.string.menu_option4));
                break;
            case 4:
                bundle.putString(getString(R.string.tipo), getString(R.string.postre));
                mFragment = new FragmentCartaPlatos();
                mFragment.setArguments(bundle);
                loadFragment(mFragment,layoutContainerId,getString(R.string.menu_option5));
                break;
            case 6:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mFragment = new FragmentGallery();
                loadFragment(mFragment,layoutContainerId,getString(R.string.menu_option7));
                break;

            case 7:
                mFragment = new FragmentUbicanos();
                loadFragment(mFragment,layoutContainerId,getString(R.string.menu_option8));
                break;
        }

    }


    private void loadFragment(Fragment fm,int layoutContainerId, String title){

        FragmentManager mFragmentManager = getSupportFragmentManager();

        if (fm != null){
            mFragmentManager.beginTransaction().replace(layoutContainerId, fm).commit();
        }

        this.setTitle(title);

    }


    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int position, boolean visible) {


    }

    @Override
    public void onClickUserPhotoNavigation(View v) {

    }

    @Override
    public void onClickFooterItemNavigation(View v) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();

        ImageLoader.getInstance().init(config.build());
    }

}