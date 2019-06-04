package com.assignment.ecommerce.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.assignment.ecommerce.R;
import com.assignment.ecommerce.activities.ProductDetailActivity;
import com.assignment.ecommerce.database.dbdao.ProductTable;
import com.assignment.ecommerce.utils.Constants;

import java.util.ArrayList;

public class ProductAdapter extends
        RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<ProductTable> productsList;
    private Context ctx;
    public ProductAdapter(ArrayList<ProductTable> productsList, Context ctx) {
        this.productsList = productsList;
        this.ctx = ctx;
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.display_product, parent, false);
        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        final ProductTable product = productsList.get(position);
        holder.productName.setText(product.productName);
        holder.productsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(ctx, ProductDetailActivity.class);
                productIntent.putExtra(Constants.PRODUCT_ID, product.getProductID()+"");
                productIntent.putExtra(Constants.PRODUCT_NAME, product.getProductName());
                ctx.startActivity(productIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public LinearLayout productsLL;
        public ViewHolder(View itemView) {
            super(itemView);
            this.productName = (TextView) itemView.findViewById(R.id.productName);
            this.productsLL = (LinearLayout) itemView.findViewById(R.id.productsLL);
        }
    }
}
