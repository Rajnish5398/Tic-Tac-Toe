package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int turn=0;
    // Player Represent
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    // State
    // 0 - X
    // 1 - O
    // 2 - Null
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
    //  Winning Position
    int[][] winPositions={{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                        {0, 4, 8}, {2, 4, 6}};

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive || turn>=9){
            gameReset(view);
            turn = -1;
        }
        if(gameState[tappedImage] == 2 && gameActive && turn >= 0){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            }else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }
            //Check is anyone Win the Game
            for(int[] winPosition: winPositions){
                if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                    String winnerStr;
                    gameActive = false;
                    if(gameState[winPosition[0]] == 0){
                        winnerStr = "X has Won";
                        gameActive = false;
                    }else{
                        winnerStr = "O has Won";
                        gameActive = false;
                    }
                    TextView status=findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        turn = turn + 1;
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i< gameState.length; i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}