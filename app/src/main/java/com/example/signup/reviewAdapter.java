package com.example.signup;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.ViewHolder> {
    Context ctx;

    Vector<Review> reviewVector;

    private OnUpdateClickListener updateClickListener;
    private OnDeleteClickListener deleteClickListener;

    public reviewAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public interface OnUpdateClickListener {
        void onUpdateClick(Review review);
    }

    public void setReviewVector(Vector<Review> reviewVector) {
        this.reviewVector = reviewVector;
    }

    public void setOnUpdateClickListener(OnUpdateClickListener listener) {
        updateClickListener = listener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Review review);
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        deleteClickListener = listener;
    }
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Resources resources = holder.itemView.getContext().getResources();
        String pictName = reviewVector.get(position).getGameImage();
        int resourceID = resources.getIdentifier(pictName, "drawable", holder.itemView.getContext().getPackageName());

        Review review = reviewVector.get(position);

        holder.gameImage.setImageResource(resourceID);
        holder.gameName.setText(review.getGameName());
        holder.userComment.setText(review.getUserComment());
        holder.username.setText(review.getUsername());

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateClickListener != null) {
                    updateClickListener.onUpdateClick(review);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(review);
                }
            }
        });
    }

    public int getItemCount() {
        return reviewVector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gameImage;
        TextView  gameName, userComment, username;
        Button updateButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.game_image_txt);
            gameName = itemView.findViewById(R.id.game_name_txt);
            userComment = itemView.findViewById(R.id.user_comment_txt);
            username = itemView.findViewById(R.id.username_txt);
            updateButton = itemView.findViewById(R.id.update_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    public void updateReview(Review review) {
        int index = reviewVector.indexOf(review);
        if (index != -1) {
            reviewVector.set(index, review);
            notifyItemChanged(index);
        }
    }
    public void deleteReview(Review review) {
        int index = reviewVector.indexOf(review);
        if (index != -1) {
            reviewVector.remove(index);
            notifyItemRemoved(index);
        }
    }
}



