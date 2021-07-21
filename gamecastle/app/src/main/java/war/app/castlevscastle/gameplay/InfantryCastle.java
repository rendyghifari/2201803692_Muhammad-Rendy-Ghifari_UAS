package war.app.castlevscastle.gameplay;

import war.app.castlevscastle.gameplay.Army;
import war.app.castlevscastle.gameplay.Castle;
import war.app.castlevscastle.gameplay.Heroes;

public class InfantryCastle extends Castle {

    public InfantryCastle() {
        this.CastleType = Castle.INFANTRY;
    }

    @Override
    public double calculatePower() {
        double power = calculateTotalArmies();

        //Cavalry castle boost Cavalry Army by 20%
        for (Army arm: this.ArmiesToBattle) {
            if (arm.ArmyType == Army.INFANTRY) {
                power += arm.numbers * Army.INFANTRY_BOOST;
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
        if(this.CastleType == Castle.MIXARMIES){
            this.myKillPower = myPower * Castle.killMIXARMIES;
            this.enemyKillPower = enemyPower * Castle.MIXARMIESkill;
        }
        else{
            if (ct2.CastleType == Castle.ARCHER) {
                this.myKillPower = myPower * Castle.INFANTRYkillARCHER;
                this.enemyKillPower = enemyPower * Castle.ARCHERkillINFANTRY;
            } else if (ct2.CastleType == Castle.CAVALRY) {
                this.myKillPower = myPower * Castle.INFANTRYkillCAVALRY;
                this.enemyKillPower = enemyPower * Castle.CAVALRYkillINFANTRY;
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
