Documentation Translator package

Class LettersToNumbers will translate the letter of the codons 
from the fasta-files to a number, which can be used to calculate 
the different aspects of the notes needed for a midi-file.

Class NumberToNote will calculate a number to represent the
pitch of the note, the dynamics of the note and the length of
the note. 

To calculate the pitch of the note, we will take the number
and add 36. This has to do with the fact that the different codon
possibilities are 64 and the different note possibilities are 128.
Therefor we have chosen to only use the middle octaves of the
total possible range. To be precisize the range will be from
36 - 100, which correlates to C3-E8 (C-e3).









