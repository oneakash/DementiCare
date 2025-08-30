package com.dementicare.contentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "story_steps")
public class StoryStep {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank(message = "Step text is required")
    @Column(nullable = false, length = 1000)
    private String text;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "audio_url")
    private String audioUrl;
    
    @NotNull(message = "Step order is required")
    @Column(name = "step_order", nullable = false)
    private Integer stepOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_story_id", nullable = false)
    private SocialStory socialStory;
    
    // Constructors
    public StoryStep() {}
    
    public StoryStep(String text, Integer stepOrder, SocialStory socialStory) {
        this.text = text;
        this.stepOrder = stepOrder;
        this.socialStory = socialStory;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    
    public Integer getStepOrder() { return stepOrder; }
    public void setStepOrder(Integer stepOrder) { this.stepOrder = stepOrder; }
    
    public SocialStory getSocialStory() { return socialStory; }
    public void setSocialStory(SocialStory socialStory) { this.socialStory = socialStory; }
}