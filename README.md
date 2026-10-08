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
java -jar build/libs/MuSeq-1.0-SNAPSHOT.jar --input input.fasta --output output.wav
```
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