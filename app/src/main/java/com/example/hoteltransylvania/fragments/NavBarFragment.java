package com.example.hoteltransylvania.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoteltransylvania.activities.InicioActivity;
import com.example.hoteltransylvania.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBarFragment extends Fragment {

    BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaFragment = inflater.inflate(R.layout.fragment_nav_bar, container, false);

        bottomNav = vistaFragment.findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    InicioActivity.irFragment("home");
                    break;
                case R.id.perfil:
                    InicioActivity.irFragment("perfil");
                    break;
                default:
                    break;
            }
            return true;
        });

        return vistaFragment;
    }
}