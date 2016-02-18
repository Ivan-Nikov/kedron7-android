package co.centroida.kedron.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.fragments.DebtsListFragment;
import co.centroida.kedron.fragments.PaymentsListFragment;

public class PaymentsActivity extends AppCompatActivity {

    ViewPager mViewPager;
    PaymentFragmentAdapter fragmentAdapter;



    public class PaymentFragmentAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragmentList;

        public PaymentFragmentAdapter(FragmentManager fm) {
            super(fm);
            fragmentList = new ArrayList<>();
            fragmentList.add(new DebtsListFragment());
            fragmentList.add(new PaymentsListFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getItem(position).getClass().getSimpleName();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fragmentAdapter = new PaymentFragmentAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.payment_pager);
        mViewPager.setAdapter(fragmentAdapter);

        final ActionBar actionBar = getActionBar();



    }

}


//// Since this is an object collection, use a FragmentStatePagerAdapter,
//// and NOT a FragmentPagerAdapter.
//public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
//    public DemoCollectionPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int i) {
//        Fragment fragment = new DemoObjectFragment();
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public int getCount() {
//        return 100;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return "OBJECT " + (position + 1);
//    }
//}
//
//// Instances of this class are fragments representing a single
//// object in our collection.
//public static class DemoObjectFragment extends Fragment {
//    public static final String ARG_OBJECT = "object";
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        // The last two arguments ensure LayoutParams are inflated
//        // properly.
//        View rootView = inflater.inflate(
//                R.layout.fragment_collection_object, container, false);
//        Bundle args = getArguments();
//        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
//                Integer.toString(args.getInt(ARG_OBJECT)));
//        return rootView;
//    }
//}
