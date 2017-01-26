package com.example.barteksvaberg.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnHit;
    Button btnStand;
    Button btnSplit;
    Button btnDouble;

    Engine engine = new Engine();

    public static final String DEBUG = "Debug: ";

    private int playersDone = 0;
    private int playingHand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHit = (Button) findViewById(R.id.btn_hit);
        btnSplit = (Button) findViewById(R.id.btn_split);
        btnStand = (Button) findViewById(R.id.btn_stand);
        btnDouble = (Button) findViewById(R.id.btn_double);


    }

    public void start(View view) {
        engine.initNewGame();

        engine.dealer.initialDeal();
        
        getStatus();

        nextPlayer();
    }

    private void nextPlayer() {
        Engine.Player player = engine.players.get(playersDone);
        if ( player.getClass() == Engine.AIPlayer.class) {
            player.takeTurn();
            playersDone++;
            nextPlayer();
            Log.d(DEBUG, playerStatus(player) + "\n\n") ;
        } else if (playersDone == engine.players.size()-1) {
            engine.dealer.takeTurn();
        } else {
            btnHit.setEnabled(true);
            btnStand.setEnabled(true);
            btnSplit.setEnabled(player.hands.get(playingHand).canSplit());
            btnDouble.setEnabled(player.hands.get(playingHand).canDoubleDown());
        }
    }

    private void getStatus() {
        for (Engine.Player player: engine.players) {
            Log.d(DEBUG, playerStatus(player) + "\n\n") ;
        }
    }

    private String playerStatus(Engine.Player player) {
        String playerName = player.name;
        String cardString = "";
        for (Card card: player.hands.get(0).cards) {
            cardString += card.toString() + "\n";
        }
        int value = player.hands.get(0).handValue();
        return playerName + "\n" + cardString + "\n" + value + "\n\n";
    }
}
