package MuSeq.translator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberToNoteTest {

    @Test
    void devideNumbersSunnyDayTest() {
        NumberToNote numberToNote = new NumberToNote();
        //creating the input ArrayList
        ArrayList<Integer> pitchLengthVolumeinput = new ArrayList<>();
        pitchLengthVolumeinput.add(0);
        pitchLengthVolumeinput.add(42);
        pitchLengthVolumeinput.add(21);
        ArrayList<Integer> result = numberToNote.devideNumbers(pitchLengthVolumeinput);
        //Expected ArrayList using translationtable in README
        ArrayList<Integer> pitchLengthVolumeexpected = new ArrayList<>();
        pitchLengthVolumeexpected.add(36);
        pitchLengthVolumeexpected.add(1290);
        pitchLengthVolumeexpected.add(42);
        assertEquals(pitchLengthVolumeexpected, result);
    }

    @Test
    void devideNumbersRainyDayTest() {
        NumberToNote numberToNote = new NumberToNote();
        //creating input ArrayList with to large input values
        ArrayList<Integer> pitchLengthVolumeinput = new ArrayList<>();
        pitchLengthVolumeinput.add(100);
        pitchLengthVolumeinput.add(142);
        pitchLengthVolumeinput.add(121);
        assertThrows(IllegalArgumentException.class, () -> {numberToNote.devideNumbers(pitchLengthVolumeinput);});
    }
}