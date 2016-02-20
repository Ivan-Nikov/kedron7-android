package co.centroida.kedron.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.borax12.materialdaterangepicker.date.DatePickerDialog;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.fragments.DebtsListFragment;
import co.centroida.kedron.fragments.DepositListFragment;
import co.centroida.kedron.fragments.RefreshListFragment;
import co.centroida.kedron.fragments.PaymentsListFragment;

public class HouseholdBookActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener{

    ViewPager mViewPager;
    PaymentFragmentAdapter fragmentAdapter;

    List<RefreshListFragment> cashbookFragments;
    DebtsListFragment debtsListFragment;
    PaymentsListFragment paymentsListFragment;
    DepositListFragment depositListFragment;

    public enum HouseholdScreen{
        Payments, Debts, Deposits
    }

    public class PaymentFragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList;

        public PaymentFragmentAdapter(List<Fragment> fragmentList, FragmentManager fm) {
            super(fm);
            this.fragmentList = fragmentList;
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
            switch (HouseholdScreen.values()[position]) {
                case Debts:
                    choice = getString(R.string.debts_fragment_name);
                    break;
                case Payments:
                    choice = getString(R.string.payments_fragment_name);
                    break;
                case Deposits:
                    choice = getString(R.string.deposits_fragment_name);
                    break;
                default:
                    choice = "HMM";
                    break;
            }
            return choice;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_householdbook);
        Toolbar toolbar = (Toolbar) findViewById(R.id.cashbook_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //TODO: Meh

        paymentsListFragment = new PaymentsListFragment();
        debtsListFragment = new DebtsListFragment();
        depositListFragment = new DepositListFragment();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(paymentsListFragment);
        fragments.add(debtsListFragment);
        fragments.add(depositListFragment);

        fragmentAdapter = new PaymentFragmentAdapter(fragments, getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.payment_pager);
        mViewPager.setAdapter(fragmentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_householdbook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.cashbook_date:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        HouseholdBookActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Select dates");
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,
                          int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        Calendar lower = Calendar.getInstance();
        lower.set(year, monthOfYear, dayOfMonth);
        Calendar upper = Calendar.getInstance();
        upper.set(yearEnd, monthOfYearEnd, dayOfMonthEnd);
        paymentsListFragment.updateList(lower.getTime(), upper.getTime());
    }

}
