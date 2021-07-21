package war.app.castlevscastle;

import war.app.castlevscastle.gameplay.ArcherArmy;
import war.app.castlevscastle.gameplay.ArcherCastle;
import war.app.castlevscastle.gameplay.ArcherHero;
import war.app.castlevscastle.gameplay.Army;
import war.app.castlevscastle.gameplay.CavalryArmy;
import war.app.castlevscastle.gameplay.CavalryHero;
import war.app.castlevscastle.gameplay.Heroes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import war.app.castlevscastle.gameplay.BattleWorker;
import war.app.castlevscastle.gameplay.Castle;
import war.app.castlevscastle.gameplay.CavalryCastle;
import war.app.castlevscastle.gameplay.InfantryArmy;
import war.app.castlevscastle.gameplay.InfantryCastle;
import war.app.castlevscastle.R;
import war.app.castlevscastle.gameplay.InfantryHero;

import  java.lang.Thread;

public class MainActivity extends AppCompatActivity {
    //CASTLE
    Castle c1;
    Castle c2;

    //ARMIES
    Army c1Armies[] = new Army[2];
    Army c2Armies[] = new Army[2];

    //HEROES
    Heroes c1Heroes[] = new Heroes[1];
    Heroes c2Heroes[] = new Heroes[1];

    Castle fightingCastles[] = new Castle[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity myActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cavalryvsarcher = findViewById(R.id.cavalryvsarcher);
        Button mixarmiesvsinfantry = findViewById(R.id.mixarmiesvsinfantry);
        ImageView vs = findViewById(R.id.vs);
        final Button btn = findViewById(R.id.fightNowBtn);
        cavalryvsarcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                mixarmiesvsinfantry.setVisibility(View.GONE);
                cavalryvsarcher.setVisibility(View.GONE);
                //INIT CASTLE
                c1 = new CavalryCastle();
                c2 = new ArcherCastle();

                //INIT ARMY
                c1Armies[0]=new CavalryArmy();
                c1Armies[0].setNumbers(50000);
                c1Armies[1]=new CavalryArmy();
                c1Armies[1].setNumbers(50000);
                c2Armies[0]=new ArcherArmy();
                c2Armies[0].setNumbers(50000);
                c2Armies[1]=new ArcherArmy();
                c2Armies[1].setNumbers(50000);

                //INIT HERO
                c1Heroes[0]=new CavalryHero();
                c1Heroes[0].setNumbers(5);
                c2Heroes[0]=new ArcherHero();
                c2Heroes[0].setNumbers(5);

                c1.setArmyandHeroes(c1Armies,c1Heroes);
                c2.setArmyandHeroes(c2Armies,c2Heroes);

                fightingCastles[0] = c1;
                fightingCastles[1] = c2;
                initCastleImageBattle(fightingCastles);
            }
        });

        mixarmiesvsinfantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                mixarmiesvsinfantry.setVisibility(View.GONE);
                cavalryvsarcher.setVisibility(View.GONE);
                //INIT CASTLE
                c1 = new ArcherCastle();
                c2 = new InfantryCastle();

                //INIT ARMY
                c1Armies[0]=new ArcherArmy();
                c1Armies[0].setNumbers(50000);
                c1Armies[1]=new CavalryArmy();
                c1Armies[1].setNumbers(50000);
                c2Armies[0]=new InfantryArmy();
                c2Armies[0].setNumbers(50000);
                c2Armies[1]=new InfantryArmy();
                c2Armies[1].setNumbers(50000);

                //INIT HERO
                c1Heroes[0]=new ArcherHero();
                c1Heroes[0].setNumbers(5);
                c2Heroes[0]=new InfantryHero();
                c2Heroes[0].setNumbers(5);

                c1.setArmyandHeroes(c1Armies,c1Heroes);
                c2.setArmyandHeroes(c2Armies,c2Heroes);

                fightingCastles[0] = c1;
                fightingCastles[1] = c2;
                initCastleImageBattle(fightingCastles);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BattleWorker bw = new BattleWorker(myActivity, c1, c2);
                new Thread(bw).run();
            }
        });
    }


    private void initCastleImageBattle(Castle[] castles) {
        //assume castles has 2 members

        Castle left = castles[0];
        Castle right = castles[1];

        loadCastleImage(findViewById(R.id.castleLeft),left);
        loadCastleImage(findViewById(R.id.castleRight),right);
        loadCastleName(findViewById(R.id.nameLeft),left);
        loadCastleName(findViewById(R.id.nameRight),right);

    }

    private void loadCastleImage (ImageView iView, Castle ct) {
        if (ct instanceof CavalryCastle) {
            iView.setBackgroundResource(R.drawable.cavalry);
        }else if (ct instanceof InfantryCastle) {
            iView.setBackgroundResource(R.drawable.infantry);
        }else  {
            iView.setBackgroundResource(R.drawable.archer);
        }
    }

    private void loadCastleName (TextView tView, Castle ct) {
        if (ct instanceof CavalryCastle) {
            tView.setVisibility(View.VISIBLE);
            tView.setText("CAVALRY");
        }else if (ct instanceof InfantryCastle) {
            tView.setVisibility(View.VISIBLE);
            tView.setText("INFANTRY");
        }else  {
            tView.setVisibility(View.VISIBLE);
            tView.setText("ARCHER");
        }
    }

}