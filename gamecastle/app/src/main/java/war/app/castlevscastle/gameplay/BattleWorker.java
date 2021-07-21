package war.app.castlevscastle.gameplay;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import war.app.castlevscastle.R;

public class BattleWorker implements Runnable{

    private Castle c1, c2;
    private Activity a;
    public Castle winner;

    public BattleWorker(Activity ac, Castle c1, Castle c2) {
        this.a = ac;
        this.c1 = c1;
        this.c2 = c2;
        this.winner = null;
    }

    @Override
    public void run() {

        winner = c1.battleTo(c2);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageView winleft = a.findViewById(R.id.winLeft);
        ImageView winright = a.findViewById(R.id.winRight);
        TextView draw = a.findViewById(R.id.draw);
        TextView statleft  = a.findViewById(R.id.statLeft);
        TextView statright  = a.findViewById(R.id.statRight);
        statleft.setVisibility(View.VISIBLE);
        statright.setVisibility(View.VISIBLE);
        if (winner.equals(c1)) {
            winleft.setVisibility(View.VISIBLE);
            statleft.setBackgroundResource(R.drawable.statwin);
            statright.setBackgroundResource(R.drawable.statlose);

        }else if(winner.equals(c2)){
            winright.setVisibility(View.VISIBLE);
            statleft.setBackgroundResource(R.drawable.statlose);
            statright.setBackgroundResource(R.drawable.statwin);
        }else{
            draw.setVisibility(View.VISIBLE);
            statleft.setBackgroundResource(R.drawable.statlose);
            statright.setBackgroundResource(R.drawable.statlose);
        }
        statleft.setText(
                "POWER KILL : "+c1.calculatePower()+" x "+(c1.myKillPower/c1.calculatePower())
                        +"\nSURVIVES : "+c1.getMySurvives()+"\n"+c1.getCastleType()
        );
        statright.setText(
                "POWER KILL : "+c2.calculatePower()+" x "+(c1.enemyKillPower/c2.calculatePower())
                        +"\nSURVIVES : "+c1.getEnemySurvives()+"\n"+c2.getCastleType()
        );
    }

    public Castle getWinner() {
    //return winner castle after a match done
        return this.winner;

    }
}
