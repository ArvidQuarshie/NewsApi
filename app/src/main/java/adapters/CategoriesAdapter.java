package adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Categories;
import com.example.arvidquarshie.newsapiapp.R;

import java.util.List;

/**
 * Created by arvid.quarshie on 5/8/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<Categories> categoryList;
    Context mContext;
    public String NAME, DESC, CAT;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, description, category;
        public View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.name);
            category = (TextView) view.findViewById(R.id.category);
            description = (TextView) view.findViewById(R.id.description3);

        }

        @Override
        public void onClick(View v) {
            Log.d("ArticlesAdapter:", "onClick " + getPosition() + " " + categoryList.size());
            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Alert message to be shown");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


    public CategoriesAdapter(List<Categories> categoryList) {
        this.categoryList = categoryList;
        Log.v("categoryList", "");
    }

    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_item_row, parent, false);
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.MyViewHolder holder, int position) {
        final Categories categories = categoryList.get(position);
        holder.name.setText(categories.getName());
        holder.category.setText(categories.getCategory());
        holder.description.setText(categories.getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = categories.getName();
                String description = categories.getDescription();

//populate the dialog with content when clicked
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                alertDialog.setTitle(title);
                alertDialog.setMessage(description);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}