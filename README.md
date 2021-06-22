[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
<p align="center">

  <h3 align="center">Gomoku Server</h3>

  <p align="center">
    A HTTP-based 'X-in-a-row' game
    <br />
    <a href="https://github.com/DomhnallP/Gomoku-server"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/DomhnallP/Gomoku-server/issues">Report Bug</a>
    ·
    <a href="https://github.com/DomhnallP/Gomoku-server/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
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
        <li><a href="#installation">Installation and Launching</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#usage">Configuration</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project was built to showcase how a multiplayer game can be build on a client-server architecture using HTTP to transfer Player Movement Actions as well as a Synchronised Game State between the clients and the Game Server.


### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Project Lombok](https://projectlombok.org/)



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

This Project is entirely self contained and useds the maven wrapper, so nothing needs to be installed prior to running this project

### Installation and Launching

1. Clone the repo
   ```sh
   git clone https://github.com/DomhnallP/Gomoku-server.git
   ```
2. Run the application
   ```sh
   ./mvnw spring-boot:run
   ```
   _Note: Some terminals do not required the ./ prefix, so try removing it if the project doesn't run_



<!-- USAGE EXAMPLES -->
## Usage

The Server should now be up and running and is ready to accept new game requests.

_To Test this, you can use the test gomoku_client application found here: [Gomoku Client](https://github.com/DomhnallP/Gomoku-client). Installation and usage instructions are available on that page_


## Configuration

The Allows for a number of properties to be configured within the [Spring Properties File](https://github.com/DomhnallP/Gomoku-server/blob/master/src/main/resources/application.properties). See below a list of available properties: 

| Name               | Description                                                             | Type        |
|--------------------|-------------------------------------------------------------------------|-------------|
|     server.port    | sets the localhost port that the game server will use (Default is 8080) | port number |
|    board.height    | Sets the height of the gomoku board                                     |     int     |
|     board.width    | Sets the width of the gomoku board                                      |     int     |
| board.winCondition | Defines the number of adjacent tokens required to win                   |     int     |


<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/DomhnallP/Gomoku-server/issues) for a list of proposed features (and known issues).


<!-- CONTACT -->
## Contact

Domhnall ó Póil -  dm.poole@hotmail.com

Project Link: [https://github.com/DomhnallP/Gomoku-server](https://github.com/DomhnallP/Gomoku-server)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/DomhnallP/gomoku-server.svg?style=for-the-badge
[contributors-url]: https://github.com/DomhnallP/gomoku-server/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/DomhnallP/gomoku-server.svg?style=for-the-badge
[forks-url]: https://github.com/DomhnallP/gomoku-server/network/members
[stars-shield]: https://img.shields.io/github/stars/DomhnallP/gomoku-server.svg?style=for-the-badge
[stars-url]: https://github.com/DomhnallP/gomoku-server/stargazers
[issues-shield]: https://img.shields.io/github/issues/DomhnallP/gomoku-server.svg?style=for-the-badge
[issues-url]: https://github.com/DomhnallP/gomoku-server/issues
[license-shield]: https://img.shields.io/github/license/DomhnallP/gomoku-server.svg?style=for-the-badge
[license-url]: https://github.com/DomhnallP/gomoku-server/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/DomhnallP