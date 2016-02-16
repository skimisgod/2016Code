
package org.usfirst.frc.team1554.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TalonSRX;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
import java.lang.Math;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;





public class Robot extends IterativeRobot {
	RobotDrive myRobot; //Controls the drive machine
    Joystick rightStick; //Joy stick that is meant for movement of right tread 
    Joystick leftStick; //Joy stick that is meant for movement of the left tread
    Move move = new Move(); //Constructor for the move class
    boolean alive = false; //boolean alive stores the value of isEnabled()
    TalonSRX crocco;
    
    int x = 0;
	
	
	
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
   
   public void robotInit() {
	   	myRobot = new RobotDrive(0,1); //sets left motor channel to 0, right motor channel to 1
       	leftStick = new Joystick(1); //sets USB port to USB port 1
       	rightStick = new Joystick(2); //sets USB port to USB port 2 
    	crocco = new TalonSRX(Ref.CROCCO_CHANNEL);
    	crocco.setSafetyEnabled(true);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	 alive = isEnabled();
    	 // autonomous notifier
    	System.out.println("Autonomus start");
    	
    	
    	
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
    	 myRobot.setSafetyEnabled(false);
         while(isAutonomous() && isEnabled()){
         }
    	
    	
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }
    public void teleopInit(){ //Called once each time the robot enter teleop
        alive = isEnabled(); //alive stores true if the robot is enabled
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	myRobot.setSafetyEnabled(true);
        while(alive && isOperatorControl()) //isOperatorControl will be true if robot is not in autonomous or test mode
        {
        	
        	myRobot.tankDrive(-leftStick.getY(), -rightStick.getY()); //Drive method (can change to double values using getY()
            if(Math.abs(rightStick.getTwist()) == Ref.Z_MAX){ // only uses precise turn if twist is at its max value
                // precise turn notifier
            	System.out.println("precise turn");
            	move.preciseTurn(rightStick,myRobot);
            }
            alive = move.deathSwitch(rightStick);//will turn drive train off
             
        }
        
        alive = move.lifeSwitch(rightStick);//will turn drive train on 
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	 while(isTest() && isEnabled()){
             LiveWindow.run();
             // Joystick data
             double lx = Math.round(leftStick.getX() * 1000) / 1000;
             double ly = Math.round(leftStick.getY() * 1000) / 1000;
             double lz = Math.round(leftStick.getZ() * 1000) / 1000;
             double rx = Math.round(rightStick.getX() * 1000) / 1000;
             double ry = Math.round(rightStick.getY() * 1000) / 1000;
             double rz = Math.round(rightStick.getZ() * 1000) / 1000;
             /*if(Math.abs(rightStick.getX()) > .01)
            	 System.out.println("X: " + rightStick.getX());
             if(Math.abs(rightStick.getY()) > .01)
            	 System.out.println("Y: " + rightStick.getY());
             if(Math.abs(rightStick.getZ()) > .01)
            	 System.out.println("Z: " + rightStick.getZ());
             if(leftStick.getRawButton(1))
            	 System.out.println("FIRE!");*/
             
             System.out.print("LX: " + lx + " LY: " + ly + " LZ: " + lz
            		 + " RX: " + rx + " RY: " + ry + " RZ: " + rz + "\r");
             Timer.delay(.5); 
             /*if (x < 1) {
            	 move.turnOn(crocco);
                 Timer.delay(1.0);
                 move.turnOff(crocco);
                 x = 10;
             }*/
             
             
    	 }   
    
    }
    
}
