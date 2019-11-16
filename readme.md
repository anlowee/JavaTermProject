# *Catalog*
<!-- TOC -->

- [*Catalog*](#catalog)
- [*ABOUT ME*](#about-me)
- [*SUMMARY*](#summary)
- [*HOW2PLAY*](#how2play)
- [*TECHNICAL DIFFICULTY*](#technical-difficulty)
    - [*Load and Save*](#load-and-save)
    - [*Game Rendering*](#game-rendering)
    - [*Threads*](#threads)
    - [*Coordinate Calculation*](#coordinate-calculation)
    - [*Plot and Image resources*](#plot-and-image-resources)
- [*VERSION*](#version)
- [*DEVELOP ENVIRONMENT*](#develop-environment)
- [*BASIC KNWOLEDGE*](#basic-knwoledge)
- [*CONCLUSION AND OUTLOOK*](#conclusion-and-outlook)
- [*LAST BUT NOT LEAST*](#last-but-not-least)

<!-- /TOC -->

---

# WHAT'S NEW

## 11/16/2019
Very apperciate **@RePudding**, who spend his personal time testing my game patiently and give me pericous advice. Some bugs to fix and new things to update as following:

+ Player cannot move when have dialogue with NPC and cannot talk with another NPC when have a misson. Cause method **talk()** shot according to player's current mission, I've found some game-logic bugs already now.
+ Something wrong with map rolling, especially in **downwards**. Debuging but not find where yet.
+ **fps** maybe lost especially when turn to another direction after moving.
+ Instead **"Quit&Save"** with **"Quit" and "Save"**.
+ All dialogue will draw in the bottom of the main frame on a more beautiful board. And NPC's words won't display automaticlly, but by **pressing "T"**.
+ Add **BGM** and more **music effections**.

***Chop! Chop!***

---

# *ABOUT ME*
I'm ***EDDY GOEI***, study in **WHU** Hongyi Colledge now and major in **Computer Science and Technology**.

---

# *SUMMARY* 
This is a fresh man's **java term project**, which is a java2D game **without any game engines, 100% HAND CODING!** Without **LGP**'s help, I have to PS all images by myself, so miss him! **Classic RPG!** As its name, this is a game that you plant crops and sell the fruit of them, yuo can get money and recruit NPC to complete the final mission. There is a **tutorial** at the begining, by communicating with NPC, you can learn the basic operation. 

---

# *HOW2PLAY*
You can click on **GUIDE** button and get help and there is also a **HELP** in game frame.

---

# *TECHNICAL DIFFICULTY*
## *Load and Save* 
Due to to many objects and threads used in the game, there are plenty of data to save, or game won't work correctly when you restart it. Original method **toString()** is stupid for objects, because its hash algorithm is useless for rerun the game. So you have to use **ReadBuffered** and **WriteBuffered** to read and write the data one by one. But what sounds good is that make data in a binary way that user cannot adapt them easily, in other words, they are **invisble**.
## *Game Rendering*
Though this is a BIG title, seems to be very difficult to understand, here, it just means **paint**. As noted, this is a 2D game, I set user's view down, or say player see everything from down to up. Thus, **those lower(closer) objects should be paint above those higher(further)**, this is just a detail. Moreover, java has some own toolkits to make rendering more fluent. All paint work is done in **BackgroundPanel.java** in **paintComponent(Graphics g)** method, if you have seen my code, you will find I put it in thread Gameloop, so every image in game will be refreshed per 50ms.
## *Threads*
Threads is powerful but **error-prone**, I advise those who never use threads in programing before to learn it systematicly. At least, **start(), sleep(), isAlive()**. How to manage threads is important, when this game is running, **hundreds** of threads maybe working. If you learn it well, it won't a problem. ~~I concede I haven't learn it well yet~~
## *Coordinate Calculation*
Compared to the summer one, this time, map can move with player's position, in other words, the map is **explorable**. That's the point! Means you can import maps no matter its size. Just need to fix a few parameters. ~~Because I just add mapX and mapY~~
## *Plot and Image resources*
I'm not a good plot designer with my **poor imagination** ~~just in game plot designing~~, so the plot may be boring. But I believe this part is easy with game engine, and I just for learn java, language, so I almost give up this part. When comes to image resources, **LGP** is a master, no matter **PS** or **壁画**. Without him, the resources' quality isn't good:( 

---

# *VERSION*
**Beta1.0** is raw, if you just want to play the game, you can skip it. But in beta1.0, game logic code has already done, thus, it is a simply version for those who want to see my code. 
**Beta2.0** is a basically mature version, I update **SAVE** and **LOAD** and some UIs. Even though BGM and map and plot are not good yet, it is doesn't matter, because my coding makes it very easy to update new things. Just time-consuming.

---

# *DEVELOP ENVIRONMENT*
Firstly, I use **VScode** to build the project and workplace, it is really **AMAZING** in coding experience, smart one. But when I want to package the project, it drives me crazy. So I download **Eclipse** back and package the project. Compared with VScode, to be honest, **IDE** such as Eclipse maybe better for **project**.

---

# *BASIC KNWOLEDGE*
**JFrame, JPanel, JLabel, JButton**, I only use these UI components. Game is an interactive program, so **Threads** is a must. I tried to use Timer to control gameloop, **USELESS**!

---

# *CONCLUSION AND OUTLOOK*
Though there are a number of game engines fro game development, this is not for me. This time, I learn lots of things, especially **Java's programing thoughts**(OOP) as a programer. This game is more mature than that summer one, within my personal improvement. I'll keep updating this game till the term project DDL, but not frequently. **If you have played this game and find bugs or have proposals, welcome to show me, with my best appreciation**.

---

# *LAST BUT NOT LEAST*
Game can be only run in JRE 1.7 or higher and a 64-bits equipment. All image resources are public and come from Internet. To contact with me, **anlowee@outlook.com**.

---
