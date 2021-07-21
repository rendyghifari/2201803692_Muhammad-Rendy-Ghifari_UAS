package war.app.castlevscastle.gameplay;

public abstract class Heroes {
    public static final String INFANTRY = "INFANTRY";
    public static final String CAVALRY = "CAVALRY";
    public static final String ARCHER = "ARCHER";

    public static final Double INFANTRY_BOOST = 0.4;
    public static final Double CAVALRY_BOOST = 0.4;
    public static final Double ARCHER_BOOST = 0.4;

    public String HeroType;
    public int numbers;
    public void setNumbers(int numbers){
        if(numbers>5){
            this.numbers = 5;
        }else{
            this.numbers = numbers;
        }
    }
}

