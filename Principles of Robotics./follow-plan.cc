/**
 * follow-plan.cc
 * 
 * Sample code for a robot that has two front bumpers and a laser, and
 * which is provided with localization data.
 *
 * The code also allows the controller to read and write a "plan", a sequence
 * of location that the robot should move to.
 *
 * Written by: Simon Parsons
 * Date:       10th November 2011
 *  
 **/


#include <iostream>
#include <fstream>
#include <libplayerc++/playerc++.h>
using namespace PlayerCc;  


/**
 * Function headers
 *
 **/

player_pose2d_t readPosition(LocalizeProxy& lp);
void printRobotData(BumperProxy& bp, player_pose2d_t pose);
void printLaserData(LaserProxy& sp);

int  readPlanLength(void);
void readPlan(double *, int);
void printPlan(double *,int);  
void writePlan(double *, int);

/**
 * main()
 *
 **/

int main(int argc, char *argv[])
{  

  // Variables
  int counter = 0;
  double speed;            // How fast do we want the robot to go forwards?
  double turnrate;         // How fast do we want the robot to turn?
  player_pose2d_t  pose;   // For handling localization data

  // The set of coordinates that makes up the plan

  int pLength;
  double *plan;

  // Set up proxies. These are the names we will use to connect to 
  // the interface to the robot.
  PlayerClient    robot("localhost");  
  BumperProxy     bp(&robot,0);  
  Position2dProxy pp(&robot,0);
  LocalizeProxy   lp (&robot, 0);
  LaserProxy      sp (&robot, 0);
  
  // Allow the program to take charge of the motors (take care now)
  pp.SetMotorEnable(true);

  // Plan handling
  // 
  // A plan is an integer, n, followed by n doubles (n has to be
  // even). The first and second doubles are the initial x and y
  // (respectively) coordinates of the robot, the third and fourth
  // doubles give the first location that the robot should move to, and
  // so on. The last pair of doubles give the point at which the robot
  // should stop.
  pLength = readPlanLength(); // Find out how long the plan is from plan.txt
  plan = new double[pLength]; // Create enough space to store the plan
  readPlan(plan, pLength);    // Read the plan from the file plan.txt.
  printPlan(plan,pLength);    // Print the plan on the screen
  writePlan(plan, pLength);   // Write the plan to the file plan-out.txt
  
  int planxindex = 0;
  int planyindex = 1;
  bool on = false;
  bool obstacle;
  // Main control loop
  while(true) 
    {    
      // Update information from the robot.
      robot.Read();
      // Read new information about position
      pose = readPosition(lp);
      // Print data on the robot to the terminal
      printRobotData(bp, pose);
      // Print information about the laser. Check the counter first to stop
      // problems on startup
      if(counter > 2){
	printLaserData(sp);
      }

      // Print data on the robot to the terminal --- turned off for now.
      // printRobotData(bp, pose);
      
      // If either bumper is pressed, stop. Otherwise just go forwards
      
      
       
       if(bp[0] || bp[1]){
        
        obstacle= true;
        int timer=0;
        while(obstacle){
          while(timer<30){
            robot.Read();
            speed = -0.12;
	    turnrate = -0.523599;
            printRobotData(bp, pose);
            std::cout << "Speed: " << speed << std::endl;      
            std::cout << "Turn rate: " << turnrate << std::endl << std::endl;
            std::cout << "Minleft: " << sp.MinLeft() << std::endl;
            // Send the commands to the robot
            pp.SetSpeed(speed, turnrate); 
            timer++;
          }
          robot.Read();
          if(bp[0] || bp[1]){
              break;
          }
          if(sp.MinLeft()<0.8){
              speed=1.2;
              turnrate=0;
          }else if(sp.MinLeft()>=0.8){
              speed=1.2;
              turnrate=0;
              obstacle = false;
              on=false;
          }
          printRobotData(bp, pose);
          std::cout << "Speed: " << speed << std::endl;      
          std::cout << "Turn rate: " << turnrate << std::endl << std::endl;
          std::cout << "Minleft: " << sp.MinLeft() << std::endl;
          // Send the commands to the robot
          pp.SetSpeed(speed, turnrate); 
          
        }
        continue;
       } 
       
       double currentx = pose.px;
       double currenty =pose.py;
       double planx = plan[planxindex];
       double plany = plan[planyindex];
       double differencex = planx-currentx;
       double differencey = plany-currenty;
       double A=round(pose.pa*10)/100;
       double angle=round(atan2((plany-currenty),(planx-currentx))*10)/100;
       double distance = sqrt(pow(planx-currentx, 2) + pow(plany-currenty, 2));
       double currentrate = pose.pa;
       double expectrate = atan2(plany-currenty, planx-currentx);
       double differencerate = expectrate - currentrate;

       if(A!=angle && !on){
	turnrate=.4;
        speed=0;
       }else{
        
	on=true;
        
	if(differencerate>=-0.1&&differencerate<=0.1){
               turnrate=0;
               speed = distance/1.5;
        }else if(differencerate<-0.1){
                  turnrate = -0.1;
                  speed =distance/1.5;  
        }else if(differencerate>0.1){
                 turnrate = 0.1;
                 speed =distance/1.5;
        }

        if(differencex<=0.1&&differencey<=0.1){
		std::cout<<"Here"<<std::endl;
                speed =0;
                turnrate=0;
		
	}
	}

       
       
       if(differencex<=0.1&&differencey<=0.1){
            planxindex=planxindex+2;
            planyindex=planyindex+2;
            on =false;
            if(planyindex==pLength+1){
                 break;
            }
       }

      
      // What are we doing?
      std::cout << "Speed: " << speed << std::endl;      
      std::cout << "Turn rate: " << turnrate << std::endl << std::endl;
      std::cout << "Minleft: " << sp.MinLeft() << std::endl;

      // Send the commands to the robot
      pp.SetSpeed(speed, turnrate);  
      // Count how many times we do this
      counter++;
    }
  
} // end of main()

