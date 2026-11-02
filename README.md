<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Unlicense License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/j0w0j/MuSeq">
    <img src="README_img/MuSeqLogo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">MuSeq</h3>

  <p align="center">
    An awesome tool to turn DNA into music!
    <br />
    <a href="https://github.com/j0w0j/MuSeq"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/j0w0j/MuSeq">View Demo</a>
    &middot;
    <a href="https://github.com/j0w0j/MuSeq/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    &middot;
    <a href="https://github.com/j0w0j/MuSeq/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage--quick-start">Usage / Quick Start</a></li>
    <li><a href="#composing-with-dna">Composing with DNA</a></li>
      <ul>
        <li><a href="#metadata">Metadata</a></li>
        <li><a href="#notes">Notes</a></li>
        <li><a href="#additional-information">Additional information</a></li>
        <li><a href="#example">Example</a></li>
      </ul>  
   <li><a href="#translation-tables">Translation Tables</a></li>
      <ul>
        <li><a href="#metadata">Metadata</a></li>
        <li><a href="#notes">Notes</a></li>
         <ul>
          <li><a href="#translationtable">Translationtable</a></li>
          <li><a href="#calculations">Calculations</a></li>
         </ul>
      </ul>
</ol>
</details>

## About The Project:
How does a cow sound? You would say "moo" yeahh.. Thats not what we mean, we wanted to know the sound DNA makes!
<br><br>
**MuSeq** is an interactive tool that bridges the gap between bioinformatics and audio/composing skills. 
Instead of looking at endless "boring" lines of genetic sequences, MuSeq translates and lets you translate real 
DNA sequence into playable (maybe) customized music.

