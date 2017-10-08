package com.example.sashok.jsoupparserapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sashok.jsoupparserapp.R;
import com.example.sashok.jsoupparserapp.interfaces.Folderlistener;
import com.example.sashok.jsoupparserapp.model.Folder;

import java.util.List;

/**
 * Created by sashok on 1.10.17.
 */

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.FolderHolder> {

    private Context mContext;
    private List<Folder> folderList;
    private Folderlistener folderlistener;

    public FolderListAdapter(Context mContext, List<Folder> folderList, Folderlistener folderlistener) {
        this.mContext = mContext;
        this.folderlistener = folderlistener;
        this.folderList = folderList;
    }

    @Override
    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.folder_item, parent, false);

        // Return a new holder instance
        FolderHolder viewHolder = new FolderHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FolderHolder holder, int position) {
        final Folder cur_folder = folderList.get(position);
        List<String> folder_names = cur_folder.get_rus_names();
        String names = new String();
        for (String string : folder_names) {
            names += string + ",";
        }
        if (names.length() >= 1) names = names.substring(0, names.length() - 1);
        holder.folder_name.setText(names);
        Glide.with(mContext).load(cur_folder.getFolderURL()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).error(R.drawable.football_2778583_960_720).dontAnimate().into(holder.forder_image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folderlistener.onFolderClicked(cur_folder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class FolderHolder extends RecyclerView.ViewHolder {
        private TextView folder_name;
        private ImageView forder_image;
        private CardView cardView;

        public FolderHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.card_view);
            folder_name = (TextView) itemView.findViewById(R.id.folder_name);
            forder_image = (ImageView) itemView.findViewById(R.id.folder_image);
        }
    }
}
