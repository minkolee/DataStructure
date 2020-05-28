import alog4e.libs.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MicrobitMusicChanger {

    private int octave = 4;
    private String tone = "CTONE";

    public static final String[] CTONE = {"C", "D", "E", "F", "G", "A", "B", "7"};
    public static final String[] GTONE = {"G", "A", "B", "C", "D", "E", "F#", "3"};
    public static final String[] DTONE = {"D", "E", "F#", "G", "A", "B", "C#", "6"};
    public static final String[] ATONE = {"A", "B", "C#", "D", "E", "F#", "G#", "2"};
    public static final String[] ETONE = {"E", "F#", "G#", "A", "B", "C#", "D#", "5"};
    public static final String[] BTONE = {"B", "C#", "D#", "E", "F#", "G#", "A#", "1"};
    public static final String[] FUTONE = {"F#", "G#", "A#", "B", "C#", "D#", "F", "4"};
    public static final String[] CUTONE = {"C#", "D#", "F", "F#", "G#", "A#", "C", "6"};
    public static final String[] FTONE = {"F", "G", "A", "Bb", "C", "D", "E", "4"};
    public static final String[] bBTONE = {"Bb", "C", "D", "Eb", "F", "G", "A", "1"};
    public static final String[] bETONE = {"Eb", "F", "G", "Ab", "Bb", "C", "D", "5"};
    public static final String[] bATONE = {"Ab", "Bb", "C", "Db", "Eb", "F", "G", "2"};
    public static final String[] bDTONE = {"Db", "Eb", "F", "Gb", "Ab", "Bb", "C", "6"};
    public static final String[] bGTONE = {"Gb", "Ab", "Bb", "B", "Db", "Eb", "F", "4"};
    public static final String[] bCTONE = {"B", "Db", "Eb", "E", "Gb", "Ab", "Bb", "1"};

    private static final Map<String, String[]> notes = new HashMap<>();
    private static final Map<String, String> noteToUpperNote = new HashMap<>();
    private static final Map<String, String> noteToLowerNote = new HashMap<>();

    static {
        noteToUpperNote.put("C", "C#");
        noteToUpperNote.put("D", "D#");
        noteToUpperNote.put("E", "F");
        noteToUpperNote.put("F", "F#");
        noteToUpperNote.put("G", "G#");
        noteToUpperNote.put("A", "A#");
        noteToUpperNote.put("B", "C");
        noteToUpperNote.put("C#", "D");
        noteToUpperNote.put("D#", "E");
        noteToUpperNote.put("F#", "G");
        noteToUpperNote.put("G#", "A");
        noteToUpperNote.put("A#", "B");

        noteToLowerNote.put("C", "B");
        noteToLowerNote.put("D", "Db");
        noteToLowerNote.put("E", "Eb");
        noteToLowerNote.put("F", "E");
        noteToLowerNote.put("G", "Gb");
        noteToLowerNote.put("A", "Ab");
        noteToLowerNote.put("B", "Bb");

        notes.put("CTONE", CTONE);
        notes.put("GTONE", GTONE);
        notes.put("DTONE", DTONE);
        notes.put("ATONE", ATONE);
        notes.put("ETONE", ETONE);
        notes.put("BTONE", BTONE);
        notes.put("FUTONE", FUTONE);
        notes.put("CUTONE", CUTONE);
        notes.put("FTONE", FTONE);
        notes.put("bBTONE", bBTONE);
        notes.put("bETONE", bETONE);
        notes.put("bATONE", bATONE);
        notes.put("bDTONE", bDTONE);
        notes.put("bGTONE", bGTONE);
        notes.put("bCTONE", bCTONE);
    }


    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

//    private List<String> changeToneToPythonString(String[] tones) {
//
//        List<String> result = new ArrayList<>();
//        int nubmer = Integer.parseInt(tones[7]);
//        System.out.println(nubmer);
//        for (int i = 0; i < tones.length - 1; i++) {
//            if (i < nubmer) {
//                result.add("'" + tones[i] + octave + "'");
//            } else {
//                result.add("'" + tones[i] + (octave + 1) + "'");
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        MicrobitMusicChanger changer = new MicrobitMusicChanger();
        changer.setTone("CTONE");

        String s = changer.translateNumberedMusicalNotationToMicrobitMusicString(new Note(7, -1, 4, 0));
        System.out.println(s);
    }

    public String translateNumberedMusicalNotationToMicrobitMusicString(Note aNote) {
        StringBuilder result = new StringBuilder();

        //如果是0就返回休止符
        String singleNote;
        if (aNote.getNote() == 0) {
            singleNote = "R";
        } else {
            singleNote = notes.get(tone)[aNote.getNote() - 1];
        }

        if (aNote.getUpOrDown() == 1) {
            singleNote = noteToUpperNote.get(singleNote);
        } else if (aNote.getUpOrDown() == -1) {
            singleNote = noteToLowerNote.get(singleNote);
        }

        result.append(singleNote);

        if (aNote.getNote() == 0) {
            result.append(octave + aNote.getOctave());
        } else {
            int numberToRise = Integer.parseInt(notes.get(tone)[7]);
            if (aNote.getNote() > numberToRise) {
                result.append((octave + aNote.getOctave() + 1));
            } else {
                result.append(octave + aNote.getOctave());
            }
        }

        result.append(":" + aNote.getLength());
        return "'" + result.toString() + "'";
    }

}
