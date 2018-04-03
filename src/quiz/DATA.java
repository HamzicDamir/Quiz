package quiz;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DATA {

	private static final String PERSISTENCE_UNIT = "QuizServer";

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		
		Editor editor=new Editor();
		editor.setFirst_name_("Damir");
		editor.setLast_name_("Hamzic");
		editor.setPassword_("damir");
		editor.setUsername_("damir");
		editor.setE_mail("damir-hamzic@live.com");
		
		Answer answer1_1=new Answer();
		answer1_1.setName_("static");
		answer1_1.setCorrect_(false);
		Answer answer1_2=new Answer();
		answer1_2.setName_("Boolean");
		answer1_2.setCorrect_(true);
		Answer answer1_3=new Answer();
		answer1_3.setName_("void");
		answer1_3.setCorrect_(false);
		Answer answer1_4=new Answer();
		answer1_4.setName_("private");
		answer1_4.setCorrect_(false);
		Question question1=new Question();
		question1.setName_("Which of the following is not a keyword in java?");
		answer1_1.setQuestion(question1);
		answer1_2.setQuestion(question1);
		answer1_3.setQuestion(question1);
		answer1_4.setQuestion(question1);
		question1.addAnswer(answer1_1);
		question1.addAnswer(answer1_2);
		question1.addAnswer(answer1_3);
		question1.addAnswer(answer1_4);
		
		Answer answer2_1=new Answer();
		answer2_1.setName_("8 bit");
		answer2_1.setCorrect_(false);
		Answer answer2_2=new Answer();
		answer2_2.setName_("16 bit");
		answer2_2.setCorrect_(false);
		Answer answer2_3=new Answer();
		answer2_3.setName_("32 bit");
		answer2_3.setCorrect_(true);
		Answer answer2_4=new Answer();
		answer2_4.setName_("64 bit");
		answer2_4.setCorrect_(false);
		Question question2=new Question();
		question2.setName_("What is the size of float variable?");
		answer2_1.setQuestion(question2);
		answer2_2.setQuestion(question2);
		answer2_3.setQuestion(question2);
		answer2_4.setQuestion(question2);
		question2.addAnswer(answer2_1);
		question2.addAnswer(answer2_2);
		question2.addAnswer(answer2_3);
		question2.addAnswer(answer2_4);
		
		Answer answer3_1=new Answer();
		answer3_1.setName_("0.0d");
		answer3_1.setCorrect_(false);
		Answer answer3_2=new Answer();
		answer3_2.setName_("0.0f");
		answer3_2.setCorrect_(true);
		Answer answer3_3=new Answer();
		answer3_3.setName_("0");
		answer3_3.setCorrect_(false);
		Answer answer3_4=new Answer();
		answer3_4.setName_("not defined");
		answer3_4.setCorrect_(false);
		Question question3=new Question();
		question3.setName_("What is the default value of float variable?");
		answer3_1.setQuestion(question3);
		answer3_2.setQuestion(question3);
		answer3_3.setQuestion(question3);
		answer3_4.setQuestion(question3);
		question3.addAnswer(answer3_1);
		question3.addAnswer(answer3_2);
		question3.addAnswer(answer3_3);
		question3.addAnswer(answer3_4);
		
		Answer answer4_1=new Answer();
		answer4_1.setName_("a collection of abstract methods.");
		answer4_1.setCorrect_(false);
		Answer answer4_2=new Answer();
		answer4_2.setName_("an abstract class.");
		answer4_2.setCorrect_(false);
		Answer answer4_3=new Answer();
		answer4_3.setName_("an concrete class.");
		answer4_3.setCorrect_(true);
		Answer answer4_4=new Answer();
		answer4_4.setName_("none of the above.");
		answer4_4.setCorrect_(false);
		Question question4=new Question();
		question4.setName_("What is an Interface?");
		answer4_1.setQuestion(question4);
		answer4_2.setQuestion(question4);
		answer4_3.setQuestion(question4);
		answer4_4.setQuestion(question4);
		question4.addAnswer(answer4_1);
		question4.addAnswer(answer4_2);
		question4.addAnswer(answer4_3);
		question4.addAnswer(answer4_4);
		
		Answer answer5_1=new Answer();
		answer5_1.setName_("type");
		answer5_1.setCorrect_(false);
		Answer answer5_2=new Answer();
		answer5_2.setName_("object");
		answer5_2.setCorrect_(true);
		Answer answer5_3=new Answer();
		answer5_3.setName_("both of the above");
		answer5_3.setCorrect_(false);
		Answer answer5_4=new Answer();
		answer5_4.setName_("none of the above");
		answer4_4.setCorrect_(false);
		Question question5=new Question();
		question5.setName_("Dynamic binding uses which information for binding?");
		answer5_1.setQuestion(question5);
		answer5_2.setQuestion(question5);
		answer5_3.setQuestion(question5);
		answer5_4.setQuestion(question5);
		question5.addAnswer(answer5_1);
		question5.addAnswer(answer5_2);
		question5.addAnswer(answer5_3);
		question5.addAnswer(answer5_4);
		
		Answer answer6_1=new Answer();
		answer6_1.setName_("comparable");
		answer6_1.setCorrect_(false);
		Answer answer6_2=new Answer();
		answer6_2.setName_("cloneable");
		answer6_2.setCorrect_(false);
		Answer answer6_3=new Answer();
		answer6_3.setName_("serializable");
		answer6_3.setCorrect_(true);
		Answer answer6_4=new Answer();
		answer6_4.setName_("none of the above.");
		answer4_4.setCorrect_(false);
		Question question6=new Question();
		question6.setName_("Which of the following is a marker interface?");
		answer6_1.setQuestion(question6);
		answer6_2.setQuestion(question6);
		answer6_3.setQuestion(question6);
		answer6_4.setQuestion(question6);
		question6.addAnswer(answer6_1);
		question6.addAnswer(answer6_2);
		question6.addAnswer(answer6_3);
		question6.addAnswer(answer6_4);
		
		Answer answer7_1=new Answer();
		answer7_1.setName_("string can be created using new operator");
		answer7_1.setCorrect_(false);
		Answer answer7_2=new Answer();
		answer7_2.setName_("string is immutable");
		answer7_2.setCorrect_(false);
		Answer answer7_3=new Answer();
		answer7_3.setName_("string is a primary data type");
		answer7_3.setCorrect_(true);
		Answer answer7_4=new Answer();
		answer7_4.setName_("none of the above");
		answer7_4.setCorrect_(false);
		Question question7=new Question();
		question7.setName_("Which of the following is false about String?");
		answer7_1.setQuestion(question7);
		answer7_2.setQuestion(question7);
		answer7_3.setQuestion(question7);
		answer7_4.setQuestion(question7);
		question7.addAnswer(answer7_1);
		question7.addAnswer(answer7_2);
		question7.addAnswer(answer7_3);
		question7.addAnswer(answer7_4);
		
		Answer answer8_1=new Answer();
		answer8_1.setName_("true");
		answer8_1.setCorrect_(false);
		Answer answer8_2=new Answer();
		answer8_2.setName_("false");
		answer8_2.setCorrect_(true);
		Question question8=new Question();
		question8.setName_("A class always has a default constructor?");
		answer8_1.setQuestion(question8);
		answer8_2.setQuestion(question8);
		question8.addAnswer(answer8_1);
		question8.addAnswer(answer8_2);
		
		Answer answer9_1=new Answer();
		answer9_1.setName_("true");
		answer9_1.setCorrect_(false);
		Answer answer9_2=new Answer();
		answer9_2.setName_("false");
		answer9_2.setCorrect_(true);
		Question question9=new Question();
		question9.setName_("Objects are stored on Stack?");
		answer9_1.setQuestion(question9);
		answer9_2.setQuestion(question9);
		question9.addAnswer(answer9_1);
		question9.addAnswer(answer9_2);
		
		Quiz quiz=new Quiz();
		question1.setNumber_of_seconds_(30);
		question1.setPoints_(5);
		question1.setQuiz(quiz);
		
		question2.setNumber_of_seconds_(30);
		question2.setPoints_(5);
		question2.setQuiz(quiz);
		
		question3.setNumber_of_seconds_(30);
		question3.setPoints_(5);
		question3.setQuiz(quiz);
		
		question4.setNumber_of_seconds_(30);
		question4.setPoints_(5);
		question4.setQuiz(quiz);
		
		question5.setNumber_of_seconds_(30);
		question5.setPoints_(5);
		question5.setQuiz(quiz);
		
		question6.setNumber_of_seconds_(30);
		question6.setPoints_(5);
		question6.setQuiz(quiz);
		
		question7.setNumber_of_seconds_(30);
		question7.setPoints_(5);
		question7.setQuiz(quiz);
		
		question8.setNumber_of_seconds_(30);
		question8.setPoints_(5);
		question8.setQuiz(quiz);
		
		question9.setNumber_of_seconds_(30);
		question9.setPoints_(5);
		question9.setQuiz(quiz);
		
		
		quiz.addQuestion(question1);
		quiz.addQuestion(question2);
		quiz.addQuestion(question3);
		quiz.addQuestion(question4);
		quiz.addQuestion(question5);
		quiz.addQuestion(question6);
		quiz.addQuestion(question7);
		quiz.addQuestion(question8);
		quiz.addQuestion(question9);
		quiz.setEditor(editor);
		editor.addQuiz(quiz);
		quiz.setName_("Java");
		quiz.setPhoto_("photo1.jpg");
		
		em.getTransaction().begin();
		em.persist(answer1_1);
		em.persist(answer1_2);
		em.persist(answer1_3);
		em.persist(answer1_4);
		
		em.persist(answer2_1);
		em.persist(answer2_2);
		em.persist(answer2_3);
		em.persist(answer2_4);
		
		em.persist(answer3_1);
		em.persist(answer3_2);
		em.persist(answer3_3);
		em.persist(answer3_4);
		
		em.persist(answer4_1);
		em.persist(answer4_2);
		em.persist(answer4_3);
		em.persist(answer4_4);
		
		em.persist(answer5_1);
		em.persist(answer5_2);
		em.persist(answer5_3);
		em.persist(answer5_4);
		
		em.persist(answer6_1);
		em.persist(answer6_2);
		em.persist(answer6_3);
		em.persist(answer6_4);
		
		em.persist(answer7_1);
		em.persist(answer7_2);
		em.persist(answer7_3);
		em.persist(answer7_4);
		
		em.persist(answer8_1);
		em.persist(answer8_2);
		
		em.persist(answer9_1);
		em.persist(answer9_2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		em.persist(question1);
		em.persist(question2);
		em.persist(question3);
		em.persist(question4);
		em.persist(question5);
		em.persist(question6);
		em.persist(question7);
		em.persist(question8);
		em.persist(question9);
		
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(quiz);
		em.persist(editor);
		em.getTransaction().commit();
		
		Answer ans1_1 = new Answer();
		ans1_1.setName_("5");
		ans1_1.setCorrect_(false);
		Answer ans2_1 = new Answer();
		ans2_1.setName_("10");
		ans2_1.setCorrect_(false);
		Answer ans3_1 = new Answer();
		ans3_1.setName_("25");
		ans3_1.setCorrect_(true);
		
		Answer ans1_2 = new Answer();
		ans1_2.setName_("3");
		ans1_2.setCorrect_(false);
		Answer ans2_2 = new Answer();
		ans2_2.setName_("0");
		ans2_2.setCorrect_(false);
		Answer ans3_2 = new Answer();
		ans3_2.setName_("1");
		ans3_2.setCorrect_(true);
		
		Answer ans1_3 = new Answer();
		ans1_3.setName_("4");
		ans1_3.setCorrect_(true);
		Answer ans2_3 = new Answer();
		ans2_3.setName_("5");
		ans2_3.setCorrect_(false);
		Answer ans3_3 = new Answer();
		ans3_3.setName_("7");
		ans3_3.setCorrect_(false);
		
		Question qsn1 = new Question();
		qsn1.setName_("Koliko je 5*5?");
		qsn1.setNumber_of_seconds_(30);
		qsn1.setPoints_(5);
		ans1_1.setQuestion(qsn1);
		ans2_1.setQuestion(qsn1);
		ans3_1.setQuestion(qsn1);
		qsn1.addAnswer(ans1_1);
		qsn1.addAnswer(ans2_1);
		qsn1.addAnswer(ans3_1);
		
		
		Question qsn2 = new Question();
		qsn2.setName_("Koliko je 3/3?");
		qsn2.setNumber_of_seconds_(30);
		qsn2.setPoints_(5);
		ans1_2.setQuestion(qsn2);
		ans2_2.setQuestion(qsn2);
		ans3_2.setQuestion(qsn2);
		
		qsn2.addAnswer(ans1_2);
		qsn2.addAnswer(ans2_2);
		qsn2.addAnswer(ans3_2);
		
		
		Question qsn3 = new Question();
		qsn3.setName_("Koliko je 2+2?");
		qsn3.setNumber_of_seconds_(30);
		qsn3.setPoints_(5);
		qsn3.addAnswer(ans1_3);
		qsn3.addAnswer(ans2_3);
		qsn3.addAnswer(ans3_3);
		ans1_3.setQuestion(qsn3);
		ans2_3.setQuestion(qsn3);
		ans3_3.setQuestion(qsn3);
		
		Quiz quiz2=new Quiz();
		quiz2.setName_("Matematika");
		quiz2.setPhoto_("photo2");
		qsn1.setQuiz(quiz2);
		qsn2.setQuiz(quiz2);
		qsn3.setQuiz(quiz2);
		
		quiz2.addQuestion(qsn1);
		quiz2.addQuestion(qsn2);
		quiz2.addQuestion(qsn3);
		
		em.getTransaction().begin();
		em.persist(ans1_1);
		em.persist(ans1_2);
		em.persist(ans1_3);
		
		em.persist(ans2_1);
		em.persist(ans2_2);
		em.persist(ans2_3);
		
		em.persist(ans3_1);
		em.persist(ans3_2);
		em.persist(ans3_3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(qsn1);
		em.persist(qsn2);
		em.persist(qsn3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(quiz2);
		em.getTransaction().commit();
		
		Answer answ1_1 = new Answer();
		answ1_1.setName_("Tuzla");
		answ1_1.setCorrect_(false);
		Answer answ2_1 = new Answer();
		answ2_1.setName_("Banja Luka");
		answ2_1.setCorrect_(false);
		Answer answ3_1 = new Answer();
		answ3_1.setName_("Sarajevo");
		answ3_1.setCorrect_(true);
		
		Answer answ1_2 = new Answer();
		answ1_2.setName_("Novi Sad");
		answ1_2.setCorrect_(false);
		Answer answ2_2 = new Answer();
		answ2_2.setName_("Beograd");
		answ2_2.setCorrect_(true);
		Answer answ3_2 = new Answer();
		answ3_2.setName_("Valjevo");
		answ3_2.setCorrect_(false);
		
		Answer answ1_3 = new Answer();
		answ1_3.setName_("Zagreb");
		answ1_3.setCorrect_(true);
		Answer answ2_3 = new Answer();
		answ2_3.setName_("Split");
		answ2_3.setCorrect_(false);
		Answer answ3_3 = new Answer();
		answ3_3.setName_("Rijeka");
		answ3_3.setCorrect_(false);
		
		Question qusn1 = new Question();
		qusn1.setName_("Koji je glavni grad BiH?");
		qusn1.setNumber_of_seconds_(30);
		qusn1.setPoints_(5);
		qusn1.addAnswer(answ1_1);
		qusn1.addAnswer(answ2_1);
		qusn1.addAnswer(answ3_1);
		answ1_1.setQuestion(qusn1);
		answ2_1.setQuestion(qusn1);
		answ3_1.setQuestion(qusn1);
		Question qusn2 = new Question();
		qusn2.setName_("Koji je glavni grad Srbije?");
		qusn2.setNumber_of_seconds_(30);
		qusn2.setPoints_(5);
		qusn2.addAnswer(answ1_2);
		qusn2.addAnswer(answ2_2);
		qusn2.addAnswer(answ3_2);
		answ1_2.setQuestion(qusn2);
		answ2_2.setQuestion(qusn2);
		answ3_2.setQuestion(qusn2);
		
		Question qusn3 = new Question();
		qusn3.setName_("Koji je glavni grad Hrvatske?");
		qusn3.setNumber_of_seconds_(30);
		qusn3.setPoints_(5);
		qusn3.addAnswer(answ1_3);
		qusn3.addAnswer(answ2_3);
		qusn3.addAnswer(answ3_3);
		answ1_3.setQuestion(qusn3);
		answ2_3.setQuestion(qusn3);
		answ3_3.setQuestion(qusn3);
		
		Quiz quiz3=new Quiz();
		quiz3.setName_("Glavni gradovi");
		quiz3.setPhoto_("photo3");
		qusn1.setQuiz(quiz3);
		qusn2.setQuiz(quiz3);
		qusn3.setQuiz(quiz3);
		
		quiz3.addQuestion(qusn1);
		quiz3.addQuestion(qusn2);
		quiz3.addQuestion(qusn3);
		
		em.getTransaction().begin();
		em.persist(answ1_1);
		em.persist(answ1_2);
		em.persist(answ1_3);
		
		em.persist(answ2_1);
		em.persist(answ2_2);
		em.persist(answ2_3);
		
		em.persist(answ3_1);
		em.persist(answ3_2);
		em.persist(answ3_3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(qusn1);
		em.persist(qusn2);
		em.persist(qusn3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(quiz3);
		em.getTransaction().commit();
		
		Answer answr1_1 = new Answer();
		answr1_1.setName_("auto_ptr");
		answr1_1.setCorrect_(false);
		Answer answr2_1 = new Answer();
		answr2_1.setName_("p");
		answr2_1.setCorrect_(false);
		Answer answr3_1 = new Answer();
		answr3_1.setName_("none of the above");
		answr3_1.setCorrect_(true);
		
		Answer answr1_2 = new Answer();
		answr1_2.setName_("true");
		answr1_2.setCorrect_(false);
		Answer answr2_2 = new Answer();
		answr2_2.setName_("false");
		answr2_2.setCorrect_(true);
		
		Answer answr1_3 = new Answer();
		answr1_3.setName_("Object");
		answr1_3.setCorrect_(true);
		Answer answr2_3 = new Answer();
		answr2_3.setName_("Package");
		answr2_3.setCorrect_(false);
		Answer answr3_3 = new Answer();
		answr3_3.setName_("Class");
		answr3_3.setCorrect_(false);
		
		Question quesn1 = new Question();
		quesn1.setName_("he pointer which stores always the current active object address is __?");
		quesn1.setNumber_of_seconds_(30);
		quesn1.setPoints_(5);
		quesn1.addAnswer(answr1_1);
		quesn1.addAnswer(answr2_1);
		quesn1.addAnswer(answr3_1);
		answr1_1.setQuestion(quesn1);
		answr2_1.setQuestion(quesn1);
		answr3_1.setQuestion(quesn1);
		Question quesn2 = new Question();
		quesn2.setName_(" A constructor can be virtual.?");
		quesn2.setNumber_of_seconds_(30);
		quesn2.setPoints_(2);
		quesn2.addAnswer(answr1_2);
		quesn2.addAnswer(answr2_2);
		answr1_2.setQuestion(quesn2);
		answr2_2.setQuestion(quesn2);
		
		Question quesn3 = new Question();
		quesn3.setName_("‘cin’ is an __?");
		quesn3.setNumber_of_seconds_(30);
		quesn3.setPoints_(3);
		quesn3.addAnswer(answr1_3);
		quesn3.addAnswer(answr2_3);
		quesn3.addAnswer(answr3_3);
		answr1_3.setQuestion(quesn3);
		answr2_3.setQuestion(quesn3);
		answr3_3.setQuestion(quesn3);
		
		Quiz quiz4=new Quiz();
		quiz4.setName_("C++");
		quiz4.setPhoto_("photo3");
		quesn1.setQuiz(quiz4);
		quesn2.setQuiz(quiz4);
		quesn3.setQuiz(quiz4);
		
		quiz4.addQuestion(quesn1);
		quiz4.addQuestion(quesn2);
		quiz4.addQuestion(quesn3);
		
		em.getTransaction().begin();
		em.persist(answr1_1);
		em.persist(answr1_2);
		em.persist(answr1_3);
		
		em.persist(answr2_1);
		em.persist(answr2_2);
		em.persist(answr2_3);
		
		em.persist(answr3_1);
		em.persist(answr3_3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(quesn1);
		em.persist(quesn2);
		em.persist(quesn3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(quiz4);
		em.getTransaction().commit();
		
		Answer anwr1_1 = new Answer();
		anwr1_1.setName_("short float");
		anwr1_1.setCorrect_(false);
		Answer anwr2_1 = new Answer();
		anwr2_1.setName_("short char");
		anwr2_1.setCorrect_(false);
		Answer anwr3_1 = new Answer();
		anwr3_1.setName_("short int");
		anwr3_1.setCorrect_(true);
		
		Answer anwr1_2 = new Answer();
		anwr1_2.setName_("110010");
		anwr1_2.setCorrect_(true);
		Answer anwr2_2 = new Answer();
		anwr2_2.setName_("1010110");
		anwr2_2.setCorrect_(false);
		Answer anwr3_2 = new Answer();
		anwr3_2.setName_("101");
		anwr3_2.setCorrect_(false);
		
		Answer anwr1_3 = new Answer();
		anwr1_3.setName_("dealloc();");
		anwr1_3.setCorrect_(false);
		Answer anwr2_3 = new Answer();
		anwr2_3.setName_("strcat();");
		anwr2_3.setCorrect_(false);
		Answer anwr3_3 = new Answer();
		anwr3_3.setName_("free();");
		anwr3_3.setCorrect_(true);
		
		Question qesn1 = new Question();
		qesn1.setName_("The type name/reserved word ‘short’ is ___?");
		qesn1.setNumber_of_seconds_(20);
		qesn1.setPoints_(2);
		qesn1.addAnswer(anwr1_1);
		qesn1.addAnswer(anwr2_1);
		qesn1.addAnswer(anwr3_1);
		anwr1_1.setQuestion(qesn1);
		anwr2_1.setQuestion(qesn1);
		anwr3_1.setQuestion(qesn1);
		
		Question qesn2 = new Question();
		qesn2.setName_("The binary equivalent of 50 is?");
		qesn2.setNumber_of_seconds_(50);
		qesn2.setPoints_(5);
		qesn2.addAnswer(anwr1_2);
		qesn2.addAnswer(anwr2_2);
		qesn2.addAnswer(anwr3_2);
		anwr1_2.setQuestion(qesn2);
		anwr2_2.setQuestion(qesn2);
		anwr3_2.setQuestion(qesn2);
		
		Question qesn3 = new Question();
		qesn3.setName_("What function can be used to free the memory allocated by calloc()?");
		qesn3.setNumber_of_seconds_(30);
		qesn3.setPoints_(5);
		qesn3.addAnswer(anwr1_3);
		qesn3.addAnswer(anwr2_3);
		qesn3.addAnswer(anwr3_3);
		anwr1_3.setQuestion(qesn3);
		anwr2_3.setQuestion(qesn3);
		anwr3_3.setQuestion(qesn3);
		
		Quiz quiz5=new Quiz();
		quiz5.setName_("C");
		quiz5.setPhoto_("photo3");
		qesn1.setQuiz(quiz5);
		qesn2.setQuiz(quiz5);
		qesn3.setQuiz(quiz5);
		
		quiz5.addQuestion(qesn1);
		quiz5.addQuestion(qesn2);
		quiz5.addQuestion(qesn3);
		
		em.getTransaction().begin();
		em.persist(anwr1_1);
		em.persist(anwr1_2);
		em.persist(anwr1_3);
		
		em.persist(anwr2_1);
		em.persist(anwr2_2);
		em.persist(anwr2_3);
		
		em.persist(anwr3_1);
		em.persist(anwr3_2);
		em.persist(anwr3_3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(qesn1);
		em.persist(qesn2);
		em.persist(qesn3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(quiz5);
		em.getTransaction().commit();
	}
}
