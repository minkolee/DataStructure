import java.util.ArrayList;
import java.util.List;

public class NoteFactory {

    private MicrobitMusicChanger changer = new MicrobitMusicChanger();
    private String tone = "CTONE";
    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public List<String> makeMusicFromArray(int[][] music) {

        List<String> result = new ArrayList<>();
        changer.setTone(this.tone);

        for (int[] aSingleNotes : music) {
            Note newNote = new Note(aSingleNotes[0], aSingleNotes[1], aSingleNotes[2], aSingleNotes[3]);
            result.add(changer.translateNumberedMusicalNotationToMicrobitMusicString(newNote));
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] mu = {
                {6, 0, 4, 0}, {1, 0, 4, 0}, {7, -1, 4, 0}, {6, 0, 4, 0},
                {5, 0, 4, 0}, {7, -1, 4, 0}, {6, -1, 4, 0}, {5, 0, 4, 0},
                {4, 0, 4, 0}, {6, -1, 4, 0}, {5, -1, 4, 0}, {4, 0, 4, 0},
                {3, 0, 8, 0}, {1, 0, 4, 0}, {1, 0, 1, 0}, {2, 0, 1, 0}, {3, 0, 1, 0}, {5, 0, 1, 0},
                {6, 0, 12, 0}, {7, 0, 2, 0}, {1, 1, 2, 0}
        };

        NoteFactory factoryC = new NoteFactory();

        List<String> music = factoryC.makeMusicFromArray(mu);

        System.out.println(music);

        //天上人间 如果真值得歌颂 也是因为有你 才会变得闹哄哄
        int[][] muD = {
                {3, 0, 8, 0}, {2, 0, 6, 0}, {1, 0, 2, 0},
                {2, 0, 8, 0}, {0, 0, 4, 0}, {2, 0, 2, 0}, {3, 0, 2, 0},
                {4, 0, 4, 0}, {3, 0, 4, 0}, {3, 0, 4, 0}, {2, 0, 4, 0},
                {3, 0, 8, 0}, {0, 0, 4, 0}, {1, 0, 2, 0}, {1, 0, 2, 0},
                {4, 0, 6, 0}, {5, 0, 6, 0}, {6, 0, 4, 0},
                {5, 0, 6, 0}, {1, 0, 6, 0}, {5, 0, 4, 0},
                {4, 0, 4, 0}, {3, 0, 4, 0}, {2, 0, 4, 0},{1, 0, 2, 0},{2, 0, 18, 0},

                {3, 0, 8, 0}, {2, 0, 6, 0}, {1, 0, 2, 0},
                {2, 0, 8, 0}, {0, 0, 4, 0}, {2, 0, 2, 0}, {3, 0, 2, 0},
                {4, 0, 4, 0}, {3, 0, 4, 0}, {5, 0, 4, 1}, {7, 0, 4, 0},
                {2, 0, 4, 0}, {1, 0, 2, 0}, {1, 0, 2, 0}, {0, 0, 4, 0}, {1, 0, 2, 0}, {1, 0, 2, 0},
                {4, 0, 6, 0}, {5, 0, 6, 0}, {6, 0, 4, 0},
                {5, 0, 6, 0}, {1, 0, 6, 0}, {5, 0, 4, 0},
                {4, 0, 4, 0}, {3, 0, 4, 0}, {2, 0, 4, 0},{1, 0, 2, 0},{1, 0, 18, 0},


        };

        //创建工厂并设置为翻译D大调
        NoteFactory factoryD = new NoteFactory();
        factoryD.setTone("DTONE");

        List<String> music2 = factoryD.makeMusicFromArray(muD);
        System.out.println(music2);
    }

}
