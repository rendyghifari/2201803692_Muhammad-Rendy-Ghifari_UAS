package war.app.castlevscastle.gameplay;

public abstract class Castle {
    public static final String INFANTRY = "INFANTRY";
    public static final String CAVALRY = "CAVALRY";
    public static final String MIXARMIES = "MIXARMIES";
    public static final String ARCHER = "ARCHER";

    public static final double CAVALRYkillINFANTRY = 0.4;
    public static final double CAVALRYkillARCHER = 0.1;
    public static final double INFANTRYkillCAVALRY = 0.1;
    public static final double INFANTRYkillARCHER = 0.4;
    public static final double ARCHERkillCAVALRY = 0.4;
    public static final double ARCHERkillINFANTRY = 0.1;
    public static final double MIXARMIESkill = 0.5;
    public static final double killMIXARMIES = 0.5;

    public double myKillPower=0;
    public double enemyKillPower=0;
    public double mySurvives=0;
    public double enemySurvives=0;

    public String CastleType;
    public Heroes[] HeroesToBattle;

    public Army[] ArmiesToBattle;


    public String getCastleType() {
        return this.CastleType;
    }

    public double getMySurvives(){
        if(mySurvives<0){
            return 0;
        }
        return mySurvives;
    }

    public double getEnemySurvives(){
        if(enemySurvives<0){
            return 0;
        }
        return enemySurvives;
    }

    public double calculateTotalArmies(){
        double totalarmies=0;
        for (Army arm: this.ArmiesToBattle) {
            totalarmies += arm.numbers;
        }
        if(totalarmies>100000){
            totalarmies = 100000;
        }
        return  totalarmies;
    }

    public abstract double calculatePower();

    public abstract Castle battleTo(Castle ct2);

    public void setArmyandHeroes(Army[] Armies, Heroes[] hero){
        this.ArmiesToBattle = Armies;
        this.HeroesToBattle = hero;
    }
}
