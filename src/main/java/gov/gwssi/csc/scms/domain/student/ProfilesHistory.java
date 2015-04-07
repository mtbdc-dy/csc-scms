package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 来华前概况
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_PROFILES_HISTORY")
public class ProfilesHistory {
    @Id
    @Column(nullable = false, unique = true, length = 19)
    private Long id;
    @Column
    private String work;//单位
    private String occupation;//职业
    private String native_language;//母语
    private Boolean physical_examination;//体检
    private String chinese_level;//汉语水平
    private String english_level;//英语水平
    private String educated;//学历
    private Boolean english_teach;//可否英语授课

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNative_language() {
        return native_language;
    }

    public void setNative_language(String native_language) {
        this.native_language = native_language;
    }

    public Boolean isPhysical_examination() {
        return physical_examination;
    }

    public void setPhysical_examination(Boolean physical_examination) {
        this.physical_examination = physical_examination;
    }

    public String getChinese_level() {
        return chinese_level;
    }

    public void setChinese_level(String chinese_level) {
        this.chinese_level = chinese_level;
    }

    public String getEnglish_level() {
        return english_level;
    }

    public void setEnglish_level(String english_level) {
        this.english_level = english_level;
    }

    public String getEducated() {
        return educated;
    }

    public void setEducated(String educated) {
        this.educated = educated;
    }

    public Boolean isEnglish_teach() {
        return english_teach;
    }

    public void setEnglish_teach(Boolean english_teach) {
        this.english_teach = english_teach;
    }
}
