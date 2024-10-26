/*

                            _                        _     __  __
     /\                    | |                      | |   |  \/  |
    /  \   _ __   __ _ _ __| |_ _ __ ___   ___ _ __ | |_  | \  / | __ _ _ __   __ _  __ _  ___ _ __
   / /\ \ | '_ \ / _` | '__| __| '_ ` _ \ / _ \ '_ \| __| | |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__|
  / ____ \| |_) | (_| | |  | |_| | | | | |  __/ | | | |_  | |  | | (_| | | | | (_| | (_| |  __/ |
 /_/    \_\ .__/ \__,_|_|   \__|_| |_| |_|\___|_| |_|\__| |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_|
          | |                                                                        __/ |
          |_|                                                                       |___/

	by Grindelf P.
	2024
*/

package to.grindelf.apartmentmanager;

import to.grindelf.apartmentmanager.exceptions.IllegalExecutionModeException;
import to.grindelf.apartmentmanager.utils.ExecutionMode;

import static to.grindelf.apartmentmanager.application.ApartmentManagerJavaFX.startJavaFxApplication;
import static to.grindelf.apartmentmanager.application.ApartmentManagerSpring.startSpringBootApplication;

public class ApartmentManagerApplication {

    private static final ExecutionMode mode = ExecutionMode.DESKTOP;

    public static void main(String[] args) throws IllegalExecutionModeException {

        if (mode == ExecutionMode.DESKTOP) {
            startJavaFxApplication();
        } else if (mode == ExecutionMode.WEB){
            startSpringBootApplication(args);
        } else {
            throw new IllegalExecutionModeException();
        }
    }
}
