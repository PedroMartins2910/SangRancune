package common;

import java.io.Serializable;

public class Message implements Serializable{
	
	/**
	 * Attributs Sender (expéditeur) Content (contenu)
	 */
	private String sender;
	private String content;
	
	public Message(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}

	/**
	 * Methode toString pour afficher les deux attributs
	 */
	@Override
	public String toString() {
		return "Messages [sender=" + sender + ", content=" + content + "]";
	}

	public void setSender(String string) {
		this.sender = string;
		
	}
}
