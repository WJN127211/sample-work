/*
 *  bumper.cc
 * 
 *  Sample code for a robot that has two front bumpers and uses them to
 *  avoid obstacles. Suitable for use with the Roomba and Create. 
 * 
 *  Works IRL. Doesn't work so well in Stage, probably because of the
 *  crude modeling of the bumpers, and can get stuck in convex shapes
 *  when it backs into an object while trying to turn away from it.
 *
 *  Based on an example provided by Monica Anderson and Jeff Forbes,
 *  via Carlos Jaramillo, and changed to (hopefully) make it easier to
 *  understand.
 *
 *  Modified:    Simon Parsons
 *  Date:        15th June 2009
 *  Last change: 19th September 2011
 *  
 */

#include <iostream>
#include <cstdlib>
#include <libplayerc++/playerc++.h>

int main(int argc, char *argv[])
{  
  using namespace PlayerCc;  

  // Set up proxies. Proxies are the datastructures that Player uses to
  // talk to the simulator and the real robot.

  PlayerClient    robot("localhost");  
  BumperProxy     bp(&robot,0);       // The bumper proxy reads from the
                                      // bumpers.
  Position2dProxy pp(&robot,0);       // The 2D proxy reads odometry from 
                                      // the robot and sends motion commands.

  // Allow the program to take charge of the motors (take care now)
  pp.SetMotorEnable(true);
  
  bool condition = true;
  int timer =0;

  // Control loop
  while(condition) 
    {    
      double turnrate, speed;
      
      int a = rand()%2;

      // Read from the proxies.
      robot.Read();
      timer++;

      // What does odometry tell us? In other words, how far do we
      // think we have gone?
      std::cout << "x: " << pp.GetXPos()  << std::endl;
      std::cout << "y: " << pp.GetYPos()  << std::endl;
      std::cout << "a: " << pp.GetYaw()  << std::endl;


      // Print out what the bumpers tell us:
      std::cout << "Left  bumper: " << bp[0] << std::endl;
      std::cout << "Right bumper: " << bp[1] << std::endl;
      
      // If either bumper is pressed, go backwards. Depending on the
      // combination of bumpers that are pressed, turn some also,
      // trying to turn away from the point of contact. 
      //
      // Otherwise just go forwards
      if(bp[0] || bp[1]){

	speed=-0.5;

	// Left bumper in contact, right bumper not in contact. Turn
	// to the right.  
	//
	// dtor converts from degrees to radians.
	if (bp[0] && !bp[1]) {  
	  turnrate=dtor(-20);  
	}

	// Left bumper not in contact, right bumper in contact. Turn
	// to the left
	if (!bp[0] && bp[1]) {
	  turnrate=dtor(20);
	}

	// Both left and right bumpers in contact. Make a random
	// choice about which way to turn.
	if (bp[0] && bp[1]) {
	  if(a==0){
            
	    turnrate=dtor(20);

            // What did we decide to do?
            std::cout << "Speed: " << speed << std::endl;      
            std::cout << "Turn rate: " << turnrate << std::endl << std::endl;

            // Send the motion commands that we decided on to the robot.
            pp.SetSpeed(speed, turnrate); 
            
            bool turn = true;
            while(turn){
            // Read from the proxies.
            robot.Read();
            timer++;

            // What does odometry tell us? In other words, how far do we
            // think we have gone?
            std::cout << "x: " << pp.GetXPos()  << std::endl;
            std::cout << "y: " << pp.GetYPos()  << std::endl;
            std::cout << "a: " << pp.GetYaw()  << std::endl;


            // Print out what the bumpers tell us:
            std::cout << "Left  bumper: " << bp[0] << std::endl;
            std::cout << "Right bumper: " << bp[1] << std::endl;
            if(bp[0]&&!bp[1]){
            turn = false;
            turnrate=dtor(-20); 
            speed = -0.5;
            }else if(!bp[0]&&bp[1]){
            turn = false;
            turnrate=dtor(20); 
            speed = -0.5;
            }else if(bp[0]&&bp[1]){
            turn = true;
            turnrate=dtor(20); 
            speed = -0.5;
            }else{
            turnrate = 0;   
	    speed=0.5;
            turn = true;
            }
            // What did we decide to do?
            std::cout << "Speed: " << speed << std::endl;      
            std::cout << "Turn rate: " << turnrate << std::endl << std::endl;

            // Send the motion commands that we decided on to the robot.
            pp.SetSpeed(speed, turnrate); 
            
            }
            if(!turn){
            continue;
            }
	  }
	  else if(a==1){
	    turnrate=dtor(-20);
            
            // What did we decide to do?
            std::cout << "Speed: " << speed << std::endl;      
            std::cout << "Turn rate: " << turnrate << std::endl << std::endl;

            // Send the motion commands that we decided on to the robot.
            pp.SetSpeed(speed, turnrate); 
            
            bool turnleft = true;
            while(turnleft){
            // Read from the proxies.
            robot.Read();
            timer++;

            // What does odometry tell us? In other words, how far do we
            // think we have gone?
            std::cout << "x: " << pp.GetXPos()  << std::endl;
            std::cout << "y: " << pp.GetYPos()  << std::endl;
            std::cout << "a: " << pp.GetYaw()  << std::endl;


            // Print out what the bumpers tell us:
            std::cout << "Left  bumper: " << bp[0] << std::endl;
            std::cout << "Right bumper: " << bp[1] << std::endl;
            if(bp[0]&&!bp[1]){
            turnleft = false;
            turnrate=dtor(-20); 
            speed = -0.5;
            }else if(!bp[0]&&bp[1]){
            turnleft = false;
            turnrate=dtor(20); 
            speed = -0.5;
            }else if(bp[0]&&bp[1]){
            turnleft = true;
            turnrate=dtor(-20); 
            speed = -0.5;
            }else{
            turnrate = 0;   
	    speed=0.5;
            turnleft = true;
            }
            // What did we decide to do?
            std::cout << "Speed: " << speed << std::endl;      
            std::cout << "Turn rate: " << turnrate << std::endl << std::endl;

            // Send the motion commands that we decided on to the robot.
            pp.SetSpeed(speed, turnrate); 
            
            }
            if(!turnleft){
            continue;
            }

	  }
	}
      } 
      else {
	  turnrate = 0;   
	  speed=0.5;
	}
           
      if(timer>=60){
       if((pp.GetXPos()<=-2||pp.GetXPos()>=2)||(pp.GetYPos()<=-2||pp.GetYPos()>=2)){
         
         condition = true;
       }else{
        speed = 0;
        turnrate = 0;
        condition =false;
       }   
     }else{
      condition = true;
     }

      // What did we decide to do?
      std::cout << "Speed: " << speed << std::endl;      
      std::cout << "Turn rate: " << turnrate << std::endl << std::endl;

      // Send the motion commands that we decided on to the robot.
      pp.SetSpeed(speed, turnrate);  
    }
  
}


