# GymFit-Proyect
In this project we had to create, as a group, an application for a gym, in Java.

## GYMFIT APPLICATION USER GUIDE

#### * Team members: Ana Gomez Llaneza, Esther Martinez Blanco and Celia Martin Alvarez


When running the application, if the data is not loaded, it only consists of one administrator, who has to enter the user “admin” and the password “admin” to access it.

On the other hand, if you want to create a user, a section appears in the login window where it says Register. Once the customer registers, the flat rate payment is made.

Likewise, if you want to create a monitor, this must be done from the administrator, therefore you would have to log in as an administrator and register it from there.

To make it easier to test the application, we have created a gymmain that contains monitors, Activity Types, Clients. In addition, there are also rooms, both simple and composed, group activities, group sessions, personalized plans, personalized sessions, machines and material.
In order to load it, it is necessary to first run the gymmain and then the main of the gui folder.

In addition, the application has a backup copy that is made when clicking the logout button from any of the users, therefore when it comes to reopening the application it will be loaded with the data that had been created in the previous execution . If you want to delete the data and make the gym empty again, all you have to do is click on the delete backup button found in the login window, close the application, just in case you want to refresh the resources. in case it had not been deleted correctly and run main again.

On the one hand, in terms of client functionality, we find user data, group activities. If you sign up for the waiting list you will be able to see the other users who are signed up for the waiting list. If a user is unsubscribed from that activity for which you are on the waiting list, in the notifications section, one will be sent to all users who are on the waiting list.
Likewise, you can sign up for personalized plans if you meet the requirements.
To see the charges and refunds that have been made to the client, you can access the notifications section.
Finally, you can check the reservations you have made and if you wish to cancel any.


On the other hand, regarding the functionality of the monitor, you can consult the data of the monitor as well as your group activities.
On the other hand, in the my personalized plans section you can select a row and cancel both sessions and plans and add group sessions already created or create personalized sessions and plans.
Likewise, the monitor will be able to select a machine, whether rented or owned, and mark it as broken.

Finally, if a custom plan is canceled for the monitor, you will receive a notification.


Finally, regarding the administrator's functionality, you can consult the benefits of the gym, to do this you will select a month and a year and click the update table button, so that all the sessions for that month appear. To calculate the profit you will have to select all the sessions that you want to add, this is done by clicking on the ctr while selecting with the mouse, in case the rows are not in a row, to save you time if you have to select several in a row it would be with the shift key and selecting with the mouse and finally click on the calculate profit button.

On the other hand, to configure a room, it is recommended to first select whether it is a general room or subroom, because the data to be filled in changes depending on what is selected.
In addition, you can create a group activity and free sessions. You must take into account the time and date at which it is created. If it is before the current date, it will give an error, as well as ensuring that there are no more sessions in the same room at the same time. You can also consult client reservations and instructor plans.
Likewise, in the section of consulting group activities of the monitor you can cancel both sessions and activities, selecting the one you want to delete and create sessions.
You can also create an activity type, configure the salary of monitors, configure prices and configure penalties for clients (which by default have a default value).
Finally, you will be able to register both material and machines, you will be able to change the status of the machines, first of all by selecting whether it is rented or owned and being able to change its status to operational, under repair or retired. To see the cost of the equipment you will have to do the same as to consult the gym benefit, that is, select the month you want to see, update the table and select the equipment you want to add.
