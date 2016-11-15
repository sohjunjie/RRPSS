# RRPSS (CZ2002 2016/2017 SEM 1 GROUP PROJECT)

Restaurant Reservation and Point of Sale System (RRPSS)

RRPSS is a console based application computerizing the processes of making reservation, 
recording of orders and displaying of sale records. It will be solely used by the 
restaurant staff.

The restaurant is assumed to operates in 2 sessions (AM : 11am - 3pm, PM : 6pm - 10pm).


## Checking out project into Eclipse IDE
Follow the following instructions to clone the project files into your Eclipse IDE

1. Open the welcome screen (help > welcome)
2. Select Checkout projects from GIT
3. You should see a "Import Projects from Git" window.
   In the URI enter: git@github.com:sohjunjie/RRPSS.git
   If it fails use: https://github.com/sohjunjie/RRPSS.git instead
4. Continue clicking next and the project will be in your eclipse



## Pulling changes to the project

####**Switch to master branch**

   The master branch is the most definitive branch containing the latest working 
   copy of the project in github. Pull the latest changes by right clicking the project
   folder then

  `Team > Switch To... > Master  # if not in master branch`  
  `Team > Pull                   # get latest changes`  



## Making changes to the project

####**Create a new branch**

   All local development and changes should be done in appropriately named branches
   (e.g. create a new branch before doing any new major changes)

  `Team > Switch To > New Branch`
  

####**Pushing your changes to github**

   Add all the files that you want all changes to be reflected by
   
     `Team > Add to index`

   Commit all your changes, thereby creating a "savepoint"
   
     `Team > Commit...`  

   Push your changes to github
   
     `Team > Push Branch <branch name>`



####**Do not merge your changes directly into your local master branch and push to Git**

   If you are done developing the component you are working on, push your
   branch to GitHub.

   After that, visit the

   [Git repository page](//github.com/sohjunjie/RRPSS/) and open a

   pull request to the `master` branch.

   Give your pull request a title and describe what you are trying to do
   and self-review your code. Merge to master only if you are sure of 
   your changes. If not, get someone to review your code.
