
package org.usfirst.frc.team1554.robot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
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

    private final Talon leftTread, rightTread;
    private final MotorScheme scheme;
    private final BasicSense senses;
    private final JoystickControl joystick;
    private final AutoJoystickUpdater updater;

    public Robot() {
        super("Sailors", 1554);
        leftTread = new Talon(Ref.TALON_LEFT);
        rightTread = new Talon(Ref.TALON_RIGHT);

        senses = BasicSense.makeBuiltInSense();
        scheme = MotorScheme.Builder.newTwoChannelDrive(leftTread, rightTread).build();
        scheme.setDriveManagement(DriveManager.TANK);
        joystick = new DualJoystickControl(Ref.JOYSTICK_LEFT, Ref.JOYSTICK_RIGHT);
        updater = new AutoJoystickUpdater(joystick);
    }

    @Override protected void onInitialization() {
        joystick.putButtonAction(Ref.LIFE_BUTTON, "Life Button", this::killMotors, Hand.RIGHT);
    }

    private void killMotors() {
        // DOES STUFF
    }

    @Override public void dispose() {
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
        this.updateDrive();
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
