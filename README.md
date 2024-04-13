# TennisScoreboard

## Description

TennisScoreboard is a web application that provides tools for managing tennis matches. Key functionalities include creating multiple matches simultaneously, viewing completed matches, searching for matches by player names, and scoring in the current match.

## Technologies Used

### Back-end:
- Java 21
- Apache Tomcat Server
- Servlets
- Maven
- Hibernate ORM
- H2 in-memory Database
- Unit-testing (JUnit5)

### Front-end:
- HTML
- CSS

## Limitations and Rules

For simplicity, it is assumed that each match is played according to the following rules:
- The match is played until two sets (best of 3).
- At a score of 6/6 in a set, a tie-break is played to 7 points.

## Installation and Running

1. Clone the repository:
git clone https://github.com/Darfik43/TennisScoreBoard
2. Open Terminal
3. cd [path-to-repository]/TennisScoreBoard
4. docker build -t your_image_name .
5. docker run -p 8080:8080 tennis
6. Open browser and go localhost:8080/tennis
   


## Authors

Author: Darfik43

## Contact

If you have any questions or suggestions, feel free to contact us at darfik43@gmail.com
