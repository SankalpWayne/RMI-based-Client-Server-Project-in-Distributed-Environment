/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package connectivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jfree.data.category.CategoryDataset;

import models.Activity;
import models.Exam;
import models.Grade;
import models.Module;
import models.Question;
import models.Submission;
import models.User;
import net.sf.jasperreports.engine.JRException;

public interface UniScoreInterface extends Remote {

	/*
	 * The remote interface is required to be present on both client and server projects under the same package name. 
	 * UniScore class consist of all the implemented methods
	 */

	/*
	 * Checks for the connection status to the databse
	 * @return {boolen} returns true if the connection to database is established and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean getServer() throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean addLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;
	
	public Activity getLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Activity> getLogActivitiesByUser(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Activity> getLogActivities() throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean addExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean updateExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	public Exam getExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Exam> getExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Exam> getAvailableExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Exam> getExams() throws RemoteException, ClassNotFoundException, SQLException;
	
	public int getExamCountByModules(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean updateGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	public Grade getGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Grade> getGrades() throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public Module getModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Module> getModules() throws RemoteException, ClassNotFoundException, SQLException;

	public List<Module> getModulesByRelevance(Module module, int year, int semester) throws RemoteException, ClassNotFoundException, SQLException;

	public int getModuleCountByUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public Question getQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Question> getQuestions() throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Question> getExamQuestions(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	public int getExaminationQuestionCount(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Question> getExamQuestionsBySearch(String searchString) throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean addSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;
	
	public Submission getSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Submission> getSubmissionsByRelevance(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Submission> getSubmissions() throws RemoteException, ClassNotFoundException, SQLException;

	public int getExaminationSubmissionCount(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public CategoryDataset getSubmissionDatasetByExam(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public CategoryDataset getGradedDatasetByStudent(Module module, Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public String getSubmissionTableByExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public User getUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public User getUserByCredentials(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public List<User> getUsers() throws RemoteException, ClassNotFoundException, SQLException;

	public List<User> getUsersByType(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public List<User> getUsersBySearch(String searchString) throws RemoteException, ClassNotFoundException, SQLException;

	public int getUserCountByRole(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean isUserAvailable(User user) throws RemoteException, ClassNotFoundException, SQLException;

	public String encrypt(User user) throws RemoteException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, NoSuchProviderException;

	public void printSubmissionReport(int examId, String examName, String moduleId) throws RemoteException, ClassNotFoundException, SQLException, JRException;

	public String getLocation() throws RemoteException, ClassNotFoundException, MalformedURLException, IOException;

	public void sendMail(String recepients, String subject, String htmlBody) throws RemoteException,  AddressException, MessagingException;
	
}
