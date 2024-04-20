package com.corecraft;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class s1_sliderAdapter extends SliderViewAdapter<s1_sliderAdapter.Holder> {

    int[][] shots;

    public s1_sliderAdapter(int[][] shots){
        this.shots = shots;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.s1_slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imgView.setImageResource(shots[position][0]);
        viewHolder.txtView.setText(shots[position][1]);



    }

    @Override
    public int getCount() {
        return shots.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{

        ImageView imgView ;
        TextView txtView ;
        public Holder(View itemView){
            super(itemView);
            imgView = itemView.findViewById(R.id.s1_sliderItem_img);
            txtView = itemView.findViewById(R.id.s1_sliderItem_txt);

        }
    }
}
