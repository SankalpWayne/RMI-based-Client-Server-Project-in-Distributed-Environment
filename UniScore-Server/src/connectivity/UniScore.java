/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package connectivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jfree.data.category.CategoryDataset;

import com.utils.Encryptor;
import com.utils.IpifyAPI;
import com.utils.Mail;

import connectors.ActivityConnector;
import connectors.ExamConnector;
import connectors.GradeConnector;
import connectors.LoginConnector;
import connectors.ModuleConnector;
import connectors.QuestionConnector;
import connectors.SubmissionConnector;
import connectors.UserConnector;
import models.Activity;
import models.Exam;
import models.Grade;
import models.Module;
import models.Question;
import models.Submission;
import models.User;
import net.sf.jasperreports.engine.JRException;
import reports.SubmissionReport;

@SuppressWarnings("serial")
public class UniScore extends UnicastRemoteObject implements UniScoreInterface {

	protected UniScore() throws RemoteException, ClassNotFoundException, SQLException {
		super();
	}

	
	@Override
	public boolean getServer() throws RemoteException, ClassNotFoundException, SQLException {
		if(DBConnection.getDBConnection() != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.add(activity);
	}

	@Override
	public Activity getLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.get(activity);
	}

	@Override
	public List<Activity> getLogActivitiesByUser(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.getbyUser(activity);
	}

	@Override
	public List<Activity> getLogActivities() throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.getAll();
	}

	@Override
	public boolean addExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.add(exam);
	}

	@Override
	public boolean updateExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.update(exam);
	}

	@Override
	public boolean removeExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.remove(exam);
	}

	@Override
	public Exam getExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.get(exam);
	}

	@Override
	public List<Exam> getExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getByModule(exam);
	}

	@Override
	public List<Exam> getAvailableExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getByAvailability(exam);
	}

	@Override
	public List<Exam> getExams() throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getAll();
	}

	public int getExamCountByModules(User user) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getCountByModules(user);
	}

	@Override
	public boolean addGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.add(grade);
	}

	@Override
	public boolean updateGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.update(grade);
	}

	@Override
	public Grade getGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.get(grade);
	}

	@Override
	public List<Grade> getGrades() throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.getAll();
	}

	@Override
	public boolean addModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.add(module);
	}

	@Override
	public boolean updateModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.update(module);
	}

	@Override
	public boolean removeModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.remove(module);
	}

	@Override
	public Module getModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.get(module);
	}

	@Override
	public List<Module> getModules() throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.getAll();
	}

	@Override
	public List<Module> getModulesByRelevance(Module module, int year, int semester) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.getByYearAndUser(module, year, semester);
	}
	

	public int getModuleCountByUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.getCountByUser(user);
	}

	@Override
	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.add(question);
	}

	@Override
	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.update(question);
	}

	@Override
	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.remove(question);
	}

	@Override
	public Question getQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.get(question);
	}
	@Override
	public List<Question> getQuestions() throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.getAll();
	}

	@Override
	public List<Question> getExamQuestions(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.getByExamination(question);
	}

	@Override
	public int getExaminationQuestionCount(Question question) throws RemoteException, ClassNotFoundException, SQLException{
		QuestionConnector qc = new QuestionConnector();
		return qc.getQuestionCountByExamination(question);
	}

	public List<Question> getExamQuestionsBySearch(String searchString) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.getBySearch(searchString);
	}

	@Override
	public boolean addSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.add(submission);
	}

	@Override
	public Submission getSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.get(submission);
	}

	@Override
	public List<Submission> getSubmissionsByRelevance(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getByRelevance(submission);
	}

	@Override
	public List<Submission> getSubmissions() throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getAll();
	}

	public int getExaminationSubmissionCount(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getSubmissionCountByExamination(submission);
	}

	public CategoryDataset getSubmissionDatasetByExam(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getDatasetByExam(submission);
	}

	public CategoryDataset getGradedDatasetByStudent(Module module, Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getDatasetByStudent(module, submission);
	}

	public String getSubmissionTableByExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getListAsTable(exam);
	}

	@Override
	public boolean addUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.add(user);
	}

	@Override
	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.update(user);
	}
	@Override
	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.remove(user);
	}

	@Override
	public User getUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.get(user);
	}

	public User getUserByCredentials(User user) throws RemoteException, ClassNotFoundException, SQLException{
		UserConnector uc = new UserConnector();
		return uc.getbyCredentials(user);
	}

	@Override
	public List<User> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.getAll();
	}

	public List<User> getUsersByType(User user) throws RemoteException, ClassNotFoundException, SQLException{
		UserConnector uc = new UserConnector();
		return uc.getByType(user);
	}

	public List<User> getUsersBySearch(String searchString) throws RemoteException, ClassNotFoundException, SQLException{
		UserConnector uc = new UserConnector();
		return uc.getBySearch(searchString);
	}

	public int getUserCountByRole(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.getCountByRole(user);
	}

	public boolean isUserAvailable(User user) throws RemoteException, ClassNotFoundException, SQLException{
		LoginConnector lc = new LoginConnector();
		return lc.authenticateUser(user);
	}

	public String encrypt(User user) throws RemoteException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, NoSuchProviderException{
		return Encryptor.getEncryptedPassword(user);
	}

	public void printSubmissionReport(int examId, String examName, String moduleId) throws RemoteException, ClassNotFoundException, SQLException, JRException {
		new SubmissionReport(examId, examName, moduleId);
	}

	public String getLocation() throws RemoteException, ClassNotFoundException, MalformedURLException, IOException {
		return IpifyAPI.getIP();
	}

	public void sendMail(String recepients, String subject, String htmlBody) throws RemoteException,  AddressException, MessagingException {
		Mail.send(recepients, subject, htmlBody);
	}
	
}
