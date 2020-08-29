package com.example.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class homeacti extends Fragment{
    boolean isloaded=false;
    List<people> obj;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;


    public homeacti(List<people> obj) {
        this.obj = obj;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.cardrecycle, container, false);
        LinearLayoutManager lm = new LinearLayoutManager(v.getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView recycle = v.findViewById(R.id.recycle);
        peopleAdapter adapter = new peopleAdapter(obj);
        recycle.setLayoutManager(lm);
        recycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
}