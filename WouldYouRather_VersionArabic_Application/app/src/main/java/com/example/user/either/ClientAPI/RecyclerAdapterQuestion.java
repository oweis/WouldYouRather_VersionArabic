package com.example.user.either.ClientAPI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.either.Model.Question;
import com.example.user.either.R;

import java.util.ArrayList;

/**
 * Created by user on 16/08/2016.
 */
public class RecyclerAdapterQuestion extends RecyclerView.Adapter<RecyclerAdapterQuestion.MyViewHolder> {

    ArrayList<Question> arrayList = new ArrayList<>();

    public RecyclerAdapterQuestion(ArrayList<Question> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.answer1.setText("Answer1 : " + arrayList.get(position).getAnswer1());
        holder.answer2.setText("Answer2 : " + arrayList.get(position).getAnswer2());
     //   holder.buttonIdQuestion.setTag(arrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView answer1, answer2;
        Button buttonIdQuestion;

        public MyViewHolder(View itemView) {
            super(itemView);

            answer1 = (TextView) itemView.findViewById(R.id.textAnswer1);
            answer2 = (TextView) itemView.findViewById(R.id.textAnswer2);
            //  buttonIdQuestion = (Button) itemView.findViewById(R.id.buttonIdFamily);
        }
    }


}
