# Football-World-Cup-Score-Board

This is a test programm for *Sportradar* company.

The scoreboard supports the following operations: 
1. Start a new game, assuming initial score 0 – 0 and adding it the scoreboard. 
This should capture following parameters:
a. Home team
b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away 
team score. 
3. Finish game currently in progress. This removes a match from the scoreboard.
4. Get a summary of games in progress ordered by their total score. The games with the same 
total score will be returned ordered by the most recently started match in the scoreboard.

## Example of summary

1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

### How To Run as Library

1. Open Eclipse IDE and ensure that your project containing the code is open.

2. Right-click on the project in the Package Explorer or Project Explorer view.

3. Select "Export" from the context menu. This will open the Export dialog.

4. Expand the "Java" folder and select "JAR file". Click "Next".

5. In the JAR File Specification dialog, specify the JAR file's destination path and name.

6. Under "Select the resources to export", ensure that your project and its dependencies are selected. You can also choose to export the source code and resources associated with the project if needed.

7. In the "Options" section, you can choose to include or exclude certain resources from the JAR file based on your requirements.

8. Click "Finish" to start the JAR file creation process.

9. Eclipse will generate the JAR file containing your compiled code and dependencies based on the specified settings.