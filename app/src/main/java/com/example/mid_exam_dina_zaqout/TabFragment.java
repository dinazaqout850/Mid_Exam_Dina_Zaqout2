package com.example.mid_exam_dina_zaqout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mid_exam_dina_zaqout.databinding.FragmentTabBinding;

/**
 * One fragment used for the three tabs (First, Second, Third).
 * The tab name is sent to the fragment using newInstance() and a Bundle.
 */
public class TabFragment extends Fragment {

    // Key used to find the tab name inside the Bundle.
    private static final String ARG_TEXT = "text";

    private String mText;

    public TabFragment() {
        // Required empty public constructor
    }

    // Factory method: creates the fragment and puts the tab name in a Bundle.
    public static TabFragment newInstance(String text) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTabBinding binding = FragmentTabBinding.inflate(inflater, container, false);
        binding.textViewTab.setText(mText + " Tab");
        return binding.getRoot();
    }
}