package com.example.connect3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 2;

    public void Yellow(View view){
        activePlayer = 0;
        Button yellowButton = (Button) findViewById(R.id.YellowButton);
        Button redButton = (Button) findViewById(R.id.RedButton);
        Button Restart = (Button) findViewById(R.id.Restart);
        TextView colourText = (TextView) findViewById(R.id.colourText);

        Restart.setVisibility(View.VISIBLE);
        yellowButton.setVisibility(View.INVISIBLE);
        redButton.setVisibility(View.INVISIBLE);
        colourText.setVisibility(View.INVISIBLE);
    }
    public void Red(View view){
        activePlayer = 1;
        Button yellowButton = (Button) findViewById(R.id.YellowButton);
        Button redButton = (Button) findViewById(R.id.RedButton);
        Button Restart = (Button) findViewById(R.id.Restart);
        TextView colourText = (TextView) findViewById(R.id.colourText);

        Restart.setVisibility(View.VISIBLE);
        yellowButton.setVisibility(View.INVISIBLE);
        redButton.setVisibility(View.INVISIBLE);
        colourText.setVisibility(View.INVISIBLE);
    }

    int count = 0;
    int[] gamesatate = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;
    //0:yellow , 1:red
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gamesatate[tappedCounter] == 2 && gameActive) {

            gamesatate[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if(activePlayer == 2){
                Toast.makeText(this,"Please choose a colour",Toast.LENGTH_SHORT).show();
            }else{
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                    count++;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                    count++;
                }
            }
            counter.animate().translationYBy(1500).setDuration(50);
            for (int[] winningPositin : winningPositions) {
                if (gamesatate[winningPositin[0]] == gamesatate[winningPositin[1]] && gamesatate[winningPositin[1]] == gamesatate[winningPositin[2]] && gamesatate[winningPositin[0]] != 2) {
                    //someone has won
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    Button Restart = (Button) findViewById(R.id.Restart);
                    Restart.setVisibility(View.INVISIBLE);
                }
                if(gameActive == true && count == 9)
                {
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText("Game has been draw. Try again!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    Button Restart = (Button) findViewById(R.id.Restart);
                    Restart.setVisibility(View.INVISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        Button Restart = (Button) findViewById(R.id.Restart);
        Restart.setVisibility(View.INVISIBLE);
        Button yellowButton = (Button) findViewById(R.id.YellowButton);
        Button redButton = (Button) findViewById(R.id.RedButton);
        TextView colourText = (TextView) findViewById(R.id.colourText);
        yellowButton.setVisibility(View.VISIBLE);
        redButton.setVisibility(View.VISIBLE);
        colourText.setVisibility(View.VISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gamesatate, 2);
        gameActive = true;
        activePlayer = 2;
        count = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
