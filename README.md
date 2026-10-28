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
  </ol>
</details>

## About The Project:
How does a cow sound? You would say "moo" yeahh.. thats not what we mean, we wanted to know the sound DNA makes!
<br><br>
**MuSeq** is an interactive tool that bridges the gap between bioinformatics and audio/composing skills. Instead of looking at endless "boring" lines of genetic sequences, MuSeq translates and lets you translate real DNA sequence into playable (maybe) customized music.

### Key Features & Benefits:
- not sure about all features yet!
- will come later

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

 - The first codon is used for the pitch of the note. For example codon TTT translates to C2.
 - The second codon is used for the length/duration of the note. A duration of 480 is equal to a quarter note. TODO meer uitleg?
 - The third codon is used for the volume of the note. TODO?

For more information about what each codon translates too, see the Translation Tables section. Here you will find a table for each property of a note.

### Additional information

 - Headers (starting with >) are ignored. 
   - If composing, this can be useful for labeling sections of a song.
 - Gaps and ambiguous nucleotides are ignored. Only nucleotides A, C, G, T and U are recognized.
   - Example: Sequence AAANNNTTT---CCCRRR will be read as AAATTTCCC.
   - For more info, see [this IUPAC nucleotide code table](https://www.bioinformatics.org/sms/iupac.html).
 - Nucleotide U translates the same as T. 
   - For example, TTT and UUU both translate to note C2.

### Example 

TODO self made DNA music

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

### Notes

#### Pitch

| Note pitch | Codon |
| ------ | ------ |
| C2 | TTT |
| C#2 | TTC |
| D2 | TTA |
| D#2 | TTG |
| E2 | TCT |
| F2 | TCC |
| F#2 | TCA |
| G2 | TCG |
| G#2 | TAT |
| A2 | TAC |
| A#2 | TAA |
| B2 | TAG |
| C3 | TGT |
| C#3 | TGC |
| D3 | TGA |
| D#3 | TGG |
| E3 | CTT |
| F3 | CTC |
| F#3 | CTA |
| G3 | CTG |
| G#3 | CCT |
| A3 | CCC |
| A#3 | CCA |
| B3 | CCG |
| C4 | CAT |
| C#4 | CAC |
| D4 | CAA |
| D#4 | CAG |
| E4 | CGT |
| F4 | CGC |
| F#4 | CGA |
| G4 | CGG |
| G#4 | ATT |
| A4 | ATC |
| A#4 | ATA |
| B4 | ATG |
| C5 | ACT |
| C#5 | ACC |
| D5 | ACA |
| D#5 | ACG |
| E5 | AAT |
| F5 | AAC |
| F#5 | AAA |
| G5 | AAG |
| G#5 | AGT |
| A5 | AGC |
| A#5 | AGA |
| B5 | AGG |
| C6 | GTT |
| C#6 | GTC |
| D6 | GTA |
| D#6 | GTG |
| E6 | GCT |
| F6 | GCC |
| F#6 | GCA |
| G6 | GCG |
| G#6 | GAT |
| A6 | GAC |
| A#6 | GAA |
| B6 | GAG |
| C7 | GGT |
| C#7 | GGC |
| D7 | GGA |
| D#7 | GGG |

#### Length

TODO meer uitleg over wat deze waarden zijn.

| Note length | Codon |
| ------ | ------ |
| 0 | TTT |
| 2 | TTC |
| 4 | TTA |
| 6 | TTG |
| 8 | TCT |
| 10 | TCC |
| 12 | TCA |
| 14 | TCG |
| 16 | TAT |
| 18 | TAC |
| 20 | TAA |
| 22 | TAG |
| 24 | TGT |
| 26 | TGC |
| 28 | TGA |
| 30 | TGG |
| 32 | CTT |
| 34 | CTC |
| 36 | CTA |
| 38 | CTG |
| 40 | CCT |
| 42 | CCC |
| 44 | CCA |
| 46 | CCG |
| 48 | CAT |
| 50 | CAC |
| 52 | CAA |
| 54 | CAG |
| 56 | CGT |
| 58 | CGC |
| 60 | CGA |
| 62 | CGG |
| 64 | ATT |
| 66 | ATC |
| 68 | ATA |
| 70 | ATG |
| 72 | ACT |
| 74 | ACC |
| 76 | ACA |
| 78 | ACG |
| 80 | AAT |
| 82 | AAC |
| 84 | AAA |
| 86 | AAG |
| 88 | AGT |
| 90 | AGC |
| 92 | AGA |
| 94 | AGG |
| 96 | GTT |
| 98 | GTC |
| 100 | GTA |
| 102 | GTG |
| 104 | GCT |
| 106 | GCC |
| 108 | GCA |
| 110 | GCG |
| 112 | GAT |
| 114 | GAC |
| 116 | GAA |
| 118 | GAG |
| 120 | GGT |
| 122 | GGC |
| 124 | GGA |
| 126 | GGG |

#### Volume

TODO meer uitleg over wat deze waarden zijn.

| Note volume | Codon |
| ------ | ------ |
| 40 | TTT |
| 41 | TTC |
| 42 | TTA |
| 43 | TTG |
| 44 | TCT |
| 45 | TCC |
| 46 | TCA |
| 47 | TCG |
| 48 | TAT |
| 49 | TAC |
| 50 | TAA |
| 51 | TAG |
| 52 | TGT |
| 53 | TGC |
| 54 | TGA |
| 55 | TGG |
| 56 | CTT |
| 57 | CTC |
| 58 | CTA |
| 59 | CTG |
| 60 | CCT |
| 61 | CCC |
| 62 | CCA |
| 63 | CCG |
| 64 | CAT |
| 65 | CAC |
| 66 | CAA |
| 67 | CAG |
| 68 | CGT |
| 69 | CGC |
| 70 | CGA |
| 71 | CGG |
| 72 | ATT |
| 73 | ATC |
| 74 | ATA |
| 75 | ATG |
| 76 | ACT |
| 77 | ACC |
| 78 | ACA |
| 79 | ACG |
| 80 | AAT |
| 81 | AAC |
| 82 | AAA |
| 83 | AAG |
| 84 | AGT |
| 85 | AGC |
| 86 | AGA |
| 87 | AGG |
| 88 | GTT |
| 89 | GTC |
| 90 | GTA |
| 91 | GTG |
| 92 | GCT |
| 93 | GCC |
| 94 | GCA |
| 95 | GCG |
| 96 | GAT |
| 97 | GAC |
| 98 | GAA |
| 99 | GAG |
| 100 | GGT |
| 101 | GGC |
| 102 | GGA |
| 103 | GGG |


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