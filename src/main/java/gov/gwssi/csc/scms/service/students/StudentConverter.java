package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.warning.Warning;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Wang Zishi on 15/8/19.
 * 转换器
 */
public class StudentConverter extends BaseService implements Converter<Student, Map<String, Object>> {
    private List<String> fields;

    public StudentConverter(String[] fields) {
        this.fields = new ArrayList<String>();
        Collections.addAll(this.fields, fields);
    }

    public StudentConverter() {
        this.fields = new ArrayList<String>();
        for (Field field : Student.class.getDeclaredFields()) {
            this.fields.add(field.getName());
        }
    }

    /**
     * Convert the source of type S to target type T.
     *
     * @param source the source object to convert, which must be an instance of S
     * @return the converted object, which must be an instance of T
     * @throws IllegalArgumentException if the source could not be converted to the desired target type
     */
    @Override
    public Map<String, Object> convert(Student source) {
        Map<String, Object> target = new HashMap<String, Object>();

        setNullByField(source.getBasicInfo(), "student", BasicInfo.class);
        setNullByField(source.getSchoolfellow(), "student", Schoolfellow.class);
        setNullByField(source.getDiscuss(), "student", Discuss.class);
        setNullByField(source.getProfilesHistory(), "student", ProfilesHistory.class);
        setNullByField(source.getRegistrationInfo(), "student", RegistrationInfo.class);
        setNullByField(source.getSchoolRoll(), "student", SchoolRoll.class);
        setNullByField(source.getWarning(), "student", Warning.class);
        setNullByField(source.getAccidents(), "student", Accident.class);
        setNullByField(source.getRelatedAddress(), "student", RelatedAddress.class);
        setNullByField(source.getGrades(), "student", Grade.class);
        setNullByField(source.getGradeAttachment(), "student", GradeAttachment.class);
        setNullByField(source.getAbnormals(),"student", Abnormal.class);
        setNullByField(source.getTickets(),"student", Ticket.class);
        setNullByField(source.getInsurances(), "student", Insurance.class);
        setNullByField(source.getScholarshipXs(), "student", ScholarshipX.class);

//        source.setAbnormals(null);
//        source.setTickets(null);

        if (this.fields != null) {

            for (String fieldName : this.fields) {
                try {
                    if (fieldName.contains(".")){
//                        System.out.println("fieldName = " + fieldName);
                        String[] subFieldNames = fieldName.split("\\.");
                        Map<String, Object> map = new HashMap<String, Object>();

                        Field field = Student.class.getDeclaredField(subFieldNames[0]);
                        field.setAccessible(true);
                        if(field.get(source) == null){
                            map.put(subFieldNames[1], "");
                        } else {
                            Field subField = field.get(source).getClass().getDeclaredField(subFieldNames[1]);
                            subField.setAccessible(true);
                            Object subValue = subField.get(field.get(source));

                            map.put(subFieldNames[1], subValue);
                        }

                        if (target.get(subFieldNames[0]) != null) {
                            map.putAll((Map<String, Object>) target.get(subFieldNames[0]));
                        }

                        target.put(subFieldNames[0], map);

                        continue;
                    }
                    Field field= Student.class.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    target.put(fieldName, field.get(source));

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return target;
    }
}