### Key Features & Benefits:
- Works with fasta as file format for the input data
- Works both with DNA & RNA
- Output is a MIDI-file which is playable through a wide variety different tools and programs
- Easy to use, even with little to no bioinformatics background

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With:
- [![Java][Java-shield]][Java-url]
- [![Gradle][Gradle-shield]][Gradle-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started:

### Prerequisites:
Make sure you have Java installed on your machine:
* Java JDK (26 or higher)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Installation:
Follow these steps to install MuSeq:

**1.Clone the repository**
```bash
   git clone [https://github.com/j0w0j/MuSeq.git](https://github.com/j0w0j/MuSeq.git)
```
**2. Go to the project folder**
```bash
cd MuSeq
```
**3. Build project with Gradle**
```bash
gradle build 
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage / Quick Start:
You can use the tool with the build gradle compiled JAR file:
```bash
java -jar build/libs/MuSeq-1.0-SNAPSHOT.jar input.fasta --output output.mid
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Composing with DNA

If you want to compose music from DNA using this tool, here is what you should know:

### Metadata

The first 10 codons are used as "metadata" for the music. These are used for options such as BPM and instrument. These codons are NOT used as notes for the music.  
The table below shows which codon(s) are used for which option.

TODO tabel maken, de tabel hieronder is een voorbeeld van wat het zou kunnen zijn.

| Option | Codon Number(s) |
| ------ | ------ |
| BPM | 1 |
| Instrument | 2 and 3 |
| Iets ander leuks | 4 |
| etc.. | 4 and 5 |

For more information about what each codon translates too, see the Translation Tables section. Here you will find a table for each option.

### Notes

All codons after the metadata section are used for notes. Each note consists of 3 codons:

 - The first codon is used for the pitch of the note. For example codon TTT translates to C3.
 - The second codon is used for the length/duration of the note. A duration of 480 is equal to a quarter note. There is not yet rhythm or time signature implemented here.
 - The third codon is used for the volume of the note. 

For more information about what each codon translates too, see the Translation Tables section. 
Here you will find a table for each property of a note. Followed by an explanation of which calculations
were used for the translations.

### Additional information

 - Headers (starting with >) are ignored. 
   - If composing, this can be useful for labeling sections of a song.
 - Gaps and ambiguous nucleotides are ignored. Only nucleotides A, C, G, T and U are recognized.
   - Example: Sequence AAANNNTTT---CCCRRR will be read as AAATTTCCC.
   - For more info, see [this IUPAC nucleotide code table](https://www.bioinformatics.org/sms/iupac.html).
 - Nucleotide U translates the same as T. 
   - For example, TTT and UUU both translate to note C3.

### Example 

hier moet nog text komen verhaal lalallal

<p align="right">(<a href="#readme-top">back to top</a>)</p> 

## Translation Tables

### Metadata

TODO, bijvoorbeeld iets als:

| BPM | Codon |
| ------ | ------ |
| 0 | TTT |
| 20 | TTC |
| 30 | TTA |
| 40 | TTG |

| Instrument | Codon |
| ------ | ------ |
| Trompet | TTT |
| Piano | TTC |
| Gitaar | TTA |
| Bass | TTG |

etc..
<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Notes
The notes are composed using 3 consecutive codons. Each possible codon is assigned a number, 
which is translated into either the pitch, volume or length of the note, always in that order meaning the first
of the consecutive codons is always used for pitch etc.

#### Translationtable
Depending on whether the data contains DNA or RNA nucleotides all T's 
can be exchanged for U's. Beneath the table will be an explanation of each calculation
used to create the different note characteristics per codon.

| Codon | Number | Note pitch | Note length | Note volume |
|-------|:-------|------------|-------------|:------------|
| TTT   | 0      | C3         | 0           | 0           |
| TTC   | 1      | C#3        | 30          | 2           |
| TTA   | 2      | D3         | 60          | 4           |
| TTG   | 3      | D#3        | 120         | 6           |
| TCT   | 4      | E3         | 150         | 8           |
| TCC   | 5      | F3         | 180         | 10          |
| TCA   | 6      | F#3        | 210         | 12          |
| TCG   | 7      | G3         | 240         | 14          |
| TAT   | 8      | G#3        | 270         | 16          |
| TAC   | 9      | A3         | 300         | 18          |
| TAA   | 10     | A#3        | 330         | 20          |
| TAG   | 11     | B3         | 360         | 22          |
| TGT   | 12     | C4         | 390         | 24          |
| TGC   | 13     | C#4        | 420         | 26          |
| TGA   | 14     | D4         | 450         | 28          |
| TGG   | 15     | D#4        | 480         | 30          |
| CTT   | 16     | E4         | 510         | 32          |
| CTC   | 17     | F4         | 540         | 34          |
| CTA   | 18     | F#4        | 570         | 36          |
| CTG   | 19     | G4         | 600         | 38          |
| CCT   | 20     | G#4        | 630         | 40          |
| CCC   | 21     | A4         | 660         | 42          |
| CCA   | 22     | A#4        | 690         | 44          |
| CCG   | 23     | B4         | 720         | 46          |
| CAT   | 24     | C5         | 750         | 48          |
| CAC   | 25     | C#5        | 780         | 50          |
| CAA   | 26     | D5         | 810         | 52          |
| CAG   | 27     | D#5        | 840         | 54          |
| CGT   | 28     | E5         | 870         | 56          |
| CGC   | 29     | F5         | 900         | 58          |
| CGA   | 30     | F#5        | 930         | 60          |
| CGG   | 31     | G5         | 960         | 62          |
| ATT   | 32     | G#5        | 990         | 64          |
| ATC   | 33     | A5         | 1020        | 66          |
| ATA   | 34     | A#5        | 1050        | 68          |
| ATG   | 35     | B5         | 1080        | 70          |
| ACT   | 36     | C6         | 1110        | 72          |
| ACC   | 37     | C#6        | 1140        | 74          |
| ACA   | 38     | D6         | 1170        | 76          |
| ACG   | 39     | D#6        | 1200        | 78          |
| AAT   | 40     | E6         | 1230        | 80          |
| AAC   | 41     | F6         | 1260        | 82          |
| AAA   | 42     | F#6        | 1290        | 84          |
| AAG   | 43     | G6         | 1320        | 86          |
| AGT   | 44     | G#6        | 1350        | 88          |
| AGC   | 45     | A6         | 1380        | 90          |
| AGA   | 46     | A#6        | 1410        | 92          |
| AGG   | 47     | B6         | 1440        | 94          |
| GTT   | 48     | C7         | 1470        | 96          |
| GTC   | 49     | C#7        | 1500        | 98          |
| GTA   | 50     | D7         | 1530        | 100         |
| GTG   | 51     | D#7        | 1560        | 102         |
| GCT   | 52     | E7         | 1590        | 104         |
| GCC   | 53     | F7         | 1620        | 106         |
| GCA   | 54     | F#7        | 1650        | 108         |
| GCG   | 55     | G7         | 1680        | 110         |
| GAT   | 56     | G#7        | 1710        | 112         |
| GAC   | 57     | A7         | 1740        | 114         |
| GAA   | 58     | A#7        | 1770        | 116         |
| GAG   | 59     | B7         | 1800        | 118         |
| GGT   | 60     | C8         | 1830        | 120         |
| GGC   | 61     | C#8        | 1860        | 122         |
| GGA   | 62     | D8         | 1890        | 124         |
| GGG   | 63     | D#8        | 1920        | 126         |

#### Calculations
Firstly to assign a number to each codon, each letter was assigned a number 0-3. 
The first letter in the codon was multiplied by a factor of 16, the second by a factor of
4 and the last by a factor of 1. Making this work similarly to a 
nucleotide-to-aminoacid-translation-table like shown here below:
<div align="center">
  <a href="https://noorsiddiqui.com/what-is-chip-seq-atac-seq/">
    <img src="README_img/Codon-aminoacid-table.png" alt="Codon to amino acid translation table" width="599" height="341">
  </a>

This results in each codon having a number between 0 and 63. This number is then used to calculate the other 3 numbers 
used in a MIDI-file to describe either the pitch, volume (also referred to as velocity) and note length. 64 options
seemed plenty to us to have as pitch options. We only add 36 to each number to raise the overall pitch 3 full octaves 
as MIDI has 128 pitch options, and we wanted to use the middle of those, surrounding middle C.

Volume also runs from 0-127 and seeing we have exactly half the options, we elected to use a factor 2 for this. 
This way the full scale is available and used. And a step of 2 is definitely small enough to feel gradual in human
perception.

Lastly note length is a number between 0 and 1920. The number is multiplied by 30. This makes each note a length of a 
whole note as the longest option or a sixty-forth note (hemidemisemiquaver) as the shortest. The randomness introduced by
the input results in differing lengths of notes whom are not interconnected, this results in an absence of pulse or
feeling of rhythm. We have not been able to introduce time signatures into this as to help with this.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/j0w0j/MuSeq.svg?style=for-the-badge
[contributors-url]: https://github.com/j0w0j/MuSeq/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/j0w0j/MuSeq.svg?style=for-the-badge
[forks-url]: https://github.com/j0w0j/MuSeq/network/members
[stars-shield]: https://img.shields.io/github/stars/j0w0j/MuSeq.svg?style=for-the-badge
[stars-url]: https://github.com/j0w0j/MuSeq/stargazers
[issues-shield]: https://img.shields.io/github/issues/j0w0j/MuSeq.svg?style=for-the-badge
[issues-url]: https://github.com/j0w0j/MuSeq/issues
[license-shield]: https://img.shields.io/github/license/j0w0j/MuSeq.svg?style=for-the-badge
[license-url]: https://github.com/j0w0j/MuSeq/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/jouw_linkedin_gebruikersnaam
[product-screenshot]: README_img/screenshot.png
[Java-shield]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.oracle.com/java/
[Gradle-shield]: https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white
[Gradle-url]: https://gradle.org/