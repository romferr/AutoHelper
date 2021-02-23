package com.darkjp.AutoHelper;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GreatWesternTrail extends AppCompatActivity {

    // view elements
    Button next, easyMode, normalMode, hardMode;
    LinearLayout cardsLeftBlock, difficultyBlock, textBlockforCards;
    TextView easyRules, normalRules, hardRules, cardText, cardLeft, maxsize;

    //misc
    ArrayList<String> deckAutoma = new ArrayList<>();
    boolean party = false;
    int difficulty;
    int index;

    // the automa's deck
    static String[] IA = {
            "Move forward 1",
            "Move forward 1",
            "Move forward 1",
            "Move forward 1",
            "Move forward based on your current speed (3, 4 or 5)",
            "Move forward based on your current speed (3, 4 or 5)",
            "Move forward 1\n\nMove Train up to 3 spaces forward\n\nIf Brisco's train is ahead of yours when reaching a station, stop and place a disk in the station. Gain the tile if possible",
            "Move forward 1\n\nMove Train up to 3 spaces forward\n\nIf Brisco's train is ahead of yours when reaching a station, stop and place a disk in the station. Gain the tile if possible",
            "Move forward 1\n\nDraw a random building from Brisco's bag of building and place it on ide A in the next space in front of you on the trail",
            "Move forward 1\n\nTake LEAST expensive worker\n\nIf there are multiple to choose from, choose the top left least expensive worker",
            "Move forward 1\n\nTake LEAST expensive worker\n\nIf there are multiple to choose from, choose the top left least expensive worker",
            "Move forward 1\n\nTake an objective Card that is worth the HIGHEST amount of VP",
            "Move forward 1\n\nTake a cattle card worth the LEAST amount of VPs",
            "Move forward 1\n\nTake a cattle card worth the HIGHEST amount of VPs",
            "Move forward 1\n\nTake a teepee tile worth the HIGHEST dollar value",
            "Move forward 1\n\nTake a hazard tile worth the HIGHEST amount of VPs"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great_western_trail);

        cardsLeftBlock = (LinearLayout) findViewById(R.id.cardsLeftBlock);
        difficultyBlock = (LinearLayout) findViewById(R.id.difficultyBox);
        textBlockforCards = (LinearLayout) findViewById(R.id.textBlockforCards);

        next = (Button) findViewById(R.id.next);
        easyMode = (Button) findViewById(R.id.easyMode);
        normalMode = (Button) findViewById(R.id.normalMode);
        hardMode = (Button) findViewById(R.id.hardMode);
        easyRules = (TextView) findViewById(R.id.easyRules);
        normalRules = (TextView) findViewById(R.id.normalRules);
        hardRules = (TextView) findViewById(R.id.hardRules);

        cardText = (TextView) findViewById(R.id.cardText);
        cardLeft = (TextView) findViewById(R.id.cardLeft);
        maxsize = (TextView) findViewById(R.id.maxsize);
        deckAutoma = new ArrayList<>();

        easyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 0;
                launchGame(next, difficultyBlock, cardsLeftBlock, textBlockforCards, maxsize);

            }
        });
        normalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 1;
                launchGame(next, difficultyBlock, cardsLeftBlock, textBlockforCards, maxsize);
            }
        });
        hardMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 2;
                launchGame(next, difficultyBlock, cardsLeftBlock, textBlockforCards, maxsize);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!party) {
                    cardsLeftBlock.setVisibility(View.VISIBLE);
                    cardLeft.setText(" " + deckAutoma.size());
                    maxsize.setText(" /" + (16 - difficulty));
                    next.setText("Next Turn");
                    party = true;
                }

                if (deckAutoma.size() == 0) {
                    createAndShuffleDeck(deckAutoma, difficulty);
                    Toast.makeText(getBaseContext(), "Automat's deck is shuffled", Toast.LENGTH_SHORT).show();
                }
                Random rand = new Random();
                index = rand.nextInt(deckAutoma.size());
                cardLeft.setText(String.valueOf(deckAutoma.size() - 1));
                cardText.setText(deckAutoma.get(index));
                deckAutoma.remove(index);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putStringArrayList("deckAutoma", deckAutoma);
        savedInstanceState.putInt("difficulty", difficulty);
        savedInstanceState.putString("textCard", cardText.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        deckAutoma = savedInstanceState.getStringArrayList("deckAutoma");
        party = savedInstanceState.getBoolean("party");
        difficulty = savedInstanceState.getInt("difficulty");
        String textCard = savedInstanceState.getString("textCard");

        next.setVisibility(View.VISIBLE);
        difficultyBlock.setVisibility(View.GONE);
        textBlockforCards.setVisibility(View.VISIBLE);
        cardsLeftBlock.setVisibility(View.VISIBLE);

        cardLeft.setText(String.valueOf(deckAutoma.size()));
        maxsize.setText(" /" + (16 - difficulty));
        cardText.setText(textCard);
        next.setText("Next Turn");


    }

    private void launchGame(Button next, LinearLayout difficultyBlock, LinearLayout cardsLeftBlock, LinearLayout textBlockforCards, TextView maxsize) {
        createAndShuffleDeck(deckAutoma, difficulty);
        next.setVisibility(View.VISIBLE);
        difficultyBlock.setVisibility(View.GONE);
        textBlockforCards.setVisibility(View.VISIBLE);
        String rules = "\"Brisco\" the AUTOMA :\n\n";
        switch (difficulty) {
            case 0:
                rules += easyRules.getText().toString();
                break;
            case 1:
                rules += normalRules.getText().toString();
                break;
            case 2:
                rules += hardRules.getText().toString();
                break;
        }
        cardText.setText(rules);
    }

    private void createAndShuffleDeck(ArrayList<String> deckAutoma, int cardsToRemove) {
        for (int i = cardsToRemove; i < IA.length; i++) {
            deckAutoma.add(IA[i]);
        }
    }
}