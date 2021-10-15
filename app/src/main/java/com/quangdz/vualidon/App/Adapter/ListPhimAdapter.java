package com.quangdz.vualidon.App.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.quangdz.vualidon.App.Admin.NhapPhimActivity;
import com.quangdz.vualidon.App.ThongTinPhimActivity;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

import java.util.ArrayList;
import java.util.List;

public class ListPhimAdapter extends ArrayAdapter<Phim> {

    List<Phim> phimArrayList;
    DatabaseDBContext dbContext;

    public ListPhimAdapter(Context context, ArrayList<Phim> phimArrayList) {
        super(context, 0, phimArrayList);
        this.phimArrayList = phimArrayList;
        dbContext = new DatabaseDBContext();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_phim, parent, false);
            ImageView imageView = convertView.findViewById(R.id.img_poster);
            TextView name_vi = convertView.findViewById(R.id.tv_name_vi);
            TextView name_en = convertView.findViewById(R.id.tv_name_en);
            Phim phim = phimArrayList.get(position);
            String urlImg = String.format("https://img.youtube.com/vi/%s/mqdefault.jpg", phim.getId());
            Glide.with(getContext()).load(urlImg).into(imageView);
            name_vi.setText(phim.getTenphimtv());
            name_en.setText(phim.getTenphimta());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.phim = phimArrayList.get(position);
                    view.getContext().startActivity(new Intent(view.getContext(), ThongTinPhimActivity.class));
                }
            });
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (Common.taiKhoan.isAdmin()) {
                        Common.phim = phimArrayList.get(position);
                        view.getContext().startActivity(new Intent(view.getContext(), NhapPhimActivity.class));
                    }
                    return false;
                }
            });
        }

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<Phim> suggestions = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                suggestions.addAll(phimArrayList);
            } else {
                String filterPartern = charSequence.toString().toLowerCase().trim();
                for (Phim phim : phimArrayList
                ) {
                    if (phim.getTenphimtv().toLowerCase().contains(filterPartern) ||
                            phim.getTenphimta().toLowerCase().contains(filterPartern) ||
                            phim.getDienvien().toLowerCase().contains(filterPartern) ||
                            phim.getTheloai().toLowerCase().contains(filterPartern) ||
                            phim.getQuocgia().toLowerCase().contains(filterPartern)
                    ) {
                        suggestions.add(phim);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}


