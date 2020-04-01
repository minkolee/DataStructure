import java.util.Arrays;
import java.util.Random;

class DnDCharacter {

    private Random random = new Random();

    private int strength, dexterity, constitution, intelligence, wisdom, charisma;

    public DnDCharacter() {
        strength = ability();
        dexterity = ability();
        constitution = ability();
        intelligence = ability();
        wisdom = ability();
        charisma = ability();
    }



    int ability() {
        int[] array = new int[4];
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            array[i] = random.nextInt(6) + 1;
            sum = sum + array[i];
        }

        int min = array[0];
        for (int i = 0; i < 4; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return sum - min;
    }

    int modifier(int input) {
        return (int) Math.floor((double) (input - 10) / 2);
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return ability();
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        return 10 + modifier(getConstitution());
    }

    public static void main(String[] args) {
        DnDCharacter dnDCharacter = new DnDCharacter();
        System.out.println(dnDCharacter);

    }

    @Override
    public String toString() {
        return "DnDCharacter{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                '}';
    }
}
