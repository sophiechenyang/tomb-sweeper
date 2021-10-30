# Pharaoh’s Treasures

### Introduction

<img src="https://user-images.githubusercontent.com/80676658/139554221-dd82f319-2dde-4999-aef0-1cd296d206ce.png" width="660">

Pharaoh's Treasures is an Egyptian themed spin-off of the classic Minesweeper game. The game takes place in the tomb of an Egyptian pharaoh. Players click on the cells to reveal gems that are randomly placed underneath, and try to avoid the scarabs that are spawned every few seconds. The objective of the game is to reveal all the cells before the tomb gets infested with beetles. 

The code uses JavaFX library and is structured according to the Model View Controller paradigm (MVC). The game is created as part of the Intro to Programming of Interactive Systems course at Université Paris-Saclay. To run this, "--add-modules javafx.controls,javafx.fxml,javafx.media" must be included in the configuration. 

### Gameplay
* Click to open a cell 
* Drag the gem to collect it
* Avoid touching the scarab beetles that are crawling around the tomb, or the game ends. 
* Clear all cells to win.

A special amulet offers protection against the scarab beetles. To activate the amulet, the player has to hold down the A key. 

When the amulet is activated, the amulet symbol on top becomes highlighted and the cursor changes to a crosshair when the cursor hovers over it. In this mode, the player can right-click to stop the beetle from scuttering around, and then click on the beetle a few times to collect it. However, they will not be able to open any cells whilst using the amulet. 

![gameplay](https://user-images.githubusercontent.com/80676658/139555731-7509d726-f366-4a35-b2f2-fc2cc932437b.gif)


### Screens

<img width="660" alt="instructions" src="https://user-images.githubusercontent.com/80676658/139555421-beed1171-22ff-4a88-b6a9-3b372d140f8c.png">

<img width="660" alt="game over" src="https://user-images.githubusercontent.com/80676658/139555424-29891a14-4458-4f1e-8d14-01bf3eb15711.png">

<img width="660" alt="game won" src="https://user-images.githubusercontent.com/80676658/139555428-8159e18a-2eec-4153-8a20-b6b3b23ea525.png">



