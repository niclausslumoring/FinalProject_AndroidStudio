package com.example.signup.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.Review;
import com.example.signup.reviewAdapter;
import com.example.signup.reviewHelper;

import java.util.Vector;

public class FragmentReview extends Fragment implements reviewAdapter.OnUpdateClickListener, reviewAdapter.OnDeleteClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        RecyclerView reviewRecycler = view.findViewById(R.id.review_rv);
        Vector<Review> review = new Vector<>();
        reviewHelper rh = new reviewHelper(getContext());

        rh.open();
        review.addAll(rh.viewReview());
        rh.close();

        reviewAdapter review_adapter = new reviewAdapter(getContext());
        review_adapter.setReviewVector(review);

        reviewRecycler.setAdapter(review_adapter);
        reviewRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));

        review_adapter.setOnUpdateClickListener(this);
        review_adapter.setOnDeleteClickListener(this);

        return view;
    }

    public void onUpdateClick(Review review) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update Review");

        final EditText input = new EditText(getContext());
        input.setText(review.getUserComment());
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updatedComment = input.getText().toString().trim();

                if (updatedComment.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a comment", Toast.LENGTH_SHORT).show();
                    return;
                }

                review.setUserComment(updatedComment);


                reviewHelper rh = new reviewHelper(getContext());
                rh.open();
                rh.updateReview(review);
                rh.close();

                reviewAdapter review_adapter = (reviewAdapter) ((RecyclerView) getView().findViewById(R.id.review_rv)).getAdapter();
                review_adapter.updateReview(review);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void onDeleteClick(Review review) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete Review");

        builder.setMessage("Are you sure you want to delete this review?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reviewHelper rh = new reviewHelper(getContext());
                rh.open();
                rh.deleteReview(review);
                rh.close();

                reviewAdapter review_adapter = (reviewAdapter) ((RecyclerView) getView().findViewById(R.id.review_rv)).getAdapter();
                review_adapter.deleteReview(review);

                Toast.makeText(getContext(), "Review deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
