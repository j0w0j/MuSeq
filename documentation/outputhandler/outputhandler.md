# outputhandler package
This package contains three classes for handling output:
## NoteToMidi Class
This class converts numbers to MIDI characters, for now the input is a int[]. Returning a new string with the numbers converted to MIDI characters.
## WriteMidi Class
This class takes the new string and writes it into a txt file. The user can give a path and name to use for the new written file. If the use does not give
a file name and path the file will be written to default path/filename: output/output.txt.
```bash
java -jar --.jar -writemidi --path/filename.txt
```
## Synthesizer
