package md.klass.application.models;

import java.time.LocalDateTime;

public class Note extends AbstractBaseModel {
  private String title;
  private String html;
  private int accountId;
  private LocalDateTime lastUpdateAt;
  public int getAccountId() {
    return accountId;
  }

  public Note(String title, String html, int accountId) {
    this.html = html;
    this.title = title;
    this.accountId = accountId;
  }
  public Note(){}

  public Note(int id, String title, String html, int accountId) {
    this(title, html, accountId);
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public String getHtml() {
    return html;
  }
  public void setLastUpdateAt(LocalDateTime lastUpdateAt){
    this.lastUpdateAt=lastUpdateAt;
  }
  public LocalDateTime getLastUpdateAt(){
    return this.lastUpdateAt;
  }
}
