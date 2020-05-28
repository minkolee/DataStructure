class Note {

    private int note;
    private int length;
    private int octave;
    private int upOrDown = 0;

    public Note(int note, int octave, int length, int upOrDown) {
        this.note = note;
        this.length = length;
        this.octave = octave;
        this.upOrDown = upOrDown;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public int getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(int upOrDown) {
        this.upOrDown = upOrDown;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note=" + note +
                ", length=" + length +
                ", octave=" + octave +
                ", upOrDown=" + upOrDown +
                '}';
    }
}
