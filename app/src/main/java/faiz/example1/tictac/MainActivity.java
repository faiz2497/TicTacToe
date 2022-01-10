package faiz.example1.tictac;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:cross  1:circle
    int activePlayer=0;

    int []gameState={2,2,2,2,2,2,2,2,2};

    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    String winner;

    boolean won=false;

    int count=0;

    public void dropIn(View view) {

        count++;

        Button button=(Button)findViewById(R.id.button);
        TextView textView= (TextView) findViewById(R.id.textView);

        if (!won) {

            ImageView counter = (ImageView) view;

            int log = Integer.parseInt(counter.getTag().toString());

            if (gameState[log] == 2) {

                gameState[log] = activePlayer;

                counter.setTranslationY(-1500);

                Log.i("Clicked box is", counter.getTag().toString());

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.cross);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.circle);
                    activePlayer = 0;
                }

                counter.animate().translationYBy(1500).rotation(1800);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                    {
                        if (activePlayer == 1)
                            winner = "First";
                        else
                            winner = "Second";

                        won = true;

                        textView.setText(winner+" Player won!");
                        textView.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                        count=0;
                    }
                    if(count==9)
                    {
                        if( gameState[winningPosition[0]]!=gameState[winningPosition[1]]  && gameState[winningPosition[1]] != gameState[winningPosition[2]])
                        {
                            button.setVisibility(View.VISIBLE);
                            count=0;
                        }
                    }
                }
            }
        }
    }

    public void newGame(View view){

        count=0;
        Button button= (Button)findViewById(R.id.button);
        TextView textView=(TextView)findViewById(R.id.textView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout= (GridLayout) findViewById(R.id.gridLayout);



        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter= (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;

        for(int i=0;i<9;i++)
        gameState[i]=2;

        won=false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
