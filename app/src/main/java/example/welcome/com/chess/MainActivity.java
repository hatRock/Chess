package example.welcome.com.chess;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout battleField, innerLayout;
    ImageView[][] imageViews = new ImageView[8][8];
    int click = 0;
    int intI, intJ;
    int count = 0;
    Handler handler;
    ArrayList<String> RightOne;
    ArrayList<String> RightTwo;
    ArrayList<String> LeftOne;
    ArrayList<String> LeftTwo;
    ArrayList<String> TopOne;
    ArrayList<String> TopTwo;
    ArrayList<String> BottomOne;
    ArrayList<String> BottomTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        battleField = findViewById(R.id.battleField);
        handler = new Handler();

        createField();
        fourList();
        setPawns();
        player();

        RightOne = new ArrayList<>();
        RightTwo = new ArrayList<>();
        LeftOne = new ArrayList<>();
        LeftTwo = new ArrayList<>();
        TopOne = new ArrayList<>();
        TopTwo = new ArrayList<>();
        BottomOne = new ArrayList<>();
        BottomTwo = new ArrayList<>();
    }

    public void createField(){

        for (int i = 0; i < 8; i++){

            innerLayout = new LinearLayout(this);
            innerLayout.setGravity(Gravity.CENTER);
            for (int j = 0; j < 8; j++){

                imageViews[i][j] = new ImageView(this);
                imageViews[i][j].setMaxHeight(120);
                imageViews[i][j].setMaxWidth(120);
                imageViews[i][j].setImageResource(R.drawable.border);
                //imageViews[i][j].setBackgroundColor();
                imageViews[i][j].setTag(0);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120, 120);

                layoutParams.topMargin = 10;
                layoutParams.leftMargin = 10;

                innerLayout.addView(imageViews[i][j], layoutParams);
            }
            battleField.addView(innerLayout);
        }
    }

    public void fourList(){

        for (int i = 0; i<8; i++){

            for (int j = 0; j<8; j++){

                String string = i+""+j;
                if (i == 0){
                    TopOne.add(string);
                }
                if (i == 1){
                    TopTwo.add(string);
                }
                if (j == 0){
                    LeftOne.add(string);
                }
                if (j == 1){
                    LeftTwo.add(string);
                }
                if (j == 7){
                    RightOne.add(string);
                }
                if (j==6){
                    RightTwo.add(string);
                }
                if (i==7){
                    BottomOne.add(string);
                }
                if (i==6){
                    BottomTwo.add(string);
                }
            }
        }
    }

    public void setPawns(){
        for (int  i = 0; i < 8; i++){

            for (int j = 0; j < 8; j++){

                if (i == 0 || i == 1){
                    imageViews[i][j].setImageResource(R.drawable.red);
                    imageViews[i][j].setTag(1);
                }
                if (i == 6 || i == 7){
                    imageViews[i][j].setImageResource(R.drawable.blue);
                    imageViews[i][j].setTag(2);
                }
            }
        }
    }

    public void player(){

        for (int i = 0; i < 8; i++){

            for (int j = 0; j < 8; j++){

                final int finalI = i;
                final int finalJ = j;
                String position = intI+""+intJ;
                imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         if (imageViews[finalI][finalJ].getTag().equals(2)){
                             imageViews[finalI][finalJ].setImageResource(R.drawable.blueone);

                             if (imageViews[finalI-1][finalJ].getTag().equals(0)){
                                 imageViews[finalI-1][finalJ].setImageResource(R.drawable.focus);
                                 click = 1;
                             }
                             intI = finalI;
                             intJ = finalJ;
                             onSecondClick();
                         }
                    }
                });
            }
        }
    }

    public void onSecondClick(){

        for (int i = 0; i < 8; i++){

            for (int j = 0; j < 8; j++){

                final int finalI = i;
                final int finalJ = j;
                final int finalK = intI;
                final int finalM = intJ;
                imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (imageViews[finalI][finalJ].getTag().equals(2)){
                            if (intI == finalI && intJ == finalJ){
                                imageViews[finalI][finalJ].setImageResource(R.drawable.blue);
                                if (imageViews[finalI-1][finalJ].getTag().equals(0)){
                                    imageViews[finalI-1][finalJ].setImageResource(R.drawable.border);
                                }
                                click = 0;
                            }
                        }
                        if (click == 0){
                            player();
                        }else {
                            if (imageViews[finalI][finalJ].getTag().equals(0)){
                                imageViews[finalI][finalJ].setImageResource(R.drawable.blue);
                                imageViews[finalI][finalJ].setTag(2);
                                imageViews[finalK][finalM].setImageResource(R.drawable.border);
                                imageViews[finalK][finalM].setTag(0);
                                count++;
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        robot();
                                    }
                                }, 1000);
                            }
                        }
                    }
                });
            }
        }
    }

    public void robot(){

        if (count == 1){

            String string = intI+""+intJ;
            switch (string){

                case "60":
                    moveRobot(1, 4);
                    break;

                case "61":
                    moveRobot(1,5);
                    break;

                case "62":
                    moveRobot(1,6);
                    break;

                case "63":
                    moveRobot(1, 7);
                    break;

                case "64":
                    moveRobot(1,0);
                    break;

                case "65":
                    moveRobot(1,1);
                    break;

                case "66":
                    moveRobot(1, 2);
                    break;

                case "67":
                    moveRobot(1,3);
                    break;
            }
        }
        player();
    }

    public void moveRobot(int i,int j){

        if (j == 7){
            //TODO: something
        }
        if (imageViews[i+1][j].getTag().equals(0)){
            imageViews[i+1][j].setImageResource(R.drawable.red);
            imageViews[i+1][j].setTag(1);
            imageViews[i][j].setImageResource(R.drawable.border);
            imageViews[i][j].setTag(0);
        }
    }

    public  void  playerMove(int i, int j){

    }
}
