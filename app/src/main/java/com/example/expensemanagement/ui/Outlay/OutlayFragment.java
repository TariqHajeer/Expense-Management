package com.example.expensemanagement.ui.Outlay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.expensemanagement.databinding.FragmentOutlayOwnerBinding;

public class OutlayFragment extends Fragment {
    private FragmentOutlayOwnerBinding binding;

    public OutlayFragment(@NonNull LayoutInflater inflater,
                          ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOutlayOwnerBinding.inflate(inflater, container, false);
    }
}
