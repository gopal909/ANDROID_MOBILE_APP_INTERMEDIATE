package com.gopal.gopalagentapptest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gopal.gopalagentapptest.R;
import com.gopal.gopalagentapptest.model.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<Result> mNicePlaces = new ArrayList<>();
    private Context mContext;
    private List<Result> listFiltered;


    public RecyclerAdapter(Context context, List<Result> results) {
        mNicePlaces = results;
        mContext = context;
        this.listFiltered = results;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // Set the name of the 'Result'
        ((ViewHolder)viewHolder).mName.setText(mNicePlaces.get(i).getTrackName());
        ((ViewHolder)viewHolder).text_Artistname.setText(mNicePlaces.get(i).getArtistName());
        ((ViewHolder)viewHolder).view.setBackgroundColor(mNicePlaces.get(i).isSelected() ? Color.CYAN : Color.WHITE);

        ((ViewHolder)viewHolder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNicePlaces.get(i).setSelected(!mNicePlaces.get(i).isSelected());
                ((ViewHolder)viewHolder).view.setBackgroundColor(mNicePlaces.get(i).isSelected() ? Color.CYAN : Color.WHITE);
            }
        });


        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNicePlaces.get(i).getArtworkUrl100())
                .into(((ViewHolder)viewHolder).mImage);
    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    mNicePlaces = listFiltered;
                } else {
                    List<Result> filteredList = new ArrayList<>();
                    for (Result row : mNicePlaces) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTrackName().toLowerCase().contains(charString.toLowerCase()) || row.getArtistName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mNicePlaces = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mNicePlaces;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mNicePlaces = (ArrayList<Result>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mImage;
        private View view;
        private TextView mName, text_Artistname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.text_name);
            text_Artistname = itemView.findViewById(R.id.text_Artistname);
        }
    }
}
