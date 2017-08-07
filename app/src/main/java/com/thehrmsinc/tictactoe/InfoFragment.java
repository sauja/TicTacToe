package com.thehrmsinc.tictactoe;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thehrmsinc.tictactoe.R;


public class InfoFragment extends Fragment {
    TextView textView_player1;
    TextView textView_player2;
    TextView textView_currentPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container, false);
        textView_player1=(TextView)view.findViewById(R.id.textView_player1);
        textView_player2=(TextView)view.findViewById(R.id.textView_player2);
        textView_currentPlayer=(TextView)view.findViewById(R.id.textView_currentPlayer);
        return view;
    }



    public void updatePlayer(int player)
    {
        if(player==0)
        {
            textView_currentPlayer.setText(textView_player1.getText());
            textView_player1.setTypeface(null,Typeface.BOLD);
            textView_player2.setTypeface(null,Typeface.NORMAL);
        }
        else
        {
            textView_currentPlayer.setText(textView_player2.getText());
            textView_player1.setTypeface(null,Typeface.NORMAL);
            textView_player2.setTypeface(null,Typeface.BOLD);
        }
    }

}
