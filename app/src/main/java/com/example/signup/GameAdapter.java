package com.example.signup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signup.fragments.FragmentReview;

import java.util.Vector;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    Context ctx;
    Vector<Game> gameVector;

    public GameAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setGameVector(Vector<Game> gameVector) {
        this.gameVector = gameVector;
    }

    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {

        Resources resources = holder.itemView.getContext().getResources();
        String pictName = gameVector.get(position).getGameImage();
        int resourceID = resources.getIdentifier(pictName, "drawable", holder.itemView.getContext().getPackageName());

        holder.gameID = gameVector.get(position).getGameID();
        holder.gameImage.setImageResource(resourceID);
        holder.gameName.setText(gameVector.get(position).getGameName());
        holder.gameGenre.setText(gameVector.get(position).getGameGenre());
        holder.gamePrice.setText(String.valueOf(gameVector.get(position).getGamePrice()));

        holder.viewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();
                Game game = gameVector.get(itemPosition);
                String rating = String.valueOf(game.getGameRating());
                String description = game.getGameDesc();

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle(game.getGameName());
                builder.setMessage("Rating: " + rating + "\n\nDescription:\n" + description);

                LayoutInflater inflater = LayoutInflater.from(ctx);
                View commentLayout = inflater.inflate(R.layout.comment_layout, null);

                final EditText commentEditText = commentLayout.findViewById(R.id.comment_edit_text);

                builder.setView(commentLayout);

                builder.setPositiveButton("Add Comment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = commentEditText.getText().toString();

                        int gameId = game.getGameID();

                        SharedPreferences sharedPreferences = ctx.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        String username = sharedPreferences.getString("username", "");

                        userHelper uhelper = new userHelper(ctx);
                        uhelper.open();
                        User user = uhelper.getUserByUsername(username) ;

                        uhelper.close();
                        Integer userId = user.getUserID();
                        reviewHelper reviewHelper = new reviewHelper(ctx);
                        reviewHelper.open();
                        reviewHelper.insertReview(gameId, userId, comment);
                        reviewHelper.close();
                        notifyDataSetChanged();

                        Toast.makeText(ctx, "Comment added successfully!", Toast.LENGTH_SHORT).show();
                    }
                });


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return gameVector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameName, gameGenre, gamePrice;
        Button viewMoreBtn;

        ImageView gameImage;
        Integer gameID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameImage = itemView.findViewById(R.id.game_image_txt);
            gameName = itemView.findViewById(R.id.game_name_txt);
            gameGenre = itemView.findViewById(R.id.game_genre_txt);
            gamePrice = itemView.findViewById(R.id.game_price_txt);
            viewMoreBtn = itemView.findViewById(R.id.viewMoreButton);
        }
    }
}