package com.hfad.ad3lesson1_26.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.ad3lesson1_26.R;
import com.hfad.ad3lesson1_26.data.Card;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiHolder> {

    private EmojiGame game;
    private Listener listener;
    private Context context;

    public EmojiAdapter(EmojiGame game,
                        Listener listener,
                        Context context) {
        this.game = game;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public EmojiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new EmojiHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiHolder holder, int position) {
        holder.bind(game.getCards().get(position));
    }

    @Override
    public int getItemCount() {
        return game.getCards().size();
    }


    //◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉ViewHolder◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉◉
    class EmojiHolder extends RecyclerView.ViewHolder {

        private Listener listener;
        private TextView tvCard;
        private Animation fadeIn;


        public EmojiHolder(@NonNull View itemView,
                           Listener listener) {
            super(itemView);
            this.listener = listener;

            fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            tvCard = itemView.findViewById(R.id.tv_card);
        }

        public void bind(Card<String> card) {
            itemView.setOnClickListener(v -> {
                listener.cardClick(card);
                notifyDataSetChanged();
            });

            if (card.isFacedUp()) {

                tvCard.setBackgroundColor(Color.WHITE);
                tvCard.setText(card.getContent());

//            } else if (card.getContent() == null) {
//
//                tvCard.setBackgroundColor(Color.WHITE);
//                tvCard.setText("The game is over");
//                Log.e("TAG", "bind:" );

            } else {

                tvCard.setBackgroundColor(Color.BLUE);
                tvCard.setText("");

            }
        }
    }

    interface Listener {
        void cardClick(Card<String> card);
    }
}
