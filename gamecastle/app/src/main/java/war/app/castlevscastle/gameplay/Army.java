package war.app.castlevscastle.gameplay;

public abstract class Army {

    public static final String ARCHER = "ARCHER";
    public static final String CAVALRY = "CAVALRY";
    public static final String INFANTRY = "INFANTRY";

    public static final double ARCHER_BOOST = 0.2;
    public static final double CAVALRY_BOOST = 0.2;
    public static final double INFANTRY_BOOST = 0.2;

    public String ArmyType;
    public int numbers;
    public void setNumbers(int numbers){
        if(numbers>100000){
            this.numbers = 100000;
        }else{
            this.numbers = numbers;
        }
    }
}
