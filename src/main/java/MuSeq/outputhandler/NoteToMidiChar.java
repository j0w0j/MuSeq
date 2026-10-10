package MuSeq.outputhandler;

import java.lang.StringBuilder;

// nummers naar bestand schrijven
public class NoteToMidiChar {

    // input: lijst met getallen
    public String convertNumberToMidiChar(int[] number) {
        StringBuilder sb = new StringBuilder();

        for (int numb : number) {
            char midiChar = (char) numb;
            sb.append(midiChar);
        }
        // return: string met MIDI characters
        return sb.toString();
    }
}

