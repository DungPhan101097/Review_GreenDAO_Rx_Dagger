package com.example.lap10715.review_greendaousingwithrx.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "note")
public class Note {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "content")
    private String content;

    @Property(nameInDb = "create_at")
    private String createAt;

    @Property(nameInDb = "module_id")
    private Long moduleId;

    @Generated(hash = 1855832770)
    public Note(Long id, String title, String content, String createAt,
            Long moduleId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.moduleId = moduleId;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Long getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }


}
