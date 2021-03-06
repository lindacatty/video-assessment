package main.java.videoassessment.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import main.java.videoassessment.form.ResponseForm;

/**
 * The response entity
 */
@Entity
@Cache
public class Response extends VideoAssessmentEntity {
  @Id
  @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
  private Long id;

  @Index
  private String videoId;

  @Index
  private Long groupId;

  @Index
  private Long topicId;

  @Index
  private String forUser;

  @Index
  private long templateId;

  @Index
  private int questionId;

  @Index
  private float score;

  /**
   * Email address of the owner.
   */
  @Index
  private String createdBy;

  @Index
  private DateTime createdOn;

  private String comment;

  public Response(long id, String createdBy, ResponseForm responseForm) {
    this.id = id;
    this.createdBy = createdBy;
    this.createdOn = DateTime.now();
    this.groupId = -1L;
    this.topicId = -1L;
    this.forUser = "";
    updateWithResponseForm(responseForm);
  }

  public Response(long id, String createdBy, String videoId, long templateId, int questionId,
                  float score, String comment) {
    this.id = id;
    this.createdBy = createdBy;
    this.createdOn = DateTime.now();
    this.groupId = -1L;
    this.topicId = -1L;
    this.forUser = "";
    this.videoId = videoId;
    this.templateId = templateId;
    this.questionId = questionId;
    this.score = score;
    this.comment = comment;
  }

  public Response(long id, String createdBy, long groupId, long topicId, String forUser,
                  long templateId, int questionId, float score, String comment) {
    this.id = id;
    this.createdBy = createdBy;
    this.createdOn = DateTime.now();
    this.videoId = "";
    this.groupId = groupId;
    this.topicId = topicId;
    this.forUser = forUser;
    this.templateId = templateId;
    this.questionId = questionId;
    this.score = score;
    this.comment = comment;
  }

  public void updateWithResponseForm(ResponseForm reponseForm) {
    videoId = reponseForm.getVideoId();
    templateId = reponseForm.getTemplateId();
    questionId = reponseForm.getQuestionId();
    score = reponseForm.getScore();
    comment = reponseForm.getComment();
  }

  public Response updateVideoId(String videoId) {
    this.videoId = videoId;
    return this;
  }

  public Long getId() {
    return id;
  }

  public String getVideoId() {
    return videoId;
  }

  public long getTemplateId() {
    return templateId;
  }

  public int getQuestionId() {
    return questionId;
  }

  public float getScore() {
    return score;
  }

  public String getComment() {
    return comment;
  }

  public long getGroupId() {
    return groupId;
  }

  public long getTopicId() {
    return topicId;
  }

  public String getForUser() {
    return forUser;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public String getCreatedOn() {
    return createdOn.toString(ISODateTimeFormat.dateTime());
  }

  private Response() {}
}
