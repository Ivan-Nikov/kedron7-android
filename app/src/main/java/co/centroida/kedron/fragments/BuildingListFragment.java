package co.centroida.kedron.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ListView;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.BuildingsAdapter;

/**
 * Created by rimmustafin on 2/15/16.
 */
public class BuildingListFragment extends ListFragment {

    BuildingsAdapter buildingsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildingsAdapter = new BuildingsAdapter(getActivity(), 0);

        setListAdapter(buildingsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}
