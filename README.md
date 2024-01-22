<h1>Tic Tac Toe Game using JavaFX - Client/Server</h1>
This is a desktop-based Tic Tac Toe game that allows two players to compete against each other over a network connection. Players take turns marking spaces on a 3x3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.
<h2>Team Members</h2>
  <li>Ahmed Khaled Abdelaziz</li>
  <li>Hadeer Adel</li>
  <li>Mohamed Kotb Saied</li>
  <li>Aya Ebrahim Mashaly</li>
  <li>Sara Ahmed</li>
<h2>Client Side Features</h2>
<ul>
<li>Simple Interface : Easy-to-use graphical user interface for gameplay.</li>
<li>Local Gameplay : to play local (2 player mode).</li>
<li>Robot Gameplay : to play robot with two levels (easy and hard).</li>
<li>Scalable: Built to handle multiple concurrent game sessions.</li>
<li>Network Gameplay:Login and SignUp to Play against another player over a network.</li>
<li>Available Players List: to send invitation.</li>
<li>Real-Time Updates: Immediate updates on the game board for both players.</li>
<li>Recording Game: to make game history for each player.</li>
<li>Update the score after each game for winner 
  (1 Points) and draw (no Point for each).</li>
<li>User profile: user name and score.</li>
</ul>
<h2>Installation</h2>
<ul>
  <li>Clone the repository or download the ZIP file.</li>
  <li>Navigate to the project directory.</li>
</ul>
<h2>Run the application</h2>
 <li>Can Run the compiled JAR file: java -jar TicTacToeClient.jar</li>
 <li>client and server both run on the same machine if you want to run them on different machines one small change should be made and it's to change the connection ip in the Client            project in a class called Constant.java (IP_ADDRESS) from 127.0.0.1 to the ip of the machine which the server is runing on.</li>
  
<h3>Libraries Used:</h3>
<li>JSON: to handle Requests and Responses between client and server.</li>





