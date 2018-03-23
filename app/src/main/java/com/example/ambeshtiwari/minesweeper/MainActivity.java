package com.example.ambeshtiwari.minesweeper;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    static int[][] mine = new int[5][5];
    int BOMBS=7;
    ImageButton ib[]= new ImageButton[25];
    View.OnClickListener onClickListener;
    View.OnLongClickListener onLongClickListener;
    boolean flags[];
    int minesOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMines();
        setButtons();
    }

    void setImage(int id, int r, int c){
        ImageButton ib = (ImageButton)findViewById(id);
        switch (mine[r][c]){
            case 0: ib.setImageResource(R.drawable.pressed);
                break;
            case 1: ib.setImageResource(R.drawable.i);
                break;
            case 2: ib.setImageResource(R.drawable.ii);
                break;
            case 3: ib.setImageResource(R.drawable.iii);
                break;
            case 4: ib.setImageResource(R.drawable.iv);
                break;
            case 5: ib.setImageResource(R.drawable.v);
                break;
            case 6: ib.setImageResource(R.drawable.vi);
                break;
            case 7: ib.setImageResource(R.drawable.vii);
                break;
            case 8: ib.setImageResource(R.drawable.viii);
                break;
            case -1: ib.setImageResource(R.drawable.bomb);
                MediaPlayer mp = MediaPlayer.create(this,R.raw.explosion);
                mp.start();
                Toast.makeText(this, "YOU LOOSE", Toast.LENGTH_SHORT).show();
                synchronized (this){try{wait(1000);}catch (Exception e){}
                    }
                   mp.stop();
                break;
        }

    }

    void setMines()
    {
        flags= new boolean[25];
        minesOpened=0;
        for(int i=0;i<BOMBS;i++){
            int r = (int) (Math.random()*24);
            if(mine[r/5][r%5]==-1)i--;
            else{

                mine[r/5][r%5]=-1;
                for(int j=0;j<9;j++){ try{if(mine[r/5 + j/3 -1][r%5 + j%3 -1]!=-1)mine[r/5 + j/3 -1][r%5 + j%3 -1]++;}
                catch (Exception e){}}
            }
        }
    }

    void setButtons(){

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                ImageButton imageButton = (ImageButton) findViewById(id);
                for(int i=0;i<25;i++){
                    if( ib[i].equals(imageButton)){
                        setImage(id,i/5,i%5);
                        ib[i].setClickable(false);
                        ib[i].setLongClickable(false);

                        if(mine[i/5][i%5]!=-1)minesOpened+=1;       //winnning condition
                        else minesOpened=-99;
                        if(minesOpened==25-BOMBS){
                            Toast.makeText(MainActivity.this, "Hurray!!!\nYOU WON", Toast.LENGTH_SHORT).show();
                            MediaPlayer.create(MainActivity.this,R.raw.won).start();
                        }
                    }
                }
            }
        };

        onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id = v.getId();
                ImageButton imageButton = (ImageButton) findViewById(id);
                TextView flag= findViewById(R.id.flag);
                for(int i=0;i<25;i++)
                    if( ib[i].equals(imageButton)){
                        if(!flags[i]){
                            imageButton.setImageResource(R.drawable.flag);
                            flags[i]=true;
                        }
                        else{
                            imageButton.setImageResource(R.drawable.notpressed);
                            flags[i]=false;
                        }

                    }
                int count=BOMBS; for(int i=0;i<25;i++)if(flags[i])count--; flag.setText("\t\t "+count+"\t\t\t\t");
                return true;
            }
        };

        ib[0]=(ImageButton)findViewById(R.id.imageButton1);
        ib[1]=(ImageButton)findViewById(R.id.imageButton2);
        ib[2]=(ImageButton)findViewById(R.id.imageButton3);
        ib[3]=(ImageButton)findViewById(R.id.imageButton4);
        ib[4]=(ImageButton)findViewById(R.id.imageButton5);
        ib[5]=(ImageButton)findViewById(R.id.imageButton6);
        ib[6]=(ImageButton)findViewById(R.id.imageButton7);
        ib[7]=(ImageButton)findViewById(R.id.imageButton8);
        ib[8]=(ImageButton)findViewById(R.id.imageButton9);
        ib[9]=(ImageButton)findViewById(R.id.imageButton10);
        ib[10]=(ImageButton)findViewById(R.id.imageButton11);
        ib[11]=(ImageButton)findViewById(R.id.imageButton12);
        ib[12]=(ImageButton)findViewById(R.id.imageButton13);
        ib[13]=(ImageButton)findViewById(R.id.imageButton14);
        ib[14]=(ImageButton)findViewById(R.id.imageButton15);
        ib[15]=(ImageButton)findViewById(R.id.imageButton16);
        ib[16]=(ImageButton)findViewById(R.id.imageButton17);
        ib[17]=(ImageButton)findViewById(R.id.imageButton18);
        ib[18]=(ImageButton)findViewById(R.id.imageButton19);
        ib[19]=(ImageButton)findViewById(R.id.imageButton20);
        ib[20]=(ImageButton)findViewById(R.id.imageButton21);
        ib[21]=(ImageButton)findViewById(R.id.imageButton22);
        ib[22]=(ImageButton)findViewById(R.id.imageButton23);
        ib[23]=(ImageButton)findViewById(R.id.imageButton24);
        ib[24]=(ImageButton)findViewById(R.id.imageButton25);

        for(int i=0;i<25;i++){
            ib[i].setOnClickListener(onClickListener);
            ib[i].setOnLongClickListener(onLongClickListener);
        }
    }


    public void reset(View v){
        for(int i=0;i<25;i++)mine[i/5][i%5]=0;
        setMines();
        setContentView(R.layout.activity_main);
        setButtons();
    }



}
