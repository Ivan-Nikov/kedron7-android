package co.centroida.kedron.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.fragments.DebtsListFragment;
import co.centroida.kedron.fragments.DepositListFragment;
import co.centroida.kedron.fragments.PaymentsListFragment;

public class CashBookActivity extends AppCompatActivity {

    ViewPager mViewPager;
    PaymentFragmentAdapter fragmentAdapter;

    public class PaymentFragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList;

        public PaymentFragmentAdapter(FragmentManager fm) {
            super(fm);
            fragmentList = new ArrayList<>();
            fragmentList.add(new DebtsListFragment());
            fragmentList.add(new PaymentsListFragment());
            fragmentList.add(new DepositListFragment());
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
            String choice = null;
            switch (position){
                case 0:
                    choice = getString(R.string.debts_fragment_name);
                    break;
                case 1:
                    choice = getString(R.string.payments_fragment_name);
                    break;
                case 2:
                    choice = getString(R.string.deposits_fragment_name);
                    break;
                default:
                    choice = "NONE";
                    break;
            }
            return (CharSequence)choice;
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