/**
 * readPosition()
 *
 * Read the position of the robot from the localization proxy. 
 *
 * The localization proxy gives us a hypothesis, and from that we extract
 * the mean, which is a pose. 
 *
 **/

player_pose2d_t readPosition(LocalizeProxy& lp)
{

  player_localize_hypoth_t hypothesis;
  player_pose2d_t          pose;
  uint32_t                 hCount;

  // Need some messing around to avoid a crash when the proxy is
  // starting up.

  hCount = lp.GetHypothCount();

  if(hCount > 0){
    hypothesis = lp.GetHypoth(0);
    pose       = hypothesis.mean;
  }

  return pose;
} // End of readPosition()


void printLaserData(LaserProxy& sp)
{

  double maxRange, minLeft, minRight, range, bearing;
  int points;

  maxRange  = sp.GetMaxRange();
  minLeft   = sp.MinLeft();
  minRight  = sp.MinRight();
  range     = sp.GetRange(180);
  bearing   = sp.GetBearing(180);
  points    = sp.GetCount();

  //Uncomment this to print out useful laser data
  //std::cout << "Laser says..." << std::endl;
  //std::cout << "Maximum distance I can see: " << maxRange << std::endl;
  //std::cout << "Number of readings I return: " << points << std::endl;
  //std::cout << "Closest thing on left: " << minLeft << std::endl;
  //std::cout << "Closest thing on right: " << minRight << std::endl;
  //std::cout << "Range of a single point: " << range << std::endl;
  //std::cout << "Bearing of a single point: " << bearing << std::endl;

  return;
} // End of printLaserData()

/**
 *  printRobotData
 *
 * Print out data on the state of the bumpers and the current location
 * of the robot.
 *
 **/

void printRobotData(BumperProxy& bp, player_pose2d_t pose)
{

  // Print out what the bumpers tell us:
  std::cout << "Left  bumper: " << bp[0] << std::endl;
  std::cout << "Right bumper: " << bp[1] << std::endl;
  // Can also print the bumpers with:
  //std::cout << bp << std::endl;

  // Print out where we are
  std::cout << "We are at" << std::endl;
  std::cout << "X: " << pose.px << std::endl;
  std::cout << "Y: " << pose.py << std::endl;
  std::cout << "A: " << pose.pa << std::endl;

  
} // End of printRobotData()

/**
 * readPlanLength
 *
 * Open the file plan.txt and read the first element, which should be
 * an even integer, and return it.
 *
 **/

int readPlanLength(void)
{
  int length;

  std::ifstream planFile;
  planFile.open("plan.txt");

  planFile >> length;
  planFile.close();

  // Some minimal error checking
  if((length % 2) != 0){
    std::cout << "The plan has mismatched x and y coordinates" << std::endl;
    exit(1);
  }

  return length;

} // End of readPlanLength

/**
 * readPlan
 *
 * Given the number of coordinates, read them in from plan.txt and put
 * them in the array plan.
 *
 **/

void readPlan(double *plan, int length)
{
  int skip;

  std::ifstream planFile;
  planFile.open("plan.txt");

  planFile >> skip;
  for(int i = 0; i < length; i++){
    planFile >> plan[i];
  }

  planFile.close();

} // End of readPlan

/**
 * printPlan
 *
 * Print the plan on the screen, two coordinates to a line, x then y
 * with a header to remind us which is which.
 *
 **/

void printPlan(double *plan , int length)
{
  std::cout << std::endl;
  std::cout << "   x     y" << std::endl;
  for(int i = 0; i < length; i++){
    std::cout.width(5);
    std::cout << plan[i] << " ";
    if((i > 0) && ((i % 2) != 0)){
      std::cout << std::endl;
    }
  }
  std::cout << std::endl;

} // End of printPlan


/**
 * writePlan
 * 
 * Send the plan to the file plan-out.txt, preceeded by the information
 * about how long it is.
 *
 **/

void writePlan(double *plan , int length)
{
  std::ofstream planFile;
  planFile.open("plan-out.txt");

  planFile << length << " ";
  for(int i = 0; i < length; i++){
    planFile << plan[i] << " ";
  }

  planFile.close();

} // End of writePlan
