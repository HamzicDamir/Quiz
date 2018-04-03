package quiz;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;
@Entity
public class Editor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long editor_id_;
	private String first_name_;
	private String last_name_;
	private String e_mail_;
	private String username_;
	private String password_;
	
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy="editor")
	private Collection<Quiz> quizes;

	public Editor() {
		this.first_name_ = "";
		this.last_name_ = "";
		this.e_mail_ = "";
		this.username_ = "";
		this.password_ = "";
		this.quizes=new ArrayList<Quiz>();
		
	}   
	public Editor(String username,String password,String first_name,String last_name,String email)
	{
		this.first_name_=first_name;
		this.last_name_=last_name;
		this.username_=username;
		this.password_=password;
		this.e_mail_=email;
	}
	public void SetEditor(String username,String password,String first_name,String last_name,String email)
	{
		this.first_name_=first_name;
		this.last_name_=last_name;
		this.username_=username;
		this.password_=password;
		this.e_mail_=email;
	}
	public long getEditor_id() {
		return this.editor_id_;
	}

	public void setEditor_id(long editor_id) {
		this.editor_id_ = editor_id;
	}   
	public String getUsername_() {
		return this.username_;
	}

	public void setUsername_(String username_) {
		this.username_ = username_;
	}   
	public String getPassword_() {
		return this.password_;
	}

	public void setPassword_(String password_) {
		this.password_ = password_;
	}   
	public Collection<Quiz> getQuizes() {
		return this.quizes;
	}

	public void setQuizes(ArrayList<Quiz> quizes) {
		this.quizes = quizes;
	}
	public String getFirst_name_() {
		return first_name_;
	}
	public void setFirst_name_(String first_name_) {
		this.first_name_ = first_name_;
	}
	public String getLast_name_() {
		return last_name_;
	}
	public void setLast_name_(String last_name_) {
		this.last_name_ = last_name_;
	}
	public String getE_mail() {
		return e_mail_;
	}
	public void setE_mail(String e_mail) {
		this.e_mail_ = e_mail;
	}
	public void addQuiz(Quiz quiz)
	{
		this.quizes.add(quiz);
	}
	public void print()
	{
		System.out.println(this.first_name_+" "+this.last_name_+" "+this.username_+" "+this.password_+" "+this.e_mail_);
	}
	public void printQuiz() {
		Iterator<Quiz>iterator = this.quizes.iterator();
		while(iterator.hasNext())
		{
			Quiz a=iterator.next();
			System.out.println("Quiz:"+" "+a.getName_()+" Editor: "+a.getEditor_().getFirst_name_());
			a.printQuestion();
		}
		
	}
}
