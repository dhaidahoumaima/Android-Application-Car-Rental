package com.example.oumaima.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserList extends ArrayAdapter<Voiture> {
    private Activity context;
    private List<Voiture> usersList;

    public UserList(Activity context, List<Voiture> usersList){
        super(context, R.layout.list_layout, usersList);
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisiViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) lisiViewItem.findViewById(R.id.textView);
        TextView textViewType = (TextView) lisiViewItem.findViewById(R.id.textView2);
        TextView textViewTypeee = (TextView) lisiViewItem.findViewById(R.id.textView4);
        TextView textViewTypeee2 = (TextView) lisiViewItem.findViewById(R.id.textView12);

        TextView textViewTypeee22 = (TextView) lisiViewItem.findViewById(R.id.textView13);
        ImageView imgv=(ImageView) lisiViewItem.findViewById(R.id.imageView) ;


        Voiture voi = usersList.get(position);

        textViewName.setText(voi.getName());
        textViewType.setText(voi.getMarque());
        textViewTypeee.setText(voi.getPrice());
        textViewTypeee2.setText(voi.getPhone());
        textViewTypeee22.setText(voi.getDescription());


        String url=voi.getImage();
        String strOut = url.substring(36);
        String f="https://firebasestorage.googleapis.com/v0/b/applicatioon-1af4c.appspot.com/o/"+strOut+"?alt=media";
        //Picasso.with(context).load(user.getImage()).into(imgv);
        Picasso.with(context)
                .load(f)
                .placeholder(R.drawable.common_full_open_on_phone)   // optional
                .error(R.drawable.common_google_signin_btn_icon_dark)      // optional
                .resize(895,900)                        // optional
                .into(imgv);


        return lisiViewItem;
    }

}
