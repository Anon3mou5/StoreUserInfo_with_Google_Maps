package com.example.task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class contactFragment extends Fragment {
    PageViewModel pageViewModel;
    private static final String ARG_SECTION_NUMBER = "section_number";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.imageui, container, false);
        LinearLayoutManager lm = new LinearLayoutManager(v.getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        Button b = v.findViewById(R.id.pickan);
        CardView cv = v.findViewById(R.id.cardView);
        final ImageView photsent = v.findViewById(R.id.photosent);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mimeTypes = {"image/jpeg", "image/png"};
                final Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        .setType("image/*")
                        .putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(
                        Intent.createChooser(
                                pickIntent,
                                "Select Image from here..."),
                        123);
            }
        });
return v;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,
                resultCode,
                data);
        final ImageView photsent = getActivity().findViewById(R.id.photosent);
        Button b = getActivity().findViewById(R.id.pickan);

        CardView cv = getActivity().findViewById(R.id.cardView);
        photsent.setVisibility(View.VISIBLE);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == 123
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            Uri filePath = data.getData();
            try {
                b.setVisibility(View.INVISIBLE);
             cv.setVisibility(View.VISIBLE);
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getActivity().getContentResolver(),
                                filePath);
                photsent.setImageBitmap(bitmap);
                ;
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }

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
