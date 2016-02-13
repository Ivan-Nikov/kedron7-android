package co.centroida.kedron.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.centroida.kedron.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FrontActivityFragment extends Fragment {

    public FrontActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front, container, false);
    }
}
