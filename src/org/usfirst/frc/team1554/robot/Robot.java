
package org.usfirst.frc.team1554.robot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
import edu.wpi.first.wpilibj.GenericHID;
import org.usfirst.frc.team1554.lib.common.control.AutoJoystickUpdater;
import org.usfirst.frc.team1554.lib.common.control.DualJoystickControl;
import org.usfirst.frc.team1554.lib.common.control.Hand;
import org.usfirst.frc.team1554.lib.common.control.JoystickControl;
import org.usfirst.frc.team1554.lib.common.robot.EnhancedIterativeRobot;
import org.usfirst.frc.team1554.lib.common.system.BasicSense;
import org.usfirst.frc.team1554.lib.common.system.MotorScheme;
import org.usfirst.frc.team1554.lib.common.system.MotorScheme.DriveManager;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Robot extends EnhancedIterativeRobot {

    private final Talon leftTread, rightTread, feeder, shooter, pivot;
    private final MotorScheme scheme;
    private final BasicSense senses;
    private final JoystickControl joystick;
    private final AutoJoystickUpdater updater;

    public Robot() {
        // Set Robot Name and Team Number
        super("Sailors", 1554);
        // Initialize Left and Right Tread Talons to appropriate Pin Numbers
        leftTread = new Talon(Ref.TALON_LEFT);
        rightTread = new Talon(Ref.TALON_RIGHT);
        feeder = new Talon(Ref.FEEDER_CHANNEL);
        pivot = new Talon(Ref.PIVOT_CHANNEL);
        shooter = new Talon(Ref.TRIGGER_CHANNEL);
        

        // Setup Built In Senses (RoboRIO Accelerometer/Gyro)
        senses = BasicSense.makeBuiltInSense();

        // Create Motor Scheme for two motors with the two talons (includes RobotDrive)
        scheme = MotorScheme.Builder.newTwoChannelDrive(leftTread, rightTread).build();
        scheme.setDriveManagement(DriveManager.TANK); // Tank Drive

        // Set up Dual Joystick Control (left and right USB Ports) and auto check for button presses
        joystick = new DualJoystickControl(Ref.JOYSTICK_LEFT, Ref.JOYSTICK_RIGHT);
        updater = new AutoJoystickUpdater(joystick);
    }

    @Override protected void onInitialization() {
        // Setup button commands
        joystick.putButtonAction(Ref.LIFE_BUTTON, "Life Button", this::killMotors, Hand.RIGHT);
        joystick.putButtonAction(Ref.FEED_BUTTON, "Feed Button", this::feedBall, Hand.RIGHT);
        joystick.putButtonAction(Ref.PIVOT_BUTTON, "Pivot Button", this::pivotArm, Hand.RIGHT);
        joystick.putButtonAction(Ref.LIFT_BUTTON, "Lift Button", this::liftTrap, Hand.RIGHT);
    }

    private void killMotors() {
    	scheme.getRobotDrive().stopMotor();
    }
    
	
    private void feedBall(){
    	// Feeds ball into robot
    }
    
    private void liftTrap(){
    	
    }
    
    private void pivotArm(){
    	// Pivots arm between feeding and shooting positions
    	
    }

    @Override public void dispose() {
        // Free/Dispose things and stop all current non-daemon threads
        updater.stop();
    }

    @Override public void preDisabled() {

    }

    @Override public void onDisabled() {

    }

    @Override public void preAutonomous() {

    }

    @Override public void onAutonomous() {

    }

    @Override public void preTeleop() {

    }

    @Override public void onTeleop() {
        // Update Robot Drive from joysticks
        this.updateDrive();

        if(joystick.rightJoystick().getTrigger(GenericHID.Hand.kRight))
            shooter.set(1);
        else
            shooter.set(0);
        
        
        if(joystick.rightJoystick().getRawButton(Ref.FEED_BUTTON))
        	feeder.set(1);
        else if(joystick.rightJoystick().getRawButton(Ref.LIFT_BUTTON)){
        	feeder.setInverted(true);
    		feeder.set(1);
        }
        else
        	feeder.setInverted(false);
        	feeder.set(0);
    }

    @Override public void postTeleop() {
        // Safety check
        shooter.set(0);
        feeder.set(0);
    }

    @Override public void onTest() {

    }

    @Override public RobotDrive getDrive() {
        return scheme.getRobotDrive();
    }

    @Override public JoystickControl getJoysticks() {
        return joystick;
    }

    @Override public MotorScheme getMotorScheme() {
        return scheme;
    }

    @Override public BasicSense getBasicSenses() {
        return senses;
    }
}
