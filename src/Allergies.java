import java.util.ArrayList;
import java.util.List;

public class Allergies {

    private int allergicScore;


    public Allergies(int allergicScore) {
        this.allergicScore = allergicScore;
    }


    boolean isAllergicTo(Allergen item) {

        // 实际就是检测二进制数的第n位是不是与score相同
        // 只需要将allregicScore与item对应的数字按位做与运算, 如果包含这个过敏原, 则结果一定和这个过敏原的int值相同
        return (allergicScore & item.getScore()) == item.getScore();

    }


    List<Allergen> getList() {
        List<Allergen> result = new ArrayList<>();
        for (Allergen allergen : Allergen.values()) {
            if (isAllergicTo(allergen)) {
                result.add(allergen);
            }
        }
        return result;
    }

}