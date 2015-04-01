package gov.gwssi.csc.scms.domain.student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 来华前概况
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name="SCMS_PROFILES_HISTORY")
public class ProfilesHistory {
    @Id private Long id;
    @OneToOne private Student student;
    private String work;//单位
    private String occupation;//职业
    private String native_language;//母语
    private Boolean physical_examination;//体检
    private String chinese_level;//汉语水平
    private String english_level;//英语水平
    private String educated;//学历
    private Boolean english_teach;//可否英语授课

   }
