package md.klass.application.models;

public class Note extends AbstractBaseModel {
	private String title;
	private String html;
	private int accountId;

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public Note(String title, String html, int accountId){
		this.html=html;
		this.title=title;
		this.accountId=accountId;
	}
	public Note(int id, String title, String html, int accountId){
		this(title, html, accountId);
		this.id=id;
	}

	public String getTitle() {
		return title;
	}

	public String getHtml() {
		return html;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
