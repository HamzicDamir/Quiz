package quiz;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;
@Entity
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long question_id_;
	private String name_;
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy="question")
	private Collection<Answer> answers_;
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy="question")
	private Collection<PlayersAnswer> playersanswers_;
	private int number_of_seconds_;
	private int points_;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name="QuestionQuiz",joinColumns=@JoinColumn(name="Question_ID", 
	   referencedColumnName="question_id_"), inverseJoinColumns=@JoinColumn(name="Quiz_ID",
	   referencedColumnName="quiz_id_"))
	private Quiz quiz;
	private int question_num_;
	public Question(){
		this.name_ = "";
		this.question_num_ = 0;
		this.number_of_seconds_ = 0;
		this.points_ = 0;
		this.answers_ = new ArrayList<Answer>();
		this.playersanswers_ = new ArrayList<PlayersAnswer>();
		this.quiz =null;
	}   
	public long getQuestion_id_() {
		return this.question_id_;
	}

	public void setQuestion_id_(long question_id_) {
		this.question_id_ = question_id_;
	}   
	public String getName_() {
		return this.name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}   
	public Collection<Answer> getAnswers_() {
		return this.answers_;
	}
	public void setAnswers_(ArrayList<Answer> answers_) {
		this.answers_ = answers_;
	}
	public Collection<PlayersAnswer> getPlayersAnswers_() {
		return this.playersanswers_;
	}
	public void setPlayersAnswers_(ArrayList<PlayersAnswer> answers_) {
		this.playersanswers_ = answers_;
	}
	public void addAnswer(Answer answer)
	{
		this.answers_.add(answer);
	}
	public void addPlayersAnswer(PlayersAnswer answer)
	{
		this.playersanswers_.add(answer);
	}
	public void printAnswers() {
		Iterator<Answer>iterator = this.answers_.iterator();
		while(iterator.hasNext())
		{
			Answer a=iterator.next();
			System.out.println("Odgovor:"+" "+a.getName_()+" Tacan: "+a.getCorrect_());
		}
		
	}
	public void printPlayersAnswers() {
		Iterator<PlayersAnswer>iterator = this.playersanswers_.iterator();
		while(iterator.hasNext())
		{
			PlayersAnswer a=iterator.next();
			System.out.println("Odgovor:"+" "+a.getName_()+" Tacan: "+a.getCorrect_());
		}
		
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public int getNumber_of_seconds_() {
		return number_of_seconds_;
	}
	public void setNumber_of_seconds_(int number_of_seconds_) {
		this.number_of_seconds_ = number_of_seconds_;
	}
	public int getPoints_() {
		return points_;
	}
	public void setPoints_(int points_) {
		this.points_ = points_;
	}
	public int getQuestion_num() {
		return question_num_;
	}
	public void setQuestion_num(int question_num) {
		this.question_num_ = question_num;
	}
	
}

