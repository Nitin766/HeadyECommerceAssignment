package com.assignment.ecommerce.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.assignment.ecommerce.R;
import com.assignment.ecommerce.activities.ProductHomeActivity;
import com.assignment.ecommerce.database.dbdao.CategoryTable;
import com.assignment.ecommerce.utils.Constants;

import java.util.ArrayList;

public class CategoryAdapter extends
        RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList<CategoryTable> categoriesList;
    private Context ctx;
    public CategoryAdapter(ArrayList<CategoryTable> categoriesList, Context ctx) {
        this.categoriesList = categoriesList;
        this.ctx = ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.display_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CategoryTable category = categoriesList.get(position);
        holder.categoryName.setText(category.getCategoryName());
        //holder.imageView.setImageResource(category.getImgId());
        holder.categoriesLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(ctx, ProductHomeActivity.class);
                productIntent.putExtra(Constants.CATEGORY_ID, category.getCategoryID()+"");
                ctx.startActivity(productIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        public LinearLayout categoriesLL;
        public ViewHolder(View itemView) {
            super(itemView);
            this.categoryName = (TextView) itemView.findViewById(R.id.categoryName);
            this.categoriesLL = (LinearLayout) itemView.findViewById(R.id.categoriesLL);
        }
    }
}
