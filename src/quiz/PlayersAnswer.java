package quiz;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
@Entity
public class PlayersAnswer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long playersanswer_id_;
	private String name_;
	private boolean correct_;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name="PlayersAnswerQuestion",joinColumns=@JoinColumn(name="PlayersAnswer_ID", 
	   referencedColumnName="playersanswer_id_"), inverseJoinColumns=@JoinColumn(name="Question_ID",
	   referencedColumnName="question_id_"))
	private Question question;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name="PlayerAnswer",joinColumns=@JoinColumn(name="PlayerAnswer_ID", 
	   referencedColumnName="playersanswer_id_"), inverseJoinColumns=@JoinColumn(name="Player_ID",
	   referencedColumnName="user_id_"))
	private Player player;
	
	public PlayersAnswer() {
		name_="";
		correct_=false;
		question=null;
		player=null;
	}   
	public long getAnswer_id_() {
		return this.playersanswer_id_;
	}
	public void setAnswer_id_(long answer_id_) {
		this.playersanswer_id_ = answer_id_;
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
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
