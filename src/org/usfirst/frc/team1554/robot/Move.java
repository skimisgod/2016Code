package org.usfirst.frc.team1554.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;

public class Move {
	
    public Move(){
    }
    public void preciseTurn(Joystick j, RobotDrive rd){
        if(j.getZ() == Ref.Z_MAX){//assuming that 1 is twist to the right
        	System.out.println("Right turn");
            rd.tankDrive(Ref.Y_MAX,0);//turn right
        }
        if(j.getZ() == Ref.Z_MIN){//assuming that the -1 is a twist to the left
            System.out.println("Left turn");
        	rd.tankDrive(0,Ref.Y_MAX);//turn left
        }    
    }
    public boolean lifeSwitch(Joystick j){
    	// Switch notifier
    	System.out.println("Robot on");
        return j.getRawButton(Ref.LIFE_BUTTON);
    }
    public boolean deathSwitch(Joystick j){
    	// Switch notifier
    	System.out.println("Robot off");
        return !j.getRawButton(Ref.LIFE_BUTTON);
    }
    
    public void turnOn(TalonSRX t){
    	// talon notifier
    	System.out.println("Talon on");
    	t.set(1.0);
    }
    public void turnOff(TalonSRX t){
    	// talon notifier
    	System.out.println("Talon off");
    	t.set(0);
    }
    
    
}
