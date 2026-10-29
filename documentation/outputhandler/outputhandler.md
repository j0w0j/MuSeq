# outputhandler package
This package contains three classes for handling output:
## WriteMidi Class
This class takes the int array and writes it into a midi file. The user can give a path and name to use for the new written file. If the use does not give
a file name and path the file will be written to default path/filename: output/output.mid.
```bash
java -jar --.jar  output/output.txt file.fasta
```
## BpmCalculator Class
This class calculates the bpm of the "song", it uses for now the first 5 elements of the metadata to calculate the bpm. The
bpm standard is the calculation of the class, if the user wants a custom bpm they can use -t like this:
```bash
java -jar --.jar  input.fasta -t 220
```
