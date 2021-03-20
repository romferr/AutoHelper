package com.darkjp.AutoHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tears extends AppCompatActivity {

    // elements
    TextView tourNumber, easyRessource, normalRessource, hardRessource, heroRessource, epicRessource, kingRessource;
    Button start, restart;
    int round = 0;
    boolean party = false;
    int easyValues[] = {0, -1, -1, -1, -1, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5};
    int normalValues[] = {0, -1, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5};
    int hardValues[] = {0, -1, -1, 0, 1, 1, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    int heroValues[] = {0, 0, 0, 1, 2, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    int epicValues[] = {0, 0, 1, 2, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    int kingValues[] = {0, 1, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tears);

        tourNumber = (TextView) findViewById(R.id.tourNumber);
        easyRessource = (TextView) findViewById(R.id.easyRessource);
        normalRessource = (TextView) findViewById(R.id.normalRessource);
        hardRessource = (TextView) findViewById(R.id.hardRessource);
        heroRessource = (TextView) findViewById(R.id.heroRessource);
        epicRessource = (TextView) findViewById(R.id.epicRessource);
        kingRessource = (TextView) findViewById(R.id.kingRessource);
        start = (Button) findViewById(R.id.startButton);
        restart = (Button) findViewById(R.id.restartButton);

        start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            round++;
            tourNumber.setText(String.valueOf(round));
            if (round >= 0 && round < easyValues.length) {
                easyRessource.setText(String.valueOf(easyValues[round]));
                normalRessource.setText(String.valueOf(normalValues[round]));
                hardRessource.setText(String.valueOf(hardValues[round]));
                heroRessource.setText(String.valueOf(heroValues[round]));
                epicRessource.setText(String.valueOf(epicValues[round]));
                kingRessource.setText(String.valueOf(kingValues[round]));
            }
            if (round == 21) {
                Toast.makeText(view.getContext(), "the battle is supposed to be done...", Toast.LENGTH_SHORT).show();
            }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party = false;
                round = 0;
                buttonVisibility(view, restart, party);
                tourNumber.setText("");
                easyRessource.setText("Easy");
                normalRessource.setText("Normal");
                hardRessource.setText("Hard");
                heroRessource.setText("Hero");
                epicRessource.setText("Epic");
                kingRessource.setText("King");
                Toast.makeText(view.getContext(), "Let's start from the scratch !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("round", round);
        savedInstanceState.putBoolean("party", party);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        round = savedInstanceState.getInt("round");
        party = savedInstanceState.getBoolean("party");
        if (round >= 0 && round < 21) {
            tourNumber.setText(String.valueOf(round));
            easyRessource.setText(String.valueOf(easyValues[round]));
            normalRessource.setText(String.valueOf(normalValues[round]));
            hardRessource.setText(String.valueOf(hardValues[round]));
            heroRessource.setText(String.valueOf(heroValues[round]));
            epicRessource.setText(String.valueOf(epicValues[round]));
            kingRessource.setText(String.valueOf(kingValues[round]));
        } else {
            tourNumber.setText(String.valueOf(round));
            easyRessource.setText(String.valueOf(5));
            normalRessource.setText(String.valueOf(5));
            hardRessource.setText(String.valueOf(5));
            heroRessource.setText(String.valueOf(5));
            epicRessource.setText(String.valueOf(5));
            kingRessource.setText(String.valueOf(5));
        }

    }

    private void buttonVisibility(View v, Button b, Boolean status) {
        if (status)
            b.setVisibility(v.VISIBLE);
        else b.setVisibility(v.INVISIBLE);
    }
}