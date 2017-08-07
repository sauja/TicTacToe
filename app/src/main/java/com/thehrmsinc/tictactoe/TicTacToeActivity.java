package com.thehrmsinc.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TicTacToeActivity extends AppCompatActivity implements TicTacToeActivityFragment.SendCurrentPlayer {
    private static final String TAG=TicTacToeActivity.class.getName();
    TicTacToeActivityFragment ticTacToeActivityFragment;
    InfoFragment infoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        ticTacToeActivityFragment= (TicTacToeActivityFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        infoFragment= (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tic_tac_toe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {

            case R.id.action_reset:
                Log.d(TAG, "onOptionsItemSelected: Game Restart");
                ticTacToeActivityFragment.initGame();
                ticTacToeActivityFragment.updateView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //implemented TicTacToeActivityFragment.SendCurrentPlayer interface method to send data to InfoFragment
    @Override
    public void updatePlayer(int player) {
        infoFragment.updatePlayer(player);
    }
}
