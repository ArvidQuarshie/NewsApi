package adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import model.Article;
import com.example.arvidquarshie.newsapiapp.R;

import java.util.List;

/**
 * Created by arvid.quarshie on 5/7/2017.
 * POJO object for the Articles
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articleList;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, description;
        public ImageView urlToImage;
        public View mView;


        public MyViewHolder(View view) {
            super(view);
            mView = view;
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title3);
            description = (TextView) view.findViewById(R.id.description3);


//            urlToImage = (ImageView) view.findViewById(R.id.urlToImage);

        }

        @Override
        public void onClick(View v) {
            Log.d("ArticlesAdapter:", "onClick " + getPosition() + " " + articleList.size());

        }


    }


    public ArticlesAdapter(List<Article> articleList) {
        this.articleList = articleList;
        Log.v("articleList", "");
    }

    @Override
    public ArticlesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_row, parent, false);
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.MyViewHolder holder, int position) {
        ;
        final Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = article.getTitle();
                String description = article.getDescription();


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

        //to be used for images
//        Picasso.with(mContext)
//                .load(IMAGE_URL)
//                .resize(50, 50)
//                .centerCrop()
//                .into(holder.urlToImage);


    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }
}