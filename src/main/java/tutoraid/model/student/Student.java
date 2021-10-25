package tutoraid.model.student;

import java.util.ArrayList;
import java.util.Objects;

import tutoraid.commons.util.CollectionUtil;
import tutoraid.model.lesson.Lesson;

/**
 * Represents a Student in the TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final StudentName studentName;
    private final Phone studentPhone;
    private final ParentName parentName;
    private final Phone parentPhone;

    // Data fields
    private final ArrayList<String> lessonList;
    private final ProgressList progressList;
    private final PaymentStatus paymentStatus;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentName studentName, Phone studentPhone, ParentName parentName, Phone parentPhone,
                   ArrayList<String> lessonList, ProgressList progressList, PaymentStatus paymentStatus) {
        CollectionUtil.requireAllNonNull(studentName, studentPhone, parentName, parentPhone);
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.lessonList = lessonList;
        this.progressList = progressList;
        this.paymentStatus = paymentStatus;
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public Phone getStudentPhone() {
        return studentPhone;
    }

    public ParentName getParentName() {
        return parentName;
    }

    public Phone getParentPhone() {
        return parentPhone;
    }

    public ArrayList<String> getLessonList() {
        return lessonList;
    }

    public ProgressList getProgressList() {
        return progressList;
    }

    public Progress getLatestProgress() {
        return progressList.getLatestProgress();
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void addProgress(Progress toAdd) {
        progressList.addProgress(toAdd);
    }

    public Progress deleteLatestProgress() {
        return progressList.deleteLatestProgress();
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentName().equals(getStudentName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getStudentName().equals(getStudentName())
                && otherStudent.getStudentPhone().equals(getStudentPhone())
                && otherStudent.getParentName().equals(getParentName())
                && otherStudent.getParentPhone().equals(getParentPhone())
                && otherStudent.getProgressList().equals(getProgressList())
                && otherStudent.getPaymentStatus().equals(getPaymentStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, studentPhone, parentName, parentPhone, progressList, paymentStatus);
    }

    /**
     * Returns the name of the student in a string form
     *
     * @return The name of the student in a String
     */
    public String toNameString() {
        return this.getStudentName().toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getStudentName());

        if (studentPhone != null) {
            builder.append("; Student's phone: ")
                    .append(getStudentPhone());
        }

        if (parentName != null) {
            builder.append("; Parent's name: ")
                    .append(getParentName());

        }

        if (parentPhone != null) {
            builder.append("; Parent's phone: ")
                    .append(getParentPhone());
        }

        builder.append("; Progress: ")
                .append(getLatestProgress())
                .append("; Payment Status: ")
                .append(getPaymentStatus());

        return builder.toString();
    }

}
