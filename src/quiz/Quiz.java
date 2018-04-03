package quiz;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;
@Entity
public class Quiz implements Serializable {	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long quiz_id_;
	private String name_;
	private boolean is_active_;
	private String photo_;
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy="quiz")
	private Collection<Question> questions_;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name="QuizEditor",joinColumns=@JoinColumn(name="Quiz_ID", 
	   referencedColumnName="quiz_id_"), inverseJoinColumns=@JoinColumn(name="Editor_ID",
	   referencedColumnName="editor_id_"))
	private Editor editor;
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy="quiz")
	private Collection<Player> players;
	public Quiz() {
		name_="";
		is_active_=false;
		questions_ = new ArrayList<Question>();
		players = new ArrayList<Player>();
	}   
	public long getQuiz_id_() {
		return this.quiz_id_;
	}
	public void setQuiz_id_(long quiz_id_) {
		this.quiz_id_ = quiz_id_;
	}   
	public String getName_() {
		return this.name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}   
	public boolean getIs_active_() {
		return this.is_active_;
	}

	public void setIs_active_(boolean is_active_) {
		this.is_active_ = is_active_;
	}   
	public Collection<Question> getQuestions() {
		return this.questions_;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions_ = questions;
	}
	public void addQuestion(Question question)
	{
		this.questions_.add(question);
	}
	public Collection<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void addPlayer(Player player)
	{
		this.players.add(player);
	}

	public void printQuestion() {
		Iterator<Question>iterator = this.questions_.iterator();
		while(iterator.hasNext())
		{
			Question a=iterator.next();
			System.out.println("Pitanje:"+" "+a.getName_()+" Tacan: "+a.getQuestion_id_());
			a.printAnswers();
		}
	}
	public Editor getEditor_() {
		return editor;
	}
	public void setEditor(Editor editor_) {
		this.editor = editor_;
	}
	public String getPhoto_() {
		return photo_;
	}
	public void setPhoto_(String photo_) {
		this.photo_ = photo_;
	}
}