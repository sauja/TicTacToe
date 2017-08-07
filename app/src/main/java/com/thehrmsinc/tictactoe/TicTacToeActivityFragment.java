package com.thehrmsinc.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thehrmsinc.tictactoe.model.Cell;
import com.thehrmsinc.tictactoe.model.CellSymbol;
import com.thehrmsinc.tictactoe.model.Game;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

/**
 * A placeholder fragment containing a simple view.
 */
public class TicTacToeActivityFragment extends Fragment {
    private static final String TAG=TicTacToeActivityFragment.class.getName();
    private Game game;
    private Button buttonCells[][]=new Button[3][3];
    private KonfettiView konfettiView;
    private int gameBoardSize=3;
    public TicTacToeActivityFragment() {
    }

    public interface SendCurrentPlayer {
        public void updatePlayer(int player);
    }
    private SendCurrentPlayer sendCurrentPlayer;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            sendCurrentPlayer = (TicTacToeActivity) context;
        } catch (ClassCastException e) {
            Log.e(TAG, "ClassCastException", e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        konfettiView = (KonfettiView) getActivity().findViewById(R.id.viewKonfetti);
        initGame();
    }

    /*
    * Initialize all the game elements and views
    * */
    public void initGame() {
        Log.d(TAG, "initGame: Initializing game");
        game=new Game(gameBoardSize);
        sendCurrentPlayer.updatePlayer(game.getCurrentPlayer());

        for(int i=0; i<game.getGameBoardSize(); i++) {
            for(int j=0; j<game.getGameBoardSize(); j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                buttonCells[i][j] = ((Button) getActivity().findViewById(resID));
                buttonCells[i][j].setClickable(true);
                buttonCells[i][j].setOnClickListener(onButtonClick);
                //buttonCells[i][j].setText(i+" "+j);
            }
        }
    }


    final View.OnClickListener onButtonClick = new View.OnClickListener() {
        public void onClick(final View v) {
            Log.d(TAG, "onButtonClick: Player "+game.getCurrentPlayer()+" moves "+v.getTag());
            v.setClickable(false);
            String[] moves=((String)v.getTag()).split(" ");
            if(moves!=null&&moves.length>1)
            {
                int row=Integer.parseInt(moves[0]);
                int col=Integer.parseInt(moves[1]);
                game.makeMove(row,col);
                if(game.getCurrentPlayer()==1)
                    buttonCells[row][col].setBackground(getResources().getDrawable(R.drawable.x,null));
                else
                    buttonCells[row][col].setBackground(getResources().getDrawable(R.drawable.o,null));
                String alertText="";
                // Check if winner is available;

                switch (game.getWinner())
                {
                    case -1:
                        sendCurrentPlayer.updatePlayer(game.getCurrentPlayer());
                        //do nothing
                        break;
                    case -2:
                        //Set alert text as game over
                        alertText=getResources().getString(R.string.game_over);
                        break;
                    default:
                        //check if the game is over, if yes the display the winner
                        if(game.getWinner()>=0)
                            alertText="Player "+(game.getWinner()+1)+" is the winner!!!";
                }
                if(!alertText.equals(""))
                    showAlert(alertText);
            }
        }
    };
    /*
    * Updates the content of buttons based on the game model
    * */
    public void updateView()
    {
        Cell cells[][]=game.getCells();
        for(int i=0;i<game.getGameBoardSize();i++)
            for(int j=0;j<game.getGameBoardSize();j++)
            {
                if(cells[i][j].getCellSymbol()== CellSymbol.X)
                    buttonCells[i][j].setBackground(getResources().getDrawable(R.drawable.x,null));
                else if(cells[i][j].getCellSymbol()== CellSymbol.O)
                    buttonCells[i][j].setBackground(getResources().getDrawable(R.drawable.o,null));
                else
                    buttonCells[i][j].setBackground(getResources().getDrawable(android.R.drawable.btn_default,null));
            }
    }

    /*
    * Showing new game or draw message when the game is over
    * */
    public void showAlert(String alertText)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(alertText);
        builder1.setCancelable(false);


        if(alertText.contains("winner"))
        {
            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(1000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .stream(300, 1000L);
        }
        builder1.setPositiveButton(
                getResources().getText(R.string.new_game),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        initGame();
                        updateView();
                        konfettiView.clearAnimation();
                        dialog.cancel();

                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
