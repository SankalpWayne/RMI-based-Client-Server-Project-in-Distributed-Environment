/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package connectivity;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import com.utils.ErrorNotifier;
import com.utils.ExceptionList;

import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import main.panels.StudentPanel;
import models.User;

public class UniScoreClient {

	 
	public static UniScoreInterface uniscoreInterface;
	
	
	public static LecturerPanel lecturerPanel;
	public static StudentPanel studentPanel;
	public static LoginPanel loginPanel;
	
	
	public static User authUser;
	
	 
	public static String authLocation;

	public UniScoreClient() {}

	
	public static void main(String args[]) {
		try {
			
			// Creating a registry obeject with a specific port to be looked-up
			Registry registry = LocateRegistry.getRegistry(1418);
			System.out.println("Registry located");
			
			// Checking and establishing remote connection if the below url can be accessed using the pre-defined port
			UniScoreClient.uniscoreInterface = (UniScoreInterface) registry.lookup("rmi://localhost/UniScoreServer");
			System.out.println("Server located");

			// If connection to database establisedm if block will execute and else block if not
			if (UniScoreClient.uniscoreInterface.getServer()) {
				System.out.println("Database connectied");
				
				// Getting user's IP address using a WEB API
				UniScoreClient.authLocation = UniScoreClient.uniscoreInterface.getLocation();
				System.out.println("User Located");
				
				// Opening up loginPanel if not exception or error was thrown 
				UniScoreClient.loginPanel = new LoginPanel();
				UniScoreClient.loginPanel.setVisible(true);

			} else {
				// Error message will be popped-up if there was a error in connecting to the databse
				ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator");
				en.setVisible(true);
			}
			
		
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (NotBoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.NOT_BOUND);
			en.setVisible(true);
			System.out.println("NotBoundException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (IOException e) {
			ErrorNotifier en = new ErrorNotifier("Unable to retrieve location. Please make that you are connected to the internet and try again.\nError refferance : "+ExceptionList.IO);
			en.setVisible(true);
			System.out.println("JRException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		}
	}
}
