package war.app.castlevscastle.gameplay;
public class ArcherCastle extends Castle {

    public ArcherCastle() {
        this.CastleType = Castle.ARCHER;
    }

    @Override
    public double calculatePower() {
        double power = calculateTotalArmies();
        //Cavalry castle boost Cavalry Army by 20%
        for (Army arm: this.ArmiesToBattle) {
            if (arm.ArmyType == Army.ARCHER) {
                power += arm.numbers * Army.ARCHER_BOOST;
            }else {
                this.CastleType = Castle.MIXARMIES;
            }
        }

        // Each Hero boost Army by 40%
        for (Heroes hero: this.HeroesToBattle) {
            for (Army arm: this.ArmiesToBattle) {
                if (arm.ArmyType == Army.ARCHER && hero.HeroType == Heroes.ARCHER) {
                    power += arm.numbers * hero.numbers * Heroes.ARCHER_BOOST;
                }else if (arm.ArmyType == Army.CAVALRY && hero.HeroType == Heroes.CAVALRY) {
                    power += arm.numbers * hero.numbers * Heroes.CAVALRY_BOOST;
                }else if (arm.ArmyType == Army.INFANTRY && hero.HeroType == Heroes.INFANTRY) {
                    power += arm.numbers * hero.numbers * Heroes.INFANTRY_BOOST;
                }
            }
        }

        return power;
    }

    @Override
    public Castle battleTo(Castle ct2) {
        double myPower=this.calculatePower();
        double enemyPower=ct2.calculatePower();
        if(this.getCastleType()==Castle.MIXARMIES){
            this.myKillPower = myPower * Castle.killMIXARMIES;
            this.enemyKillPower = enemyPower * Castle.MIXARMIESkill;
        }
        else{
            if (ct2.CastleType == Castle.INFANTRY) {
                this.myKillPower = myPower * Castle.ARCHERkillINFANTRY;
                this.enemyKillPower = enemyPower * Castle.INFANTRYkillARCHER;
            } else if (ct2.CastleType == Castle.CAVALRY) {
                this.myKillPower = myPower * Castle.ARCHERkillCAVALRY;
                this.enemyKillPower = enemyPower * Castle.CAVALRYkillARCHER;
            } else if (ct2.CastleType == Castle.MIXARMIES) {
                this.myKillPower = myPower * Castle.killMIXARMIES;
                this.enemyKillPower = enemyPower * Castle.MIXARMIESkill;
            }
        }

        this.mySurvives=this.calculateTotalArmies()-this.enemyKillPower;
        this.enemySurvives=ct2.calculateTotalArmies()-this.myKillPower;

        if (this.getMySurvives() > this.getEnemySurvives()){
            return this;
        }else if(this.getMySurvives() < this.getEnemySurvives()){
            return ct2;
        }else{
            return new Castle() {
                @Override
                public double calculateTotalArmies() { return 0; }

                @Override
                public double calculatePower() { return 0; }

                @Override
                public Castle battleTo(Castle ct2) { return null; }

                @Override
                public void setArmyandHeroes(Army[] Armies, Heroes[] hero) { }
            };
        }
    }

}
