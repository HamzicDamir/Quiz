package quiz;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
@Entity
public class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long answer_id_;
	private String name_;
	private boolean correct_;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name="AnswerQuestion",joinColumns=@JoinColumn(name="Answer_ID", 
	   referencedColumnName="answer_id_"), inverseJoinColumns=@JoinColumn(name="Question_ID",
	   referencedColumnName="question_id_"))
	private Question question;
	
	public Answer() {
		name_="";
		correct_=false;
		question=null;
	}   
	public long getAnswer_id_() {
		return this.answer_id_;
	}
	public void setAnswer_id_(long answer_id_) {
		this.answer_id_ = answer_id_;
	}   
	public String getName_() {
		return this.name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}   
	public boolean getCorrect_() {
		return this.correct_;
	}
	public void setCorrect_(boolean correct_) {
		this.correct_ = correct_;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
