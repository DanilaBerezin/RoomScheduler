INTRODUCTION:
================================================================

This is a program I wrote as the final project for CMPSC 221. The program has not been touched much since submission, but it will likely be updated in the future.

The program is a simulated room scheduling application designed for institution faculty to reserve rooms. 

It uses databases to store information about faculty and their reservations and provides a GUI for users to interact with the databases and add faculty and make reservations for faculty.

RUNNING THE PROGRAM:
================================================================

Note: this program is specifically designed for netbeans and uses Apache Derby to manage the server applications and the databases on them. To work for other IDEs and interact with other database services the application may need to be refractored.

Apache Derby should already be installed when you clone the github project. If you already have it installed refer to the "Program Setup" section further below.

To install Apache Derby:
	1) Go to: https://db.apache.org/derby/derby_downloads.html
	2) Scroll to the "Java 8 and Higher" Section of the page
	3) Click on 10.4.2.0
	4) Click on one of the first two links under the "Distributions section", depending on what OS you have. The name of the file should be something like "db-derby-10.14.2.0-bin"
	5) Extract the zip into the git directory you pulled the project into.

Program setup:
	1) Open Netbeans and open the RoomScheduler class project
	2) Navigate to the services tab on the left side of the NetBeans GUI
	3) Expand the "Databases" section underneath
	4) Right click on Java DB
	5) Click on "Properties"
	6) In the "Java DB Installation" textbox, copy the path where you installed Apache Derby. By default, for this project it should be "*Path you cloned the project into*/RoomScheduler/db-derby-10.14.2.0-bin"
	7)In the "Database Locations" textbox, copy the path where the room scheduler databases are stored. By default, for this project it should be "*Path you cloned the project into*/RoomScheduler/Derby Databases"

THE SCHEDULER:
================================================================

The Room Scheduler GUI has 4 pages which the user can navigate which all perform distinct tasks. 

The first page, "Add Faculty," well, adds faculty which want to make reservations. It prompts the user to enter a name and and stores this name in a database upon the user clicking the "submit" button located below. Adding faculty is necessary before making any reservations. 

The second page, "Reserve," allows a given faculty member to reserve a specific room based on the date the faculty member wants to make the reservation and the number of seats they want in the reserved room. The reservations work on a "first come first serve" basis, so if a room is not already reserved on a given date, any faculty member can reserve it. If the only room that's available given the constraints is a room that is reserved, the specific reservation made by the faculty member is put on a waitlist. Upon entering the faculty, the reservation date, the number of seats desired, and clicking the submit button. The specific reservation is stored in the reservation database or the waitlist database depending on whether or not the room is already reserved.

The third page, "Reservation Status," displays all the reservations made into the system so far, given the date. All the reservations are displayed after selecting a date and clicking the "Submit" button.

The final page, "Waitlist Status," displays every reservation that has been waitlisted. To display, simply click the "Submit" button.

MANAGING DATABASES:
================================================================

